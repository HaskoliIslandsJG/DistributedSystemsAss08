package requests;

import java.io.Serializable;
import java.net.InetAddress;

public class requestID implements Serializable{
	private int id;
	private InetAddress clientIP;
	private int clientPort;
	
	private static int id_class = 0;
	
	public requestID(InetAddress clientIP, int clientPort) {
		super();
		this.id = id_class++;
		this.clientIP = clientIP;
		this.clientPort = clientPort;
	}
	
}
