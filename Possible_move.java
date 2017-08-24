package chess;

public class Possible_move {

	int x;
	int y;
	int x1;
	int y1;
	
	
	public Possible_move(int xx, int yy, int xxx, int yyy){
		// set possible coordinates
		x = xx;
		y = yy;
		x1 = xxx;
		y1 = yyy;
		
	}
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getX1(){
		return x1;
	}
	public int getY1(){
		return y1;
	}
	
	
	
}
