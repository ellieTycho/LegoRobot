import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class WallFollow2 implements Behavior{

	private boolean suppressed = false;
	private TouchSensor buffer;
	private UltrasonicSensor sonar;	
	private DifferentialPilot pilot;
	private NXTRegulatedMotor headControl;
	private LightSensor lightSensor;
	private Flag flag;
	
	public WallFollow2( TouchSensor touch,
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
	    System.out.println(sonar.getDistance());
		if(sonar.getDistance()<=25){
			return true;			
		}
		else{
			return false;
		}
	}

	public void action() {
		// TODO Auto-generated method stub
//	    Sound.playNote(Sound.FLUTE, 329, 150);
//	    Sound.playNote(Sound.FLUTE, 293, 150);
//	    Sound.playNote(Sound.FLUTE, 261, 150);
		
		suppressed = false;
		
		pilot.forward();
		while(!suppressed){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int c_dist = sonar.getDistance();
			System.out.println(sonar.getDistance());
//			int c_dist = lightSensor.readValue();
//			System.out.println(lightSensor.readValue());
			if( c_dist < dist-leway){
				pilot.rotate(-20);
				justTurned = false;
			}
			else if( c_dist > dist+20 && justTurned == false){				
				pilot.rotate(80);
				try {
					pilot.forward();
					Thread.sleep(2000);
					pilot.stop();
					justTurned = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( c_dist > dist+leway){    //5
				pilot.rotate(20);
				justTurned = false;
			}			
			pilot.forward();
			try {
				Thread.sleep(1200);   //1200
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			
//			if(c_dist>50){  //move to top?
//				Sound.playNote(Sound.FLUTE, 100, 150);
//				break;
//			}
			
			headControl.rotate( 60 );			
			if(sonar.getDistance() < 30){
				pilot.rotate(-90);
			}
			headControl.rotate( -60 );
			
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			int c_dist = lightSensor.readValue();
//			System.out.println(lightSensor.readValue());
////			int c_dist = lightSensor.readValue();
////			System.out.println(lightSensor.readValue());
//			if( c_dist < dist-5){
//				pilot.rotate(-10);
//			}
//			else if( c_dist > dist+5){
//				pilot.rotate(10);
//			}
//			pilot.forward();
//			try {
//				Thread.sleep(1200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			if(c_dist>40){
//				Sound.playNote(Sound.FLUTE, 100, 150);
//				break;
//			}
		}
		pilot.stop();
		
		//System.out.println(sonar.getDistance());
		
	}

	public void suppress() {
		// TODO Auto-generated method stub
		suppressed = true;
	}

}
