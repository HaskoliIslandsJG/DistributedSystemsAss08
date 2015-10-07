import java.rmi.Naming;

import remoteObjects.Universe;



public class Server {
	/**
	 * Remote object.
	 */
	private Universe universe;
	
	public Server(){
		try{
			universe = new Universe();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void main(String args[]){
		
		System.setSecurityManager(new SecurityManager());
		try{
			Universe universe = new Universe();
		
			String registryServerName = "localhost";
			String serviceName = "SearchSolution";
			System.out.println("before binding");
			Naming.rebind("//" + registryServerName + "/" + serviceName, universe);
			System.out.println("After binding");
			
			
		} catch (Exception e){
			System.err.println("Exception:" + e.toString());
			e.printStackTrace();
		}
		
//		if(args.length != 1){
//			System.out.println("You should give one argument corresponding to the port number of the listening server.");
//			return;
//		}
//		
//		DatagramSocket aSocket = null;
//		SerialisationFramework serialisationFramework = new SerialisationFramework();
//		
//		try {
//			int portNumber = Integer.parseInt(args[0]);
//			aSocket = new DatagramSocket(portNumber);
//			
//			/**
//			 * buffer to fill the request
//			 */
//			byte[] buffer = new byte[ReplyRequest.size()];
//			Server server = new Server();
//			System.out.println("Listening on port " + portNumber);
//			
//			/**
//			 * Listening loop
//			 */
//			while (true) {
//				/**
//				 * Request we can expect to receive.
//				 */
//				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
//				
//				aSocket.receive(request);
//				
//				/**
//				 * Deserialize the information received
//				 */
//				ReplyRequest requestReceived = (ReplyRequest)serialisationFramework.readFromByteArray(request.getData());
//				
//				
//				//TODO analyse the request and execute the action on the remote object.
//				int methodToCall = requestReceived.getMethodID();
//				if(methodToCall==0){
//					server.universe.setValue0();
//				}else if(methodToCall==42){
//					server.universe.setValue42();
//				}
//				
//			}
//		} catch (SocketException e) {
//			System.out.println("Socket: " + e.getMessage());
//		} catch (IOException e) {
//			System.out.println("IO: " + e.getMessage());
//		} finally {
//			if (aSocket != null)
//				aSocket.close();
//		}
	}
}
