import java.rmi.Naming;

import remoteObjects.Universe;

public class Server {
	public static void main(String args[]){
		System.setSecurityManager(new SecurityManager());
		try{
			Universe universe = new Universe();
		
			String registryServerName = "localhost";
			String serviceName = "SearchSolution";
			Naming.rebind("//" + registryServerName + "/" + serviceName, universe);
			
			
			
		} catch (Exception e){
			System.err.println("Exception:" + e.toString());
			e.printStackTrace();
		}
	}
}
