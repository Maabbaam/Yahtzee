import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ClientController extends JFrame{
	private ClientGui view;

	
		public ClientController (ClientGui g){
			
			super("Yahtzee");
	
		view = g;
		
		view.setLocation(0,0);
		getContentPane().add(view); 
			
		//size
		setSize(1000, 650);
			
		}
	/*public ClientController (){
			super("Yahtzee");
		
		view = new ClientGui(model);
		
		
		view.setLocation(0,0);
		getContentPane().add(view); 
			
		//size
		setSize(1000, 650);
		
		
		
		
	}*/
	
	public ClientGui getView(){return view;}
//	public ClientModel getModel(){return model;}
	
	
	
		public static void main(String[] args) {

	ClientController frame;
	ClientModel m = new ClientModel();
	
	ClientGui c = new ClientGui(m);
	
	
	frame = new ClientController (c);
	
	// Create window
	
	
	frame.setVisible( true );
	// Show window
}
}
