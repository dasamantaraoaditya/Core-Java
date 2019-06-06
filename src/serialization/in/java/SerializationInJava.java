/**
 * 
 */
package serialization.in.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author daditya
 * 
 *         why serialization? serialization is an protocol to transfer objects
 *         between java components, where serialization algorithm to deserialize
 *         and serialize are same in both the jvm's
 *
 *         how serialization works in java? Serialization is a process of saving
 *         an object to an sequence of bytes. This can be achieved by
 *         implementing Serializable interface
 *         writeObject() in objectOutputStream does the serialization
 *         readObject() does deseralization and retuens an object type (type cast to convert to required object)
 *
 *         what does serialize do? Look into
 *         https://www.javaworld.com/article/2072752/the-java-serialization-algorithm-revealed.html
 *         for serialize algorithm.
 */
public class SerializationInJava extends parentClass implements Serializable {

	childClass child = new childClass();
	
	public static void main(String[] args) {
		
		//serializing an object
		try {
			FileOutputStream fos = new FileOutputStream("temp.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			SerializationInJava sij = new SerializationInJava();
			oos.writeObject(sij);
			oos.flush();
			oos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//deserializing an object
		try {
			FileInputStream fis = new FileInputStream("temp.out");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			SerializationInJava sij = (SerializationInJava) ois.readObject();
			ois.close();
			System.out.println(sij.toString());
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

class parentClass implements Serializable {
	int version = 1;
	String name = "parent Class Object";

	@Override
	public String toString() {
		return "Version : " + this.version + ", Name : " + this.name;
	}
}

class childClass implements Serializable {
	int version = 2;
	String name = "Child Class Object";

	@Override
	public String toString() {
		return "Version : " + this.version + ", Name : " + this.name;
	}
}