package threads.in.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * what is a callable interface?
 * A callable interface is used to create a implementation object for futureTask entity which can intern be used to create a thread. 
 * 
 * why do we need a callable interface?
 * The special quality of callable interface is it throws exceptions and returns result's in the form of future objects.
 * 
 *  what is a futureTask?
 *  A futureTask is an thread related object which accepts runnable/callable in its constructor and logically points to a thread. The get method would return the result of the thread(sync call) in future.
 */
public class CallableThreadsInJava {

	public static void main(String[] args) {
		FutureTask<String> ft = new FutureTask<String>(new ImplementingCallable());
		Thread t = new Thread(ft);
		t.start();
		try {
			System.out.println("After execution thread returned : " + ft.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class ImplementingCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("Thread Name : " + Thread.currentThread().getName() + ", Status : Running");
		return "I am done";
	}

}
