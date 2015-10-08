
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import requests.ReplyRequest;
import requests.RequestID;
import serialisation.SerialisationFramework;

public class Client {
	private final static SerialisationFramework serialisationFramework = new SerialisationFramework();
	private final static int maximumTries = 3;
	
	private DatagramSocket sendSocket = null, receiveSocket = null;
	private InetAddress serverAdress;
	private int serverPort , myPort = 6001, nbTries = 0;
	
	private void closeSockets(){
		if (receiveSocket != null)
			receiveSocket.close();
		if (sendSocket != null)
			sendSocket.close();
	}
	
	private boolean doOperation() throws SocketTimeoutException {
		try{
			sendSocket = new DatagramSocket();
			ReplyRequest replyRequest = new ReplyRequest(0, new RequestID(serverAdress, myPort), 0);
			byte[] message = serialisationFramework.buildByteMessage(replyRequest);
			
			DatagramPacket request = new DatagramPacket(message, message.length, serverAdress, serverPort);
			System.out.println("Try number " + (nbTries+1) + ".");
			sendSocket.send(request);
			
			//Wait for the answer
			receiveSocket = new DatagramSocket(myPort);
			
			while(true){
				DatagramPacket repliedDatagram = new DatagramPacket(message, message.length);
				receiveSocket.setSoTimeout(3000);
				receiveSocket.receive(repliedDatagram);
				
				ReplyRequest requestAnswer = (ReplyRequest)serialisationFramework.readFromByteArray(repliedDatagram.getData());
				
				if(requestAnswer.getRequestID().equals(replyRequest.getRequestID())){
					System.out.println("Reply received !");
					return true;
				}
			}
			
		}catch(SocketException e) {
			System.out.println("Socket: " + e.getMessage());
			return false;
		} catch (IOException e) {
			if(e instanceof SocketTimeoutException){
				throw (SocketTimeoutException)e;
			}else{
				System.out.println("IO: " + e.getMessage());
				return false;
			}
		}finally{
			closeSockets();
		}
	}
	
	/**
	 * Arguments :
	 * <ul>
	 * 	<li> hostname/IP adress
	 * 	<li> port number
	 * </ul>
	 * @param args
	 */
	public static void main(String args[]) {
		if(args.length != 2){
			System.out.println("You should give two arguments : the hostname/IP adress and the port number.");
			return;
		}
		
		
		
		Client mCLient = new Client();
		
			
		try {
			mCLient.serverAdress = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		mCLient.serverPort = Integer.parseInt(args[1]);
		
		
		//Try to send the request
		boolean replyOk = false;
		while(mCLient.nbTries<maximumTries){
			try{
				if(mCLient.doOperation()){
					replyOk = true;
					mCLient.nbTries = maximumTries+1;
				}
			} catch (SocketTimeoutException ste){
				mCLient.nbTries++;
			}
			
		}
		if(!replyOk)	System.out.println("No answer after " + mCLient.nbTries + " tries.");
	}
}
