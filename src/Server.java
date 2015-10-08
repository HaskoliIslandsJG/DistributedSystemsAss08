import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.Naming;

import remoteObjects.Universe;
import requests.ReplyRequest;
import requests.RequestID;



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
		if(args.length != 1){
			System.out.println("You should give one argument corresponding to the port number of the listening server (for the UDP server.");
			return;
		}
		
		System.setSecurityManager(new SecurityManager());
		try{
			Universe universe = new Universe();
		
			String registryServerName = "localhost";
			String serviceName = "SearchSolution";
			Naming.rebind("//" + registryServerName + "/" + serviceName, universe);
			System.out.println("Binding ok !");
			
			
		} catch (Exception e){
			System.err.println("Exception:" + e.toString());
			e.printStackTrace();
		}
		
		DatagramSocket receiveSocket = null, answersocket = null;
		SerialisationFramework serialisationFramework = new SerialisationFramework();
		
		try {
			answersocket = new DatagramSocket();
			int portNumber = Integer.parseInt(args[0]);
			receiveSocket = new DatagramSocket(portNumber);
			
			/**
			 * buffer to fill the request
			 */
			byte[] buffer = new byte[ReplyRequest.size()];
			Server server = new Server();
			System.out.println("Listening UDP on port " + portNumber);
			
			/**
			 * Listening loop
			 */
			while (true) {
				/**
				 * Request we can expect to receive.
				 */
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				receiveSocket.receive(request);
				
				/**
				 * Deserialize the information received
				 */
				ReplyRequest requestReceived = (ReplyRequest)serialisationFramework.readFromByteArray(request.getData());
				
				
				//Analyse the request and execute the action on the remote object.
				int methodToCall = requestReceived.getMethodID();
				if(methodToCall==0){
					server.universe.setValue0();
				}else if(methodToCall==42){
					server.universe.setValue42();
				}
				
				//TODO : send the answer
				RequestID dialogID = requestReceived.getRequestID();
				ReplyRequest answerToSend = new ReplyRequest(1, dialogID, 0);
				byte[] reply = serialisationFramework.buildByteMessage(answerToSend);
				DatagramPacket answerDatagram = new DatagramPacket(reply, reply.length, dialogID.getClientIP(), dialogID.getClientPort());
				answersocket.send(answerDatagram);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (receiveSocket != null)
				receiveSocket.close();
			if (answersocket != null)
				answersocket.close();
		}
	}
}
