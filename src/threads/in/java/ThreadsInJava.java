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
		
		/* 
		 * Scenario: Design a user -> printer's model 
		 */
		User u1= new User(1,"user 1");
		User u2= new User(2,"user 2");
		for(int i=0;i<10;i++){
			Thread t1 = new Thread(new Printer(i,"Text -"+i, u1));
			t1.start();
		}
		for(int i=0;i<10;i++)
			new Thread(new Printer(i,"Text -"+i, u2)).start();
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



class Printer implements Runnable{
	String text;
	int id;
	User u;
	
	public Printer(int i, String string, User u) {
		this.text = string;
		this.id =i;
		this.u= u;
	}

	synchronized void print(){
		try {
			if(this.u.id == 1 && this.id ==1)
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("User Details : "+this.u.toString()+", *print:: " +this.text);
	}

	@Override
	public void run() {
		print();
	}
}

class User{
	int id;
	String name;
	
	public User(int i, String string) {
		this.id = i;
		this.name = string;
	}

	@Override
	public String toString() {
		return "UserId : "+ this.id+", UserNamse : "+this.name;
	}
}