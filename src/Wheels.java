import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.util.PilotProps;


public class Wheels extends Resource{

	final RotateMoveController pilot;
	
	
	public Wheels(){
		PilotProps pp = new PilotProps();
		//pp.loadPersistentValues();
		float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.96"));
		float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "13.0"));
		RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
		RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
		boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
		
		pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
	}
	
	//@Override
	private void invokeMethod(int methodRef){
		
		switch(methodRef){
			case 1: goForward(); break;
			case 2: stop(); break;
			default: System.err.println("Can't invoke method!");
		}
	}	
	
	private void goForward(){
		pilot.forward();
	}
	
	private void stop(){
		pilot.stop();
	}
	
}
