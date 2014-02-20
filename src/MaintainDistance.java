import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class MaintainDistance implements Behavior {

	public MaintainDistance( LightSensor lightSensor,
							 DifferentialPilot pilot)
	{
		m_lightSensor = lightSensor;
		m_pilot = pilot;
	}
	
	private LightSensor m_lightSensor;
	private DifferentialPilot m_pilot;
	private boolean suppressed = false;
	private static final int LOW_RANGE = 50;
	private static final int HIGH_RANGE = 130;
	
	public boolean takeControl() 
	{
		int difference = LightDifference();
		// TODO Auto-generated method stub
		if(isInRange(difference)){
			return false;
		}
		else{
			return true;
		}
	}

	public void action()
	{
		m_pilot.forward();
			
		
		while(!suppressed ){

			int difference = LightDifference();	
			
			if(isInRange(difference)){
				Thread.yield();
			}				
			else if(isBelowRange(difference)){
				moveCloser();
			}
			else{ //lost wall
				
			}
			
		}

		m_pilot.stop();
		
	}
	
	private void readjustToDistace(int distance){
		int currentDist = getLightDistance();
		if(currentDist>distance){
			//move left then straigten
		}
		else{
			//move right then straghten
		}
		
	}
	
	private boolean isInRange(int distance){
		if(distance<=HIGH_RANGE && distance>=LOW_RANGE){
			return true;
		}
		else{
			return false;
		}
	}

	public void suppress() 
	{
		// TODO Auto-generated method stub
		suppressed = true;

	}
	
	private int LightDifference() 
	{
		int difference;
		
		m_lightSensor.calibrateLow();
		m_lightSensor.setFloodlight(false);
		int withoutLight = m_lightSensor.readNormalizedValue();
		
		try
		{
			Thread.sleep(50);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m_lightSensor.setFloodlight(true);
		
		try
		{
			Thread.sleep(50);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int withLight = m_lightSensor.readNormalizedValue();
		difference = withLight - withoutLight;
		m_lightSensor.setFloodlight(false);
		
		System.out.println("Light difference: " + difference);
		
		return difference;
	}

}
