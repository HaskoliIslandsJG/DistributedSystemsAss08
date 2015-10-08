package requests;

import java.io.Serializable;
import java.net.InetAddress;

public class RequestID implements Serializable{
	private int id;
	private InetAddress clientIP;
	private int clientPort;
	
	private static int id_class = 0;
	
	public RequestID(InetAddress clientIP, int clientPort) {
		super();
		this.id = id_class++;
		this.clientIP = clientIP;
		this.clientPort = clientPort;
	}
	
	
	
	public InetAddress getClientIP() {
		return clientIP;
	}



	public int getClientPort() {
		return clientPort;
	}

	public boolean equals(RequestID obj) {
		if(id == obj.id)	return true;
		return false;
	}
	
}
