import lejos.robotics.subsumption.Behavior;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class FindWall implements Behavior{

	
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private Flag flag;
	private NXTRegulatedMotor headControl;
	private UltrasonicSensor sonar;
	
	public FindWall(DifferentialPilot pilot, Flag flag, NXTRegulatedMotor hc,
			UltrasonicSensor sonar){
		this.pilot = pilot;
		this.flag = flag;
		headControl = hc;
		this.sonar = sonar;
	}
	
	
	public boolean takeControl() {
			return true;
	}

	
	public void action() {
		suppressed = false;
		System.out.println("Finding Wall");
		pilot.forward();
		while(!suppressed){
			
			//head currently looking left
			if(sonar.getDistance()<=25){
				flag.setFoundWall(true);
				pilot.stop();
				Thread.yield();
			}
			else{
				//look forward
				headControl.rotate( 60 );
				if(sonar.getDistance()<=25){
					flag.setFoundWall(true);
					pilot.rotate(-90);
					pilot.stop();
					headControl.rotate( -60 );
					Thread.yield();	
				}
				else{
					headControl.rotate( -60 );
				}
				
			}
			
			
				
			
			//Thread.yield();	don't need to yield as top behaviour
		}
		pilot.stop();
	}

	
	public void suppress() {
		System.out.println("supressed!");
		suppressed = true;		
	}
}
