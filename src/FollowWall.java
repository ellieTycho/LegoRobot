import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


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
	}
	
	private boolean suppressed = false;
	private TouchSensor buffer;
	private UltrasonicSensor sonar;	
	private DifferentialPilot pilot;
	private NXTRegulatedMotor headControl;
	
	private WallDistances m_wallDistances = new WallDistances();
	
	private enum WallDirection
	{
		LEFT,
		RIGHT,
		FORWARD,
		BACKWARD,
		NODIRECTION
	}
	
	private WallDirection m_directionOfWall;
	
	private static final int WALL_DIST = 1;
	
	public FollowWall( TouchSensor touch,
			UltrasonicSensor sonar,
			DifferentialPilot pilot,
			NXTRegulatedMotor headControl )
	{
		buffer = touch;
		this.sonar = sonar;
		this.pilot = pilot;
		this.headControl = headControl;
		System.out.println("created");	
	}
	
	
	
	
	//@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		
		//System.out.println("Dir: " + m_directionOfWall);
//		if ( m_directionOfWall ==  WallDirection.NODIRECTION )
//		{
//			return false;
//		}
//		else
//		{
//			//System.out.println(" dir: " + m_directionOfWall);
//			//System.out.println("  dir: " + m_directionOfWall);
// 
//			return true;
//		}
		SearchForWall();
		
		if ( m_wallDistances.canSeeWall( ) == true )
		{
			if ( m_wallDistances.canSeeWall( WallDistances.LEFT ) &&
					m_wallDistances.canSeeWall( WallDistances.FORWARD))
			{
				return true;
			}
			else if ( m_wallDistances.canSeeWall( WallDistances.LEFT) == false )
			{
				return true;
			}
		}
		
		return false;
		
		
	}

	//@Override
	public void action() {
		// TODO Auto-generated method stub
		suppressed = false;
		
		if ( m_wallDistances.canSeeWall(WallDistances.FORWARD) )
		{
			if ( suppressed != false )
			{
				pilot.rotate(90);
			}			
		}
		else if ( m_wallDistances.canSeeWall(WallDistances.RIGHT) )
		{
			if ( suppressed != false )
			{
				pilot.rotate(180);
			}
		}
		else if ( m_wallDistances.canSeeWall(WallDistances.BACKWARD) )
		{
			if ( suppressed != false )
			{
				pilot.rotate(-90);
			}
		}
		
		//massive hack
		
		/*
		WallDirection directionOfWall = m_directionOfWall;  	
		
//		if(( directionOfWall == WallDirection.NODIRECTION ) && !suppressed ){
//			//SearchForWall();
//		}System.out.println("Dir2: " + directionOfWall);
		
		while(directionOfWall != WallDirection.NODIRECTION && !suppressed)
		{			
			if( directionOfWall == WallDirection.LEFT && !suppressed ){
				
			}
			else if ( ( directionOfWall == WallDirection.FORWARD ) && !suppressed  )
			{				
				pilot.rotate(90);
			}
			else if ( ( directionOfWall == WallDirection.RIGHT ) && !suppressed  )
			{				
				pilot.rotate(180);
			}
			else if ( ( directionOfWall == WallDirection.BACKWARD ) && !suppressed )
			{
				pilot.rotate( -90 );
			}
		}	
		
		
		*/
//		
//		if(( directionOfWall != WallDirection.LEFT  && 
//			 directionOfWall != WallDirection.NODIRECTION) 
//			 && !suppressed ){
//			
//			if ( ( directionOfWall == WallDirection.FORWARD ) && !suppressed  )
//			{
//				pilot.rotate(90);
//			}
//			else if ( ( directionOfWall == WallDirection.RIGHT ) && !suppressed  )
//			{
//				pilot.rotate(180);
//			}
//			else if ( ( directionOfWall == WallDirection.BACKWARD ) && !suppressed )
//			{
//				pilot.rotate( -90 );
//			}
//			else{
//				//nada
//			}			
//		}
//		else{
//			System.out.println("GO forward");
//			pilot.forward();
//			while(!suppressed){
//				Thread.yield();
//			}
//			
//		}
		

		
		
		
//		while ( ( directionOfWall != WallDirection.NODIRECTION ) && !suppressed )
//		{
//			System.out.println("In the loop!!");
//			if ( (directionOfWall == WallDirection.LEFT) && !suppressed )
//			{
//				pilot.travel(50);
//			}
//			else if ( ( directionOfWall == WallDirection.FORWARD ) && !suppressed  )
//			{
//				pilot.rotate(90);
//			}
//			else if ( ( directionOfWall == WallDirection.RIGHT ) && !suppressed  )
//			{
//				pilot.rotate(180);
//			}
//			else if ( ( directionOfWall == WallDirection.BACKWARD ) && !suppressed )
//			{
//				pilot.rotate( -90 );
//			}
//			else
//			{
//				System.out.println("SUPRESSED");
//				break;
//			}
//			
//			SearchForWall();
//		}	
	}
	
	private void SearchForWall()
	{
		
		m_wallDistances.SetDistance( WallDistances.LEFT, Scan() );
		headControl.rotate( -90 );
		m_wallDistances.SetDistance( WallDistances.FORWARD, Scan() );
		headControl.rotate( -90 );
		m_wallDistances.SetDistance( WallDistances.RIGHT, Scan() );
		headControl.rotate( -90 );
		m_wallDistances.SetDistance( WallDistances.BACKWARD, Scan() );
		headControl.rotate( 270 );
		
		
		
		/*
		m_directionOfWall = WallDirection.NODIRECTION;
		if ( Scan() > WALL_DIST ) //left
		{
			headControl.rotate( 90 );
			if ( Scan() > WALL_DIST  ) //forward
			{
				headControl.rotate( 90 );
				if ( Scan() > WALL_DIST ) //right
				{
					headControl.rotate( 90 );
					if ( Scan() > WALL_DIST ) //backward
					{
						System.out.println("Can't FIND WALL");
					}
					else
					{
						m_directionOfWall = WallDirection.BACKWARD;
						
					}
					headControl.rotate(-270);
				}
				else
				{
					m_directionOfWall = WallDirection.RIGHT;
					headControl.rotate(-180);					
				}
			}
			else
			{
				m_directionOfWall = WallDirection.FORWARD;
				headControl.rotate(-90);
			}			
		}
		else
		{
			m_directionOfWall = WallDirection.LEFT;			
		}		
		*/
	}

	public void suppress() {
		suppressed = true;		
	}
	
	private int Scan()
	{
		int distance = sonar.getDistance();
		System.out.println(distance);
		if ( distance > WALL_DIST )
		{
			distance = 0;
		}
		Sound.playNote(Sound.FLUTE, distance * 10, 150);
		return distance;
	}






}