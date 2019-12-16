import java.net.*;
import java.io.*;

import javax.swing.SwingUtilities;
public class Client {
	
	private static int portNumber;
    private Socket socket = null;
    private ObjectOutputStream os = null;
    private ObjectInputStream is = null;
    public static String IPADDRESS;
    
    
    public Client(){};
	// the constructor expects the IP address of the server - the port is fixed
    public Client(String serverIP) {
    	if (!connectToServer(serverIP)) {
    		System.out.println("XX. Failed to open socket connection to: " + serverIP);            
    	}
    }

    private boolean connectToServer(String serverIP) {
    	try { // open a new socket to the server 
    		this.socket = new Socket(serverIP,portNumber);
    		this.os = new ObjectOutputStream(this.socket.getOutputStream());
    		this.is = new ObjectInputStream(this.socket.getInputStream());
    		System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress() 
    				+ " on port: " + this.socket.getPort());
    		System.out.println("    -> from local address: " + this.socket.getLocalAddress() 
    				+ " and port: " + this.socket.getLocalPort());
    	} 
        catch (Exception e) {
        	System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
        	System.out.println("    Exception: " + e.toString());	
        	return false;
        }
		return true;
    }

    private void getDate() {
    	String theDateCommand = "GetDate", theDateAndTime;
    	System.out.println("01. -> Sending Command (" + theDateCommand + ") to the server...");
    	this.send(theDateCommand);
    	try{
    		theDateAndTime = (String) receive();
    		System.out.println("05. <- The Server responded with: ");
    		System.out.println("    <- " + theDateAndTime);
    	}
    	catch (Exception e){
    		System.out.println("XX. There was an invalid object sent back from the server");
    	}
    	System.out.println("06. -- Disconnected from Server.");
    }
    
    public Float getTemp() {
    	String theTempCommand = "GetTemp";
    	Float	tempService;
    	System.out.println("01. -> Sending Command (" + theTempCommand + ") to the server...");
    	this.send(theTempCommand);
    	try{
    		tempService = (Float) receive();
    		System.out.println("05. <- The Server responded with: ");
    		System.out.println("    <- " + tempService);
    	}
    	catch (Exception e){
    		System.out.println("XX. There was an invalid object sent back from the server");
    		float f1=0;
    		return f1 ;
    	}
    	System.out.println("06. -- Disconnected from Server.");
    	return tempService;
    }
	
    // method to send a generic object.
    private void send(Object o) {
		try {
		    System.out.println("02. -> Sending an object...");
		    os.writeObject(o);
		    os.flush();
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Sending:" +  e.toString());
		}
    }

    // method to receive a generic object.
    private Object receive() 
    {
		Object o = null;
		try {
			System.out.println("03. -- About to receive an object...");
		    o = is.readObject();
		    System.out.println("04. <- Object received...");
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Receiving:" + e.toString());
		}
		return o;
    }

    public static void main(String args[]) 
    {	portNumber=Integer.parseInt(args[1]);
    	IPADDRESS=String. valueOf(args[0]);
    	if(args.length==2){
    		Client theApp = new Client(args[0]); 		
    		SwingUtilities.invokeLater(new Gui());
		}
    	else
    	{
    		System.out.println("Error: you must provide the address of the server");
    		System.out.println("Usage is:  java Client x.x.x.x  (e.g. java Client 192.168.7.2)");
    		System.out.println("      or:  java Client hostname (e.g. java Client localhost)");
    	}    
    	System.out.println("**. End of Application.");
    }
}