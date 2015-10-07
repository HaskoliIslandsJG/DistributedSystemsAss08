package requests;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import serialisation.SerialisationFramework;

public class ReplyRequest implements Serializable {
	
	public static int size(){
		try {
			return new SerialisationFramework().buildByteMessage(new ReplyRequest(0, new requestID(InetAddress.getLocalHost(), 600000000), 100)).length;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1000;
		}
	}

	/**
	 * 0=Request, 1=Reply
	 */
	private int messageType;
	
	private requestID requestID;
	
	private int methodID;

	public ReplyRequest(int messageType, requests.requestID requestID, int methodID) {
		super();
		this.messageType = messageType;
		this.requestID = requestID;
		this.methodID = methodID;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public requestID getRequestID() {
		return requestID;
	}

	public int getMethodID() {
		return methodID;
	}
	
	
}
