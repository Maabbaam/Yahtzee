import java.util.*;


public class ClientModel {
	
	private int[] aDie;
	private Die di;
	private int counter;
	
	public ClientModel(){
		
		aDie = new int[]{0,0,0,0,0};
		di = new Die();
		counter = 0;
		
		
		
		
	}// end of constructor
	
	public Die getDie(){return di;}
	public int[] getRoll(){return aDie;}
	
	
	public void rollAllDice(){
	
	for (int i = 0; i < 5; i++)
	{
		di.roll();
		aDie[i] = di.get();
		
		
	}
	counter++;
	}
	
	public void reRoll4Dice(int dice){
		
		for (int i = 0; i < 5; i++)
	{
		if( i != dice){
			di.roll();
			aDie[i] = di.get();
		}
	}// reroll4 dice
	counter++;
	}
	public void reRoll3Dice(int d1, int d2){
		
			for (int i = 0; i < 5; i++)
	{
		if( i != d1 && i !=d2 ){
			di.roll();
			aDie[i] = di.get();
		}
	}// reroll4 dice
	counter++;
		
		
	}
		
	public void reRoll2Dice(int d1, int d2,int d3){
		
			for (int i = 0; i < 5; i++)
	{
		if( i != d1 && i !=d2 && i != d3 ){
			di.roll();
			aDie[i] = di.get();
		}
	}// reroll4 dice
	counter++;
		
		
	}
	
		public void reRoll1Di(int d1, int d2,int d3,int d4){
		
			for (int i = 0; i < 5; i++)
	{
		if( i != d1 && i !=d2 && i != d3 && i!=d4){
			di.roll();
			aDie[i] = di.get();
		}
	}// reroll4 dice
	counter++;
		
		
	}
	

// Inner 
	
  class Die {
  private int value;
  private Random rand;

  public Die() {
    value = 0;
    rand = new Random();
  }

  public void roll() {
    value = 1 + rand.nextInt(6);
  }

  public int get() {
    return (value);
  }
  
  
}

public static void main(String[] args) {
	
	ClientModel m = new ClientModel();
	m.reRoll1Di(0,1,2,3);
	
	for (int i = 0;i < 5; i++){
		
		System.out.println(m.getRoll()[i]);
		
		
		
	}
	
}
	
	
}


