package chess;

public class chess_linked {

	chess_p head;
	
	int b_count ;
	int w_count;
	
	//adds a piece
	public void add_piece(int xx, int yy, int p_colour, int value, int p_type){
		if(head==null){
			head = new chess_p(xx,yy,p_colour,value,p_type); // just testing
		}
		
		chess_p temp = new chess_p(xx,yy,p_colour,value,p_type);
		chess_p current = head; //pointers ::)
		
		if(current!=null){
			
			while(current.next!=null){
				current = current.next;
				
			}
			current.setNext(temp);
		}
		
		
		if(p_colour==1){
			b_count++;
		}else{
			w_count++;
		}
		
	}
	
	public chess_p get_piece(int xx, int yy){
		//returns piece at this point in the board
		int y; int x;
		chess_p temp = null;
		chess_p current;
		if(head!=null){
			current = head;
			while(current!=null){
				x = current.getX();
				y = current.getY();
				
				if(x==xx &&y==yy){
					temp  = current;
					break;
				}
				
				
				current = current.next;
			}
			
		}
		return temp;
		
	}
	
	public chess_p get_piece_on_c(int tt, int cc){
		//returns piece at this point in the board
		int t; int c;
		chess_p temp = null;
		chess_p current;
		if(head!=null){
			current = head;
			while(current!=null){
				t = current.getPiece_type();
				c = current.getColour();				
				if(t==tt &&c==cc){
					temp  = current;
					break;
				}							
				current = current.next;
			}
			
		}
		
		if(temp==null){
			System.out.println("nulllll");
		}
		return temp;
		
	}
	
	

	
	//removes piece from linked list based on x & y
	public void remove_piece(int xx, int yy, int colour){
		int y; int x; int c;
		int counter;
		if(colour==1){
			counter = b_count;
		}else{
			counter = w_count;
		}
		
		if(counter>=1){
			
		
		
		if(head!=null){
			
			chess_p current = head;
			
			
			while(current.next!=null){
				//find x, y, and color
				// remove it
				
				x = current.next.getX();
				y = current.next.getY();
				c = current.next.getColour();
				
				if(x == xx && y==yy && c == colour){
					current.setNext(current.next.next);
					break; // break from loop
				}
				current = current.next;
			}		
		}		
		//derement counter
		if(colour==1){
			b_count--;
		}else{
			w_count--;
		}
		
		}
	}
	
	
	//prints pieces
	public void print_pieces(){
		
		if(head!=null){
			chess_p current = head.next;
			while(current!=null){
				System.out.println(""+current.getX());
			current = current.next;
			}
			
		}
		
		
	}
	
	
	
}
