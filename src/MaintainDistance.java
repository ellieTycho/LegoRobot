import lejos.nxt.LightSensor;
import lejos.robotics.subsumption.Behavior;


public class MaintainDistance implements Behavior {

	public MaintainDistance( LightSensor lightSensor )
	{
		m_lightSensor = lightSensor;
	}
	
	private LightSensor m_lightSensor;
	
	public boolean takeControl() 
	{
		LightDifference();
		// TODO Auto-generated method stub
		return false;
	}

	public void action()
	{
		// TODO Auto-generated method stub

	}

	public void suppress() 
	{
		// TODO Auto-generated method stub

	}
	
	private int LightDifference() 
	{
		int difference;
		
		m_lightSensor.calibrateLow();
		m_lightSensor.setFloodlight(false);
		int withoutLight = m_lightSensor.readNormalizedValue();
		
		try
		{
			Thread.sleep(500);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m_lightSensor.setFloodlight(true);
		
		try
		{
			Thread.sleep(500);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int withLight = m_lightSensor.readNormalizedValue();
		difference = withLight - withoutLight;
		m_lightSensor.setFloodlight(false);
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("Light difference: " + difference);
		
		return difference;
	}

}
