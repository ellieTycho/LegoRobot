import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.navigation.RotateMoveController;


public class CollisionDetector implements Runnable{

	static TouchSensor bumper; 
	NXTMotor m_leftWheel;
	NXTMotor m_rightWheel;
	static RotateMoveController pilot;
	NXTMotor m_head;
	UltrasonicSensor us;
	
	//cat hello
	
	
	public CollisionDetector(TouchSensor touch, NXTMotor left, 
								NXTMotor right, NXTMotor head,
								UltrasonicSensor us,
								RotateMoveController pilot){
		bumper = touch;
		m_leftWheel = left;
		m_rightWheel = right;
		m_head = head;
		this.us = us;
		this.pilot = pilot;
	}
			
	//@Override
	public void run() {
		
				
	}
	
	interface State {		
		    public State next();		
	}

	
	enum States implements State {
	    Init {
	        @Override
	        public State next() {
	            switch(1) {
	                case 1: return NoCollision;
	                default: return Fail;
	            }
	        }
	    },
	    NoCollision {
	        @Override
	        public State next() {
	        	sleep(500);
	            switch(boolToInt(bumper.isPressed())) {
	                case 1: return Collision;
	                case 0: return NoCollision;
	                default: return Fail;
	            }
	        }
	    },
	    Collision {
	        @Override
	        public State next() {	        		        		        
	            switch(collision()) {
	                case 1: return NoCollision;
	                default: return Fail;
	            }
	        }
	    },
	    Fail {
	        @Override
	        public State next() {
	               return Fail;
	        }
	    };
	 
	    public abstract State next();
	}
	
	private static int collision(){
		System.out.println("Collision!");
		try {
			pilot.backward();
			Thread.sleep(1000);
		} catch (InterruptedException e) {				
			System.err.println("Backwards failed");
			e.printStackTrace();
		}
		
		return 1;
	}
	
	private static int boolToInt(boolean b){
		return b? 1 : 0;
	}
	
	private static void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
	}
	
	
//	private void noCollision(){
//		while(!bumper.isPressed()){
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {				
//				System.err.println("Touch sensor failed");
//				e.printStackTrace();
//			}
//		}
//		collision();
//	}
//	
//	private void collision(){
//		System.out.println("Collision!");
//		try {
//			pilot.backward();
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {				
//			System.err.println("Backwards failed");
//			e.printStackTrace();
//		}
//		
//		//pilot reverse
//		//change state
//		
//		//not stuff below
//		//pilot.stop();			
//		//head.rotate left and back again		
//		//head.rotate right an fdback again
//		//choose which way to go
//		//if both bad ->error
//		//if one bad take other
//		//if both good pick one radom
//		//turn		
//	}
//	
//	private void error(){
//		System.out.println("Collision Detection error");
//		//kill?
//	}
}
