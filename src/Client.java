
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Calendar;

import requests.ReplyRequest;
import requests.RequestID;
import serialisation.SerialisationFramework;

public class Client {
	
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
		
		
		DatagramSocket sendSocket = null, receiveSocket = null;
		SerialisationFramework serialisationFramework = new SerialisationFramework();
		
		try {
			sendSocket = new DatagramSocket();
			InetAddress aHost = InetAddress.getByName(args[0]);
			int serverPort = Integer.parseInt(args[1]),
					myPort = 6001;
			
			ReplyRequest replyRequest = new ReplyRequest(0, new RequestID(aHost, myPort), 0);
			
			byte[] message = serialisationFramework.buildByteMessage(replyRequest);
			
			DatagramPacket request = new DatagramPacket(message, message.length, aHost, serverPort);
			sendSocket.send(request);
			
			//TODO : wait for the answer
			boolean answer = false;
			receiveSocket = new DatagramSocket(myPort);
			
			while(!answer){
				DatagramPacket repliedDatagram = new DatagramPacket(message, message.length);
				receiveSocket.setSoTimeout(3000);
				receiveSocket.receive(repliedDatagram);
				
				ReplyRequest requestAnswer = (ReplyRequest)serialisationFramework.readFromByteArray(repliedDatagram.getData());
				
				if(requestAnswer.getRequestID().equals(replyRequest.getRequestID())){
					System.out.println("Reply received !");
					answer = true;
				}
			}
			System.out.println("Nothing received !");
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (receiveSocket != null)
				receiveSocket.close();
			if (sendSocket != null)
				sendSocket.close();
		}
	}
}
