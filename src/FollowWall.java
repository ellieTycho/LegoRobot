import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class FollowWall implements Behavior {
	
	private boolean suppressed = false;
	private TouchSensor buffer;
	private UltrasonicSensor sonar;	
	private DifferentialPilot pilot;
	private NXTRegulatedMotor headControl;
	
	private enum WallDirection
	{
		LEFT,
		RIGHT,
		FORWARD,
		BACKWARD,
		NODIRECTION
	}
	
	private WallDirection m_directionOfWall;
	
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
		
		SearchForWall();
		System.out.println("Dir: " + m_directionOfWall);
		if ( m_directionOfWall ==  WallDirection.NODIRECTION )
		{
			return false;
		}
		else
		{
			//System.out.println(" dir: " + m_directionOfWall);
			//System.out.println("  dir: " + m_directionOfWall);
 
			return true;
		}
	}

	//@Override
	public void action() {
		// TODO Auto-generated method stub
		suppressed = false;
		WallDirection w = m_directionOfWall;   //WTFFFFFF
		System.out.println("w :" + w);
		System.out.println("t :" + w);
		System.out.println("f :" + w);
		//System.out.println("---" + m_directionOfWall );
		//System.out.println("Following Wall " + m_directionOfWall);	

		//System.out.println( WallDirection.NODIRECTION +"\n "  + ( m_directionOfWall != WallDirection.NODIRECTION ));
		while ( ( w != WallDirection.NODIRECTION ) && !suppressed )
		{
			System.out.println("In the loop!!");
			if ( (m_directionOfWall == WallDirection.LEFT) && !suppressed )
			{
				pilot.travel(50);
			}
			else if ( ( m_directionOfWall == WallDirection.FORWARD ) && !suppressed  )
			{
				pilot.rotate(90);
			}
			else if ( ( m_directionOfWall == WallDirection.RIGHT ) && !suppressed  )
			{
				pilot.rotate(180);
			}
			else if ( ( m_directionOfWall == WallDirection.BACKWARD ) && !suppressed )
			{
				pilot.rotate( -90 );
			}
			else
			{
				System.out.println("SUPRESSED");
				break;
			}
			
			SearchForWall();
		}	
	}
	
	private void SearchForWall()
	{
		m_directionOfWall = WallDirection.NODIRECTION;
		if ( Scan() > 50 ) //left
		{
			headControl.rotate( 90 );
			if ( Scan() > 50  ) //forward
			{
				headControl.rotate( 90 );
				if ( Scan() > 50 ) //right
				{
					headControl.rotate( 90 );
					if ( Scan() > 50 ) //backward
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
		
	}

	public void suppress() {
		suppressed = true;		
	}
	
	private int Scan()
	{
		int distance = sonar.getDistance();
		Sound.playNote(Sound.FLUTE, distance * 10, 150);
		return distance;
	}






}