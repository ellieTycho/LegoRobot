import lejos.robotics.subsumption.Behavior;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class FindWall implements Behavior{

	
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	
	public FindWall(DifferentialPilot pilot){
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		Motor.A.forward();
		Motor.B.forward();
		while ( suppressed = false )
		{
			Thread.yield();
		}
		Motor.A.stop();
		Motor.B.stop();
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("Finding Wall");
		pilot.forward();
		while(!suppressed){
			Thread.yield();	
		}
		pilot.stop();
	}

	@Override
	public void suppress() {
		System.out.println("supressed!");
		suppressed = true;		
	}
}
