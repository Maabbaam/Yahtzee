import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ClientGui extends JPanel implements ActionListener{
	
	ClientModel model;

	
	Client cli;
	final int xShift = 380;
	final int yShift = 150;
	final int xColShift = 5;
	final int yColShift = 25;
	final int xTable = 112;
	final int xChart = 210;
	final int yChart = 25;
	final int xBut = 270;
	final String col [] = {"Round 1" };
	final String row [][] = { {" "}, {" "},{" "},{" "},{" "},{" "},{" "},{" "},	{" "},{" "},{" "},{" "}};
	
	int Roll3;
	
	//JComponents
	 JButton D1,D2,D3,D4,D5,rollButton;
	 JButton[] dic;
	 
	JTextArea box;
	JTextField[] chart;
	JButton[] cBut;
	JLabel label,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13,label14;
	JTable table;
	
	
	

	//get Methods
	public JButton getRollButton() {return rollButton; }
	public JButton getD1() {return D1;}
	public JButton getD2() {return D2;}
	public JButton getD3() {return D3;}
	public JButton getD4() {return D4;}
	public JButton getD5() {return D5;} 
		
	public JTextArea getBox(){return box;}
	public JTextField[] getChart(){return chart;}
	
	public JTable getTable(){return table;}
	public Client getC(){return cli;}
	
	
	public class editT extends DefaultTableModel {
   
   		private boolean[][] editable_cells; // 2d array to represent rows and columns
	
	    
	    
	    
	    
	    public editT(int rows, int cols) { // constructor
	        
	        super(rows, cols);
	        this.editable_cells = new boolean[rows][cols];
	    }
	
	    
	    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
	        
	        return this.editable_cells[row][column];
	    
	    }
	
	    public void setCellEditable(int row, int col, boolean value) {
	        
	        this.editable_cells[row][col] = value; // set cell true/false
	        this.fireTableCellUpdated(row, col);
	   
	    }




}// end of editT
	
	public ClientGui(ClientModel m){
	
	
		dic = new JButton [5];
	
		Roll3 = 0;
		model = m;
		setLayout(null); 
			
		cBut = new JButton[14];
		for(int i = 0; i < 14; i++){
			cBut[i] = new JButton("Enter");
			cBut[i].setLocation(xBut, 48 + (i * yChart));
			cBut[i].setSize(80,20);
			cBut[i].addActionListener(this);
			this.add(cBut[i]);
			
			
		}	
			
		chart = new JTextField[14];
		for(int i = 0; i < 14; i++){
			chart[i] = new JTextField();
			chart[i].setLocation(xChart, 48 + (i * yChart));
			chart[i].setSize(50,20);
			this.add(chart[i]);
						
			
		}
			
		JButton rollButton = new JButton("Roll Dice");
		rollButton.setLocation(320 + xShift, 400 + yShift);
		rollButton.setSize(100,25);
		rollButton.setEnabled(true);
		add(rollButton); 
		
		
		
		// Controller Methods
		rollButton.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) 
				{	
					Roll3++;
			
			
				if (Roll3 >= 3){
					rollButton.setEnabled(false);
					return ;
					
				}
					int count = 0;
					int j = 0;
					int rice[] = new int []{5,5,5,5,5};
					
					
					for (int i = 0; i < 5; i++){
						
						if (dic[i].isEnabled() == false ){
							count++;
							rice[j] = i;
							j++;
						}
						
					}
					
					if(count == 5){
						
						//All disabled
						update();
						return;
						
					}
					else if (count == 4){
						// 4 are disabled use reRoll1Dice()
						
						model.reRoll1Di(rice[0],rice[1],rice[2],rice[3]);
						update();
						return;
						
					}
					
					else if (count == 3){
						
						model.reRoll2Dice(rice[0],rice[1],rice[2]);
						update();
						return;
						
					}
					
					else if (count == 2){
						
						model.reRoll3Dice(rice[0],rice[1]);
						update();
						return;
						
		
					}
					
					else if (count == 1){
						
						
						model.reRoll4Dice(rice[0]);
						update();
						return;
					}
					
					else if (count == 0){
					
					model.rollAllDice();
					update();
					return;
					}
			
			else{
				
				System.out.println("THIS should never reaEEEEEEEEEEEEEEEEEEEEEEEEEEEEEch");
			}
				update();}}); 
		
			
			
			
		D3 = new JButton("3");
		D3.setLocation(317 + xShift, 335 + yShift);
		D3.setSize(55,55);
	
		D3.setEnabled(true);
		this.add(D3); 
		dic[2] = D3;
			
		D3.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) 
				{
				D3.setEnabled(false);
				update();}}); 
		
			
		D2 = new JButton("2");
		D2.setLocation(237 + xShift, 335 + yShift);
		D2.setSize(55,55);
		this.add(D2); 
		dic[1] = D2;
		
				
		D2.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) 
				{
				D2.setEnabled(false);
				update();}}); 
			
	    D1 = new JButton("1");
		D1.setLocation(157 + xShift, 335 + yShift);
		D1.setSize(55,55);
		this.add(D1); 
		dic[0] = D1;
		
				
		D1.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) 
				{
				D1.setEnabled(false);
				update();}}); 
			
		D4 = new JButton("4");
		D4.setLocation(397 + xShift, 335 + yShift);
		D4.setSize(55,55);
		this.add(D4); 
		dic[3] = D4;
		
				
		D4.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) 
				{
				D4.setEnabled(false);
				update();}}); 
			
		D5 = new JButton("5");
		D5.setLocation(477 + xShift, 335 + yShift);
		D5.setSize(55,55);
		this.add(D5); 
		dic[4] = D5;
		
				
		D5.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) 
				{
				D5.setEnabled(false);
				update();}}); 
			
		box = new JTextArea();
		box.setLocation(157 + xShift, 50);
		box.setSize(400,400);
		box.setEnabled(true);
		box.setEditable(false);
		this.add(box);
			
		table = new JTable(row,col);
		editT eTable = new editT(15, 1); 
			
		eTable.setCellEditable(3,0,false);
		
		table.setModel(eTable);
		//table = new JTable(row,col);
		table.setLocation(400,50);
		table.setSize(30,193);
		
		
		
	
		
		
		 label = new JLabel("Ones");
		label.setLocation (xTable,46 );
		label.setSize(50,20);
		this.add(label);
		
		 label2 = new JLabel("Twos");
		label2.setLocation (xTable,48 + yColShift  );
		label2.setSize(50,20);
		this.add(label2);
		
		 label3 = new JLabel("Threes");
		label3.setLocation (xTable,48 + (yColShift * 2 ));
		label3.setSize(50,20);
		this.add(label3);
		
		 label4 = new JLabel("Fours");
		label4.setLocation (xTable,48 + (yColShift*3 ));
		label4.setSize(50,20);
		this.add(label4);
		
		 label5 = new JLabel("Fives");
		label5.setLocation (xTable,48 + (yColShift *4)  );
		label5.setSize(50,20);
		this.add(label5);
		
		 label6 = new JLabel("Sixes");
		label6.setLocation (xTable,48 + (yColShift * 5)  );
		label6.setSize(50,20);
		this.add(label6);
		
		
		 label7= new JLabel("Bonus");
		label7.setLocation (xTable,48 + (yColShift * 6)  );
		label7.setSize(50,20);
		this.add(label7);
		
		 label8= new JLabel("Three of a Kind");
		label8.setLocation (xTable,48 + (yColShift * 7)  );
		label8.setSize(90,20);
		this.add(label8);
		
		 label9= new JLabel("Four of a Kind");
		label9.setLocation (xTable,48 + (yColShift * 8)  );
		label9.setSize(90,20);
		this.add(label9);
		
		 label10= new JLabel("Full House");
		label10.setLocation (xTable,48 + (yColShift * 9)  );
		label10.setSize(90,20);
		this.add(label10);
		
		 label11= new JLabel("Small Straight");
		label11.setLocation (xTable,48 + (yColShift * 10)  );
		label11.setSize(90,20);
		this.add(label11);
		
		 label12= new JLabel("Large Straight");
		label12.setLocation (xTable,48 + (yColShift * 11)  );
		label12.setSize(90,20);
		this.add(label12);
		
		 label13= new JLabel("Yahtzee");
		label13.setLocation (xTable,48 + (yColShift * 12)  );
		label13.setSize(50,20);
		this.add(label13);
		
		 label14= new JLabel("Grand Total");
		label14.setLocation (xTable,48 + (yColShift * 13)  );
		label14.setSize(70,20);
		this.add(label14);
		
		cli = new Client("localhost", 1500, this);
			// test if we can start the Client
		if(!cli.start()) FailedToConnectServer();
		
				
		
		update();
		
		}
		
		
		public void actionPerformed(ActionEvent e) { 
			//System.out.print("ASDFASDFAFAF");
			fun(e);
			
			int l = 0;
			String data = "0";
			
			
				for (int i = 0; i < 14; i++)
			{
				if (e.getSource() == cBut[i]){
					
				
					l = i;
					 data = cBut[i].getText();
					break;
				}
			}
			Message m = new Message("t",l,data);
			
			
			cli.sendData(m);				
		
					
		}
		
		public void fun(ActionEvent e){
		
			JButton b;
			int j =0;
			for (int i = 0; i < 14; i++)
			{
				if (e.getSource() == cBut[i]){
					
					b = cBut[i];
					j = i;
					break;
				}
			}
			
		if(chart[j].getText().isEmpty()) chart[j].setText("0");
		
		chart[j].setEditable(false);
		}
			
		public void fun(int j, String d){
	
		
		rollButton.setEnabled(false);
		
		if(chart[j].getText().isEmpty()) chart[j].setText("0");
		else chart[j].setText(d);
		
		
	
		chart[j].setEditable(false);
			
		}
		
		public void PlayerConnect(){
			
			
			//box.a
			update();
		}
		
		public void FailedToConnectServer(){
			
			append("Unable to Connect");
		
			
			
		}
		
		public void append(String str) {
		box.append(str +  "\n");
		box.setCaretPosition(box.getText().length() - 1);
		update();
	}
		
		public void update(){
		
			
			String s = Integer.toString(model.getRoll()[0]);
			D1.setText(s);
			
			s = Integer.toString(model.getRoll()[1]);
			D2.setText(s);
			
			s = Integer.toString(model.getRoll()[2]);
			D3.setText(s);
			
			
			s = Integer.toString(model.getRoll()[3]);
			D4.setText(s);
			
			s = Integer.toString(model.getRoll()[4]);
			D5.setText(s);
			
			for(int i = 0; i < 14; i++){
				
				if (chart[i].getText() == null ){
					
					chart[i].setEditable(false);
					
				}
			}		
			
			
		}
		
		
			
}

		
		
	
	
	
	