import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;

import remoteObjects.Universe;
import remoteObjects.UniverseRemoteInterface;

/**
 * Class ClientSample to call the method with RMI.
 * @author jeje
 *
 */
public class ClientSample {
	private final String serviceName = "SearchSolution";
	private String registryServerName;
	
	private void doOperation(){
		try {
			UniverseRemoteInterface universeProxy = (UniverseRemoteInterface)Naming.lookup("//" + registryServerName + "/" + serviceName);
			
			switch(Universe.chooseMethod()){
			case 0:
				System.out.println("Sending value 0 !");
				universeProxy.setValue0();
				break;
			case 1:
				universeProxy.setValue42();
				System.out.println("Sending value 42 !");
				break;
			}
		} catch (Exception e){
			System.err.println("Exception:" + e.toString());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("You should give the server name in parameter.");
			return;
		}
		
		System.setSecurityManager(new SecurityManager());
		
		ClientSample cl = new ClientSample();
		cl.registryServerName = args[0];
		
		
		cl.doOperation();
	}
}
