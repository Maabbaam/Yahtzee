
import java.net.*;
import java.io.*;
import java.util.*;


public class Client  {

	
	private ClientGui c;
	private String server;
	private int port;
	private ObjectInputStream sInput;		
	private ObjectOutputStream sOutput;		
	private Socket socket;
	private boolean aborted;



	Client(String s, int p) {
		
		server = s;
		port = p;
		aborted = true;
	}

	
	Client(String server, int port, ClientGui client) {
		this.server = server;
		this.port = port;
		aborted = true;
		this.c = client;
	}
	
	public boolean start() {
	
		try {
			socket = new Socket(server, port);
		} 
	
		catch(Exception e) {
			c.append("Unable to connect to Yahtzee Server");
			return false;
		}
		
		String m = "Your now playing the game Yahtzee on server" + socket.getInetAddress() + ":" + socket.getPort();
		c.append(m);
	
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			c.append("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

	
		new MessagesFromServer().start();
		return true;
	}

	
	public void sendData(Message msg) {
		try {
			sOutput.writeObject(msg);
		}
		catch(IOException e) {
			c.append("Exception writing to server: " + e);
		}
	}

	
	public void disconnect() {
		try { 
			if(sInput != null) sInput.close();
		}
		catch(Exception e) {} 
		try {
			if(sOutput != null) sOutput.close();
		}
		catch(Exception e) {} 
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} 
		
				
			c.FailedToConnectServer();
			
	}
	
	 
	class MessagesFromServer extends Thread {

		public void run() {
			while(true) {
				try {
					Message msg = (Message) sInput.readObject();
					// if console mode print the message and add back the prompt
					
						if(msg.getLoc() == -1 ) 	c.append(msg.getMsg());
					else	c.fun(msg.getLoc(),msg.getDat());
					//	c.append(msg.getMsg());
					
				}
				catch(IOException e) {
					c.append("Server has close the connection: " + e);
				 
						c.FailedToConnectServer();
					break;
				}
				
				catch(ClassNotFoundException e2) {
				}
			}
		}
	}
}
		
			



