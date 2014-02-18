import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class FollowWall implements Behavior {

	private boolean supress = false;
	
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
		
		boolean foundWall = false;
		if ( sonar.getDistance() == 255 ) //left
		{
			headControl.rotate( 90 );
			if ( sonar.getDistance() == 255 ) //forward
			{
				if ( sonar.getDistance() == 255 ) //right
				{
					headControl.rotate( 90 );
					if ( sonar.getDistance() == 255 ) //backward
					{
						headControl.rotate( 90 );
					}
					else
					{
						m_directionOfWall = WallDirection.BACKWARD;
						foundWall = true;
						headControl.rotate(-270);
					}
				}
				else
				{
					m_directionOfWall = WallDirection.RIGHT;
					foundWall = true;
					headControl.rotate(-180);					
				}
			}
			else
			{
				m_directionOfWall = WallDirection.FORWARD;
				foundWall = true;
				headControl.rotate(-90);
			}			
		}
		else
		{
			m_directionOfWall = WallDirection.LEFT;
			foundWall = true;			
		}		
		
		return foundWall;
	}

	//@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("Following Wall");
		while ( ( m_directionOfWall != WallDirection.NODIRECTION ) && !suppressed )
		{
			SearchForWall();
			if ( m_directionOfWall == WallDirection.LEFT )
			{
				pilot.travel(50);
			}
			else if ( m_directionOfWall == WallDirection.FORWARD )
			{
				pilot.rotate(90);
			}
			else if ( m_directionOfWall == WallDirection.RIGHT )
			{
				pilot.rotate(180);
			}
			else if ( m_directionOfWall == WallDirection.BACKWARD )
			{
				pilot.rotate( -90 );
			}
		}
		
		
		
	}
	
	private void SearchForWall()
	{
		m_directionOfWall = WallDirection.NODIRECTION;
		boolean foundWall = false;
		if ( sonar.getDistance() == 255 ) //left
		{
			headControl.rotate( 90 );
			if ( sonar.getDistance() == 255 ) //forward
			{
				if ( sonar.getDistance() == 255 ) //right
				{
					headControl.rotate( 90 );
					if ( sonar.getDistance() == 255 ) //backward
					{
						headControl.rotate( 90 );
					}
					else
					{
						m_directionOfWall = WallDirection.BACKWARD;
						foundWall = true;
						headControl.rotate(-270);
					}
				}
				else
				{
					m_directionOfWall = WallDirection.RIGHT;
					foundWall = true;
					headControl.rotate(-180);					
				}
			}
			else
			{
				m_directionOfWall = WallDirection.FORWARD;
				foundWall = true;
				headControl.rotate(-90);
			}			
		}
		else
		{
			m_directionOfWall = WallDirection.LEFT;
			foundWall = true;			
		}		
		
	}

	public void suppress() {
		supress = true;		
	}






}