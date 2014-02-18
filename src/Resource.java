import java.util.ArrayList;
import java.util.Queue;


public class Resource {
	
	//ArrayList<Integer> taskQueue;
	Queue<Integer> taskQueue;
	
	public Resource(){
		taskQueue = new Queue<Integer>(); 
	}
	
	public void addToQueue(int taskRef){
		taskQueue.addElement(taskRef);
	}		
	
	
	private void invokeMethod(int methodRef){
		//do nothing
	}

}
