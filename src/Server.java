import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;



public class Server {


private static int playerCounter;

//port to listen for connections
private int port; 	

// list of clients
private ArrayList<ClientThread> clients;

//will be used for instructions
private boolean keepGoing ;


	

public Server(int portA){
	this.port = portA;
	clients = new ArrayList<ClientThread>();
	
	
	
} //end of contructor


public void  openServer(){
	
	keepGoing = true;

	try {
	
	ServerSocket serverSocket = new ServerSocket(port);
	

	            while(keepGoing)
	            	 {
	               
	               System.out.println("Server waiting for Players on port " + port + ".");
	                 
	                Socket socket = serverSocket.accept();      
	               
	               
	                if(!keepGoing) break;
	               
	                ClientThread t = new ClientThread(socket);  
	                clients.add(t);                                  
	                t.start();	            
	                	
	                	} 
	
		try {
	                serverSocket.close();
              for(int i = 0; i < clients.size(); ++i) {
	                    ClientThread tc = clients.get(i);
	                    try {
	                    tc.sInput.close();
	                    tc.sOutput.close();
	                    tc.socket.close();
	                    }
	                    catch(IOException ioE) {
	                    	
	                    	System.out.println("Unable to close clients input/output streams");
	                    	
	                        // not much I can do
	                    }
	                }
		}
	            catch(Exception e) {
	               
	                
	            }
	
	
				}
	catch (IOException e) {
			
	          
	        }
	
}// end of openServer

public synchronized void update(String message) {
       
	        
	       ClientThread c;
	        for(int i = clients.size() -1; i >= 0; i-- ) {
	        	
	      	
	        	 
	            c = clients.get(i);
	     
	            if(!c.msgToClient(message)) {
	                clients.remove(i);
	                
	            }
	        }
}

public synchronized void update1 (Message message) {
       
	        
	       ClientThread c;
	        for(int i = clients.size() -1; i >= 0; i-- ) {
	        	
	      	
	        	 
	            c = clients.get(i);
	     
	            if(!c.msgToClient(message)) {
	                clients.remove(i);
	                
	            }
	        }
}
	        
	    
	    
	   synchronized void remove(int pNumber) {
		
		
			ClientThread ct;
		for(int i = 0; i < clients.size(); ++i) {
			
			ct = clients.get(i);
			
			if(ct.playerNumber == pNumber) {
				clients.remove(i);
				return;
			}
		}
	}
public class ClientThread extends Thread
{
	//player number

	private	int playerNumber;
    private	Socket socket;
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Message msg;
   
     
      ClientThread(Socket s){
     	
     	
     	
     	socket = s;
	   	playerNumber = ++playerCounter;
     	try{
     	
     	sOutput = new ObjectOutputStream(socket.getOutputStream());
     	sInput  = new ObjectInputStream(socket.getInputStream());
     	//c.getView().PlayerConnect();
     	System.out.println("Player " + playerNumber + " has connected");
     	 
     	msgToClient("You are connected ");
     	
     	msgToClient("You are Player " + playerNumber);
     
     	for (int i = 0; i < clients.size(); i++){
     	msgToClient("Player " + (i+1) + " is also connected");
     	
     	
     	}
     	
     	update("Player " + playerNumber + " has connected");
     	
     	
     	}
     	 catch (IOException e) {
             
              
	            return;
	            }
	            
     	
     }// end of construtor
     
     
     
	public void waitForInco()
	{
		keepGoing = true;
		while(keepGoing){
			 try{
                   msg = (Message) sInput.readObject();
                }
	                catch (IOException e) {
	                    System.out.println(" Error with streams " + e);
	                    
	                }
	                
	                catch(ClassNotFoundException e2) {
	                   
	                }
			
			
			update1 (msg);
			
			
		}// end of while
		
		// this code will only get reached if 
		
		remove(playerNumber);
		close();
		
	}// end of function
	
	public void close(){
			
			
			try {
				if(sOutput != null) sOutput.close();
			}
			catch(Exception e) {}
		
			try {
				if(sInput != null) sInput.close();
			}
			catch(Exception e) {};
			
			try {
				if(socket != null) socket.close();
			}
			catch (Exception e) {}
		
	}
	
		public boolean msgToClient(String msg) {
		
			if(!socket.isConnected()) {close();
				return false;
			}
		
			try {
				Message m = new Message(msg,-1, "0");
				
				sOutput.writeObject(m);
			}
		
			catch(IOException e) {
			msgToClient("YOu have failed");
			}
			return true;
		}
		
		public boolean msgToClient(Message msg) {
		
			if(!socket.isConnected()) {close();
				return false;
			}
		
			try {
				
				sOutput.writeObject(msg);
			}
		
			catch(IOException e) {
			msgToClient("YOu have failed");
			}
			return true;
		}
		
		
		
		
}// end of class ClientThread


public static void main(String[] args) {
	
	Server gameServer = new Server(1500);
	
	gameServer.openServer();
	
	
}

}// end of server class
