import java.rmi.Naming;

import remoteObjects.UniverseRemoteInterface;

public class Client {
	private void doOperation(String serviceName, String registryServerName){
		try {
			UniverseRemoteInterface universeProxy = (UniverseRemoteInterface)Naming.lookup("//" + registryServerName + "/" + serviceName);
			
			universeProxy.setValue0();
		} catch (Exception e){
			System.err.println("Exception:" + e.toString());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		System.setSecurityManager(new SecurityManager());
		String serviceName = "SearchSolution";
		String registryServerName = args[0];
		
		Client cl = new Client();
		cl.doOperation(serviceName, registryServerName);
	}
}
