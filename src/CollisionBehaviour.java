import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class CollisionBehaviour implements Behavior {

	private boolean suppressed = false;
	private TouchSensor buffer;
	private UltrasonicSensor sonar;	
	private DifferentialPilot pilot;
	private NXTRegulatedMotor headControl;
	
	public CollisionBehaviour(  TouchSensor touch,
								UltrasonicSensor sonar,
								DifferentialPilot pilot,
								NXTRegulatedMotor headControl){
		buffer = touch;
		this.sonar = sonar;
		this.pilot = pilot;
		this.headControl = headControl;
		System.out.println("created");
	}
	
	@Override
	public boolean takeControl() {	
		return buffer.isPressed();
	}

	@Override
	public void action()
	{
		//HELP();
		System.out.println("Bumped into suomething");
		while ( buffer.isPressed() != true )
		{
			pilot.travel(-25);
			if ( buffer.isPressed() != true )
			{
				pilot.rotate(90);
				if ( buffer.isPressed() != true )
				{
					pilot.travel(25);
					if ( buffer.isPressed() != true )
					{
						pilot.rotate(-90);
					}
				}
			}
		}
		
		
		/*suppressed = false;		
		System.out.println("collision");
		pilot.backward();
		try{
			Thread.sleep(500);
		}
		catch(Exception E){
			//oh no
		}
		pilot.stop();
		headControl.rotate(1000);
		int rDist = sonar.getDistance();
		System.out.println("r:" + rDist);
		headControl.rotate(-2000);
		int lDist = sonar.getDistance();
		System.out.println("l" + lDist);
		headControl.rotate(1000);
		
		if(rDist >= lDist){
			System.out.println("rotate right");
			pilot.rotate(-90);
		}
		else{
			System.out.println("rotate left");
			pilot.rotate(90);
		}
		
//		if(rDist == 255 && lDist == 255){
//			System.out.println("Help meee");
//			System.exit(0);
//		}
//		if(lDist == 255 ){
//			System.out.println("rotate right");
//			pilot.rotate(90);
//		}
//		else if(rDist == 255 ){
//			System.out.println("rotate left");
//			pilot.rotate(-90);
//		}
//		else if(rDist >= lDist){
//			System.out.println("rotate right");
//			pilot.rotate(90);
//		}
////		else if(lDist != 255 lDist > rDist){
////			System.out.println("rotate left");
////			pilot.rotate(-90);
////		}
////		else if(lDist != 255 && rDist != 255 ){
////			pilot.rotateRight();
////		}
//		else{
//			System.out.println("rotate left");
//			pilot.rotate(-90);
//		}
//		
////		while(pilot.isMoving() && !suppressed){
////			Thread.yield();
////		}
		*/
	}

	@Override
	public void suppress() {
		//suppressed = true;
		
	}
	
	private void HELP()
	{
		int[] m_musicType = Sound.PIANO;
		int m_multiplier = 10;
		Sound.playNote(m_musicType, 131,36*m_multiplier);
	    Sound.playNote(m_musicType, 131,37*m_multiplier);
	    Sound.playNote(m_musicType, 131,37*m_multiplier);
	    Sound.playNote(m_musicType, 131,37*m_multiplier);
	    Sound.playNote(m_musicType, 131,37*m_multiplier);
	    Sound.playNote(m_musicType, 131,37*m_multiplier);
	    Sound.playNote(m_musicType, 116,37*m_multiplier);
	    Sound.playNote(m_musicType, 116,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 98,37*m_multiplier);
	    Sound.playNote(m_musicType, 98,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 78,55*m_multiplier);
	    Sound.playNote(m_musicType, 78,17*m_multiplier);
	    Sound.playNote(m_musicType, 104,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 78,55*m_multiplier);
	    Sound.playNote(m_musicType, 78,17*m_multiplier);
	    Sound.playNote(m_musicType, 104,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,255*m_multiplier);
	    Sound.playNote(m_musicType, 58,62*m_multiplier);
	    Sound.playNote(m_musicType, 116,77*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 78,55*m_multiplier);
	    Sound.playNote(m_musicType, 78,17*m_multiplier);
	    Sound.playNote(m_musicType, 104,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 78,55*m_multiplier);
	    Sound.playNote(m_musicType, 78,17*m_multiplier);
	    Sound.playNote(m_musicType, 104,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,255*m_multiplier);
	    Sound.playNote(m_musicType, 58,62*m_multiplier);
	    Sound.playNote(m_musicType, 116,77*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,37*m_multiplier);
	    Sound.playNote(m_musicType, 104,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,255*m_multiplier);
	    Sound.playNote(m_musicType, 58,62*m_multiplier);
	    Sound.playNote(m_musicType, 73,255*m_multiplier);
	    Sound.playNote(m_musicType, 73,62*m_multiplier);
	    Sound.playNote(m_musicType, 98,255*m_multiplier);
	    Sound.playNote(m_musicType, 98,62*m_multiplier);
	    Sound.playNote(m_musicType, 116,77*m_multiplier);
	    Sound.playNote(m_musicType, 78,77*m_multiplier);
	    Sound.playNote(m_musicType, 87,157*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,55*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 87,77*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 73,55*m_multiplier);
	    Sound.playNote(m_musicType, 73,17*m_multiplier);
	    Sound.playNote(m_musicType, 110,73*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 98,55*m_multiplier);
	    Sound.playNote(m_musicType, 98,17*m_multiplier);
	    Sound.playNote(m_musicType, 73,77*m_multiplier);
	    Sound.playNote(m_musicType, 78,55*m_multiplier);
	    Sound.playNote(m_musicType, 78,17*m_multiplier);
	    Sound.playNote(m_musicType, 104,77*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 116,17*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 78,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 52,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 44,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 55,37*m_multiplier);
	    Sound.playNote(m_musicType, 65,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,255*m_multiplier);
	    Sound.playNote(m_musicType, 58,62*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 49,37*m_multiplier);
	    Sound.playNote(m_musicType, 73,37*m_multiplier);
	    Sound.playNote(m_musicType, 73,37*m_multiplier);
	    Sound.playNote(m_musicType, 98,37*m_multiplier);
	    Sound.playNote(m_musicType, 98,37*m_multiplier);
	    Sound.playNote(m_musicType, 73,37*m_multiplier);
	    Sound.playNote(m_musicType, 98,37*m_multiplier);
	    Sound.playNote(m_musicType, 58,255*m_multiplier);
	    Sound.playNote(m_musicType, 58,63*m_multiplier);
	}

}