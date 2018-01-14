import java.io.*;
import javax.swing.*;
import java.awt.*;
public class Message implements Serializable {

  private String msg;
  private int loc;
  private String data;
	
	public Message(String messg,int l, String d){
		
		msg = messg;
		loc = l;
		data = d;
		
	}
	
	public String getMsg(){ return msg;}

	public void setMsg(String s){msg = s;}
	public int getLoc(){ return loc;}
	public String getDat(){ return data;}
	
	
		

}// end of class Message
