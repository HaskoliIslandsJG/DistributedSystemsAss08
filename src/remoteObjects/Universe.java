package remoteObjects;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Universe extends UnicastRemoteObject implements UniverseRemoteInterface,Serializable{
	private int solution;
	
	public static int chooseMethod(){
		return new Random().nextInt(2);
	}
	
	public Universe() throws RemoteException {
		super();
	}
	
	public void setValue0 () throws RemoteException{
		solution = 0;
		System.out.println("The solution from the client is 0 !");
	}
	
	public void setValue42 () throws RemoteException{
		solution = 42;
		System.out.println("The solution from the client is 42 !");
	}
	
}
