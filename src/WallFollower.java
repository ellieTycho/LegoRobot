
//package org.lejos.sample.objectdetect;
import lejos.nxt.Button;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;

/**
 * This class demonstrates the object detection package. For this example you will need a single ultrasonic
 * sensor plugged into port 4. When you run the program, move the sensor closer and farther from objects. 
 * It will notify the listener and display the range on the LCD. Instructions are provided on the LCD display.
 *  
 * @author BB
 *
 */
public class WallFollower implements FeatureListener {

	/**
	 * Maximum distance to report a detected object. Default is 80 cm.
	 */
	public static int MAX_DETECT = 80;
	
	public static void main(String[] args) throws Exception {
		
		// Instructions:
		System.out.println("Autodetect ON");
		System.out.println("Max dist: " + MAX_DETECT);
		System.out.println("ENTER = do scan");
		System.out.println("RIGHT = on/off");
		System.out.println("ESCAPE = exit");
				
		// Initialize the detection objects:
		WallFollower listener = new WallFollower();
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);
		RangeFeatureDetector fd = new RangeFeatureDetector(us, MAX_DETECT, 500);
		fd.addListener(listener);
		
		Button.setKeyClickVolume(0); // Disable default button sound
		
	    NXTMotor MA = new NXTMotor(MotorPort.A);
	    NXTMotor MB = new NXTMotor(MotorPort.B);
		//NXTMotor MC = new NXTMotor(MotorPort.C);
		
		MB.forward();
		MA.forward();
		
		int RN = 100;
	    boolean STOP = false;
	    
	    while(!STOP){
			Feature res = fd.scan();
			int range = (int)res.getRangeReading().getRange();	  
			System.out.println("Range:" + range);
			if(range<5){
				STOP = true;
				RN = 0;
			}
			else{
				RN = 100*(range/80);
			}
			
			MB.setPower(RN);
			MA.setPower(RN);
			
			Thread.sleep(500);
	    }
		

		

		
		//MA.stop();

		//MB.stop();
		//MC.stop();
		
//		// Button inputs:
//		while(!Button.ESCAPE.isDown()) {
//			
//			// Perform a single scan:
//			if(Button.ENTER.isDown()) {
//				Feature res = fd.scan();
//				if(res == null) System.out.println("Nothing detected");
//				else {
//					// This is unorthodox--easier to piggy-back on listener's display code:
//					listener.featureDetected(res, fd);
//				}
//				
//			}
//			
//			// Enable/disable detection using buttons:
//			if(Button.RIGHT.isDown()) {
//				if(fd.isEnabled()) {
//					Sound.beepSequence();
//					System.out.println("Autodetect OFF");
//				} else {
//					Sound.beepSequenceUp();
//					System.out.println("Autodetect ON");
//				}
//				fd.enableDetection(!fd.isEnabled());
//				Thread.sleep(500);
//			}
//			Thread.yield();		
//		}
	}
	
	/**
	 * Output data about the detected object to LCD.
	 * Plays a tone corresponding to range.
	 */
	public void featureDetected(Feature feature, FeatureDetector detector) {
		int range = (int)feature.getRangeReading().getRange();
		Sound.playTone(1200 - (range * 10), 100);
		System.out.println("Range:" + range);
	}
}