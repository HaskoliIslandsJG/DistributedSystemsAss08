package remoteObjects;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Universe extends UnicastRemoteObject implements UniverseRemoteInterface{
	private int solution;
	
	public Universe() throws RemoteException {
		super();
	}
	
	public void setValue0 () throws RemoteException{
		solution = 0;
	}
	
	public void setValue42 () throws RemoteException{
		solution = 42;
	}
	
}
