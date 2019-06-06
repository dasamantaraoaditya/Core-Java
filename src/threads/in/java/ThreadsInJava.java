package threads.in.java;

/*
 * What is a thread? 
 * A thread is a smallest independent unit of execution which
 * shares the resources from a shared location. why do we need thread?
 */
public class ThreadsInJava {

	public static void main(String[] args) {
		//Thread created by extending thread class
		Thread etc =  new ExtendingThreadClass();
		etc.start();
		
		//Thread created by implementing runnable
		Thread iri = new Thread(new ImplementingRunnable());
		iri.start();
	}

}

/*
 * creating a thread by extending thread class. The main disadvantage of this
 * approach is that, java does not allows multiple inheritance as a result a
 * thread that is created using extends thread class cannot extend any other
 * class
 */
class ExtendingThreadClass extends Thread{
	String name = "default value";
	@Override
	public void run() {
		System.out.println("Thread Name : "+ Thread.currentThread().getName() + ", Status : Running");
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/*
 * Creating a thread by implementing runnable interface.
 * Thread constructor accepts runnable object which overrides the run method.
 */
class ImplementingRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println("Thread Name : "+ Thread.currentThread().getName() + ", Status : Running");
	}
}

