package chess;

public class chess_p {

	//this class with represent a chess piece.
	
	chess_p next;
	int x; // place on board
	int turn_moved;
	int y;//place on board
	int color; // color
	int piece_type; // type...pawn,knight,queen etc.
	int chess_value;// value of piece
	int turn = 0;//turn...aka pawn turns
	public chess_p(int xx, int yy, int p_colour, int value, int p_type ){
		next = null;
		x = xx;
		y = yy;
		color  = p_colour;
		chess_value = value;
		piece_type = p_type;
		turn_moved=0;
	}
	
	
//*****GETTERS******//
	public int getX(){
		return x; // gets x
	}
	public int get_turn_moved(){
		return turn_moved;
	}
	public int getY(){
		return y; //gets y
	}
	public int getColour(){
		return color; //gets color
	}
	public int getChess_value(){
		return chess_value; // gets chess value
	}
	public int getPiece_type(){
		return piece_type; // gets piece type
	}
	public int getTurn(){
		
		return turn; // gets piece type
	}
	
	
	//*****Setters******//
	
	public void set_coordinate(int xx, int yy){
		x = xx;
		y = yy;
	}
		
	public void setNext(chess_p nextPice){
	next = nextPice;
	}
	public void incTurn(){
		turn++;
		}
		
	public void set_current_turn(int ct){
		turn_moved = ct;
	}
	
	public void setTurn(int turnset){
		turn = turnset;
		}
	
}
