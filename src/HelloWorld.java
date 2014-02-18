
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class HelloWorld
{
	private static int[] m_musicType = Sound.PIANO;
	
	private static NXTRegulatedMotor m_rightWheel = Motor.A;
	private static NXTRegulatedMotor m_leftWheel = Motor.B;
	
	private static NXTRegulatedMotor m_headControl = Motor.C;
	
	private static DifferentialPilot pilot;
	
    public static void main (String[] args) 
  {
    System.out.println("My name is Judge");
    Button.waitForAnyPress();
    /*m_rightWheel.forward();
    m_leftWheel.forward();
    Button.waitForAnyPress();
    */
    m_headControl.rotate(2000);
    m_headControl.rotate(-2000);
    
    
    
    UltrasonicSensor uSensor = new UltrasonicSensor(SensorPort.S4);
    TouchSensor frontTouchSensor = new TouchSensor(SensorPort.S3);
    pilot = new DifferentialPilot( 7, 14 , m_leftWheel, m_rightWheel);
    
    Sound.playNote(m_musicType, 261, 150);
    Sound.playNote(m_musicType, 293, 150);
    Sound.playNote(m_musicType, 329, 150);
    Sound.playNote(m_musicType, 349, 150);
    Sound.playNote(m_musicType, 391, 150);
    Sound.playNote(m_musicType, 440, 150);
    Sound.playNote(m_musicType, 493, 150);
    Sound.playNote(m_musicType, 523, 150);
    Sound.playNote(m_musicType, 493, 150);
    Sound.playNote(m_musicType, 440, 150);
    Sound.playNote(m_musicType, 391, 150);
    Sound.playNote(m_musicType, 349, 150);
    Sound.playNote(m_musicType, 329, 150);
    Sound.playNote(m_musicType, 293, 150);
    Sound.playNote(m_musicType, 261, 150);
    
    System.out.println("Played some music");
    
    Button.waitForAnyPress();
    //GoForward();
    
//    inializeWheelMotors(3
//    		3
//    		3.30
//    		.);
//    int angle = 180;
//    int time = 300;
    
    //GoBackward();
    //System.out.println("Going Forward");
    
    while ( (frontTouchSensor.isPressed() == false )
    		|| ( getUSensorRange(uSensor) < 10 ) )
    {
    	//stepForward();
        //rotateRightBy(angle);
        //rotateLeftBy(angle);
        //printTachoCount();
        //angle =+ 45;
    	try{
    		pilot.travel(50);
    		SensorPort.S1.setPowerType(100);
    		pilot.rotate(90);
    		Thread.sleep(1000);
    		pilot.rotate(-90);
    		Thread.sleep(1000); 
    		pilot.rotate(360);
    		pilot.rotate(-360);
    	}
    	catch(Exception e){
    		
    	}
    	
    	//    		try {
//    			System.out.println( "Range: " + getUSensorRange(uSensor));
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    }
    Stop();
    System.out.println(uSensor.getDistance());
    
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    //UltrasonicSensor.
        
  }
    
    
   private static void TurnLeft()
   {
	   m_leftWheel.backward();
	   m_rightWheel.forward();
   }
   
   private static void TurnRight()
   {
	   m_leftWheel.forward();
	   m_rightWheel.backward();
   }
  
   private static void GoForward()
   {
	  m_leftWheel.forward();
	  m_rightWheel.forward();
   }
   
   private static void GoBackward()
   {
	   m_leftWheel.backward();
		  m_rightWheel.backward();
	   
   }
   
   private static void Stop()
   {
	   m_leftWheel.stop();
	   m_rightWheel.stop();
	   
   }
  
   //want to use RangeFeatureDetecot class?
   private static int getUSensorRange(UltrasonicSensor uSensor){
	   return (int)uSensor.getRange();
   }
   
   private static void printTachoCount(){
	   System.out.println("LW: " + m_leftWheel.getTachoCount());
	   System.out.println("RW: " + m_rightWheel.getTachoCount());
   }
   
   private static void inializeWheelMotors(){
	   m_leftWheel.rotateTo(0);
	   m_rightWheel.rotateTo(0);
	   System.out.println("Wheels initalized");
   }
  
   private static void rotateRightBy(int angle){
	   m_leftWheel.rotate(angle);
	   m_rightWheel.rotate(-angle);
   }
   
   private static void rotateLeftBy(int angle){
	   m_leftWheel.rotate(-angle);
	   m_rightWheel.rotate(angle);
   }
   
   private static void turnLeftFor(int millisecs){
	   try {		
		   TurnLeft();
			Thread.sleep(millisecs);
	   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
   }
   
   private static void turnRightFor(int millisecs){
	   try {		
		   TurnRight();
			Thread.sleep(millisecs);
	   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
   
   private static void stepForward(){
	   GoForward();
	   try {		
			Thread.sleep(500);
			Stop();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
  
  

}
