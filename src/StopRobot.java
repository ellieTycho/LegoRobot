import lejos.nxt.TouchSensor;
import lejos.robotics.subsumption.Behavior;


public class StopRobot implements Behavior{
	
	private TouchSensor m_buffer;
	
	public StopRobot( TouchSensor sensor )
	{
		m_buffer = sensor;
	}
	
	
	public boolean takeControl() {
		return m_buffer.isPressed();
	}

	
	public void action() {
		System.out.println("Exiting");
		System.exit(0);
		// TODO Auto-generated method stub
		
	}

	
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}