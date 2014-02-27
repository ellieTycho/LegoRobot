import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class WallFollow3 implements Behavior{

	private boolean suppressed = false;
	private TouchSensor buffer;
	private UltrasonicSensor sonar;	
	private DifferentialPilot pilot;
	private NXTRegulatedMotor headControl;
	private LightSensor lightSensor;
	private Flag flag;
	
	public WallFollow3( TouchSensor touch,
			UltrasonicSensor sonar,
			DifferentialPilot pilot,	
			NXTRegulatedMotor headControl,
			LightSensor lightSensor,
			Flag flag)
	{
		buffer = touch;
		this.sonar = sonar;
		this.pilot = pilot;
		this.headControl = headControl;
		this.lightSensor = lightSensor;
		this.flag = flag;
		//System.out.println("created");
		
	}
	
	private static final int dist = 20;
	private static final int leway = 5;
	private boolean justTurned = false;
	
	public boolean takeControl() {
//		if(flag.getFoundWall() == true){
//			flag.setFoundWall(false);
//			return true;		
//		}
//		else{
//			return false;
//		}
		//return true;
//	    System.out.println(sonar.getDistance());
//		if(sonar.getDistance()<=25){
//			return true;			
//		}
//		else{
//			return false;
//		}
		return true;
	}

	public void action() {
		suppressed = false;
		System.out.println(sonar.getDistance());
		int dist = 20;
		pilot.setTravelSpeed(10);	
		pilot.forward();
		while(!suppressed){
			int c_dist = sonar.getDistance();
			System.out.println(c_dist);
			int diff = dist-c_dist;
			if(Math.abs(diff) <= 1){
				//do nothing
			}
			else if(bigJump(diff) == true){
				Sound.playNote(Sound.FLUTE, 300, 150);
				Sound.playNote(Sound.FLUTE, 300, 150);
				Sound.playNote(Sound.FLUTE, 300, 150);
				pilot.arc(100, 90);				
			}
			else if(c_dist >= dist+2){
				
				
				pilot.rotate(10);
				
				try {
					pilot.forward();
					Thread.sleep(700);
					Sound.playNote(Sound.FLUTE, 293, 150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pilot.rotate(10);
				pilot.forward();
			}			
			else if(c_dist <= dist+2){
				pilot.rotate(-10);
				pilot.forward();
				try {
					pilot.forward();
					Thread.sleep(700);
					Sound.playNote(Sound.FLUTE, 293, 150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pilot.rotate(-10);
				pilot.forward();
			}
			
			pilot.forward();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean bigJump(int diff){
		if(diff>20){
			return true;
		}
		else{
			return false;
		}
		
	}

	public void suppress() {
		// TODO Auto-generated method stub
		suppressed = true;
	}

}

