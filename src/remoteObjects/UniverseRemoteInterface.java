package remoteObjects;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UniverseRemoteInterface extends Remote{
	void setValue0() throws RemoteException;
	void setValue42() throws RemoteException;
}
