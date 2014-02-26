import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Timer;


public class FollowWall implements Behavior {
	
	private class WallDistances
	{
		final static int LEFT = 0;
		final static int FORWARD = 1;
		final static int RIGHT = 2;
		final static int BACKWARD = 3;
				
		int[] wallDistances = {0,0,0,0};
		
		public int getDistanceToWall( int direction )
		{
			return wallDistances[direction];
		}
		
		
		public boolean canSeeWall()
		{
			boolean canSeeWall = false;
			for ( int distance:wallDistances )
			{
				if ( distance != 0 )
				{
					canSeeWall = true;
				}				
			}
			return canSeeWall;
		}
		
		public boolean canSeeWall( int direction )
		{
			if ( wallDistances[direction] > 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		
		public void SetDistance( int direction, int  distance)
		{
			wallDistances[direction] = distance;
		}
		
		public void SetDistances(int[] distances)
		{
			wallDistances = distances;
		}
		
		public int[] GetDistances()
		{
			int[] newDistances = {wallDistances[0],wallDistances[1],wallDistances[2],wallDistances[3]};
			return newDistances;
		}
		
		public void printDistances(){
			String str = "";
			for(int i =0; i<wallDistances.length; i++)
				str += wallDistances[i] + " ";
			System.out.println(str );
		}
		
	}
	
	private boolean suppressed = false;
	private TouchSensor buffer;
	private UltrasonicSensor sonar;	
	private DifferentialPilot pilot;
	private NXTRegulatedMotor headControl;
	
	private Timer m_timer;
	private int m_memorySpan;
	
	private WallDistances m_wallDistances = new WallDistances();
	private WallDistances m_previousWallDistances = new WallDistances();
	
	private enum WallDirection
	{
		LEFT,
		RIGHT,
		FORWARD,
		BACKWARD,
		NODIRECTION
	}
	
	private WallDirection m_directionOfWall;
	
	private static final int VIEW_DIST = 40;
	private static final int DIST_CHANGE = 3;
	
	public FollowWall( TouchSensor touch,
			UltrasonicSensor sonar,
			DifferentialPilot pilot,	
			NXTRegulatedMotor headControl )
	{
		buffer = touch;
		this.sonar = sonar;
		this.pilot = pilot;
		this.headControl = headControl;
		//System.out.println("created");
		
	}
	
	
	
	
	//@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		//System.out.println("take control");
		SearchForWall();
		boolean returnValue = false;
		
		if ( m_wallDistances.canSeeWall( ) == true )
		{
			System.out.println("Can see a wall.");
			if ( m_wallDistances.canSeeWall( WallDistances.LEFT ) &&
					m_wallDistances.canSeeWall( WallDistances.FORWARD))
			{
				returnValue = true;
			}
			else if( m_wallDistances.canSeeWall( WallDistances.LEFT) == true ){
				
				if ( m_previousWallDistances.canSeeWall(WallDistances.LEFT) == true )
				{
					if(Math.abs(m_wallDistances.getDistanceToWall(WallDistances.LEFT)-
						    m_previousWallDistances.getDistanceToWall(WallDistances.LEFT)) 
						    > DIST_CHANGE ){
							returnValue = true;
						}
				}				
			}
			else if ( m_wallDistances.canSeeWall( WallDistances.LEFT) == false )
			{
				returnValue = true;
			}
		}
		else if(m_previousWallDistances.canSeeWall(WallDistances.LEFT))
		{
				System.out.println("I lost the wall!");
				returnValue = true;
		}
		//System.out.println( m_previousWallDistances.canSeeWall() );
		System.out.println("return takecontrol");
		return returnValue;	
	}

	//@Override
	public void action() {
		// TODO Auto-generated method stub
		//System.out.println("Took control like a boss");
		suppressed = false;
		
		//m_wallDistances.printDistances();
		
		if ( m_wallDistances.canSeeWall(WallDistances.FORWARD) )
		{
			System.out.println("Wall Forward");
			if ( suppressed == false )
			{
				pilot.rotate(-100);
			}			
		}
		else if ( m_wallDistances.canSeeWall(WallDistances.RIGHT) )
		{
			System.out.println("Wall Right");
			if ( suppressed == false )
			{
				pilot.rotate(180);
			}
		}
		else if ( m_wallDistances.canSeeWall(WallDistances.BACKWARD) )
		{
			System.out.println("Wall Backward");
			if ( suppressed == false )
			{
				pilot.rotate(100);
			}
		}
		else if( m_wallDistances.canSeeWall( WallDistances.LEFT) == true ){
			
			if ( m_previousWallDistances.canSeeWall(WallDistances.LEFT) == true )
			{
				int change = m_wallDistances.getDistanceToWall(WallDistances.LEFT)-
						m_previousWallDistances.getDistanceToWall(WallDistances.LEFT);
				
				System.out.println("change: " + change);
				if(change < -DIST_CHANGE){
					pilot.rotate(-20);
				}
				else if(change > DIST_CHANGE){
					pilot.rotate(20);
				}
			}
						
		}
		else
		{
			System.out.println("Wall in no direction????????");
			if(m_previousWallDistances.canSeeWall(WallDistances.LEFT)){
				if ( suppressed == false )
				{
					System.out.println("TURNING LEFT");
					pilot.rotate(100);		
					m_previousWallDistances = new WallDistances();
			
				}
			}
		}
		System.out.println("return action");
	}
	
	private void SearchForWall()
	{	
		if ( m_wallDistances.canSeeWall() == true )
		{
			m_previousWallDistances.SetDistances( m_wallDistances.GetDistances() );
			m_memorySpan = 0;
		}
		else
		{
			m_memorySpan++;
			if ( m_memorySpan > 5 )
			{
				m_previousWallDistances.SetDistances( m_wallDistances.GetDistances() );
			}
		}
		
		headControl.setSpeed(10000);
		m_wallDistances.SetDistance( WallDistances.LEFT, Scan() );
		headControl.rotate( 60 );
		int distance =  Scan();
		m_wallDistances.SetDistance( WallDistances.FORWARD, distance);
		if ( distance != 0 )
		{
			headControl.rotate( -60 );
		}
		else
		{
			headControl.rotate( 60 );
			m_wallDistances.SetDistance( WallDistances.RIGHT, Scan() );
			headControl.rotate( 60 );
			m_wallDistances.SetDistance( WallDistances.BACKWARD, Scan() );
			headControl.rotate( -180 );
		}
	}

	public void suppress() {
		suppressed = true;		
	}
	
	private int Scan()
	{
		int distance = sonar.getDistance();
		//System.out.println(distance);
		if ( distance > VIEW_DIST )
		{
			distance = 0;
		}
		Sound.playNote(Sound.FLUTE, distance * 10, 150);
		return distance;
	}






}