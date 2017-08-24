package chess;

public class Valid_Chess_Moves {
	// THis class deals with error checking
	chess_linked curP;//current piece
	
	int curTurn;
	
	boolean enPP = false; //en poisant possible
	

	
	public void setEP(){
		enPP = true;
		
	}
	
	public void removeEP(){
		enPP = false;
		
	}

	public boolean out_of_bounds(int x, int y, int x1, int y1){
		// this will check if move is out of bounds

		boolean validator  = true;
		if((x>7)||(y>7)||(x<0)||(y<0)||(x1>7)||(y1>7)||(x1<0)||(y1<0)){
			validator = false;
		//	System.out.println("Out of bounds");
		}

		return validator;
	}

	public boolean same_color(int x, int y, int x1, int y1){
		// this will check if same colors
		
		boolean validator = true;
		int c1 = curP.get_piece(x, y).getColour();
		int c2;
		if(curP.get_piece(x1, y1)==null){
			c2 = 3;
		}else{
			c2=curP.get_piece(x1, y1).getColour();	
		}


		if(c1==c2){
			validator = false;
		//	System.out.println("Can't Kill the same colour");
		}

		return validator;
	}

	public boolean no_piece_chosen(int x, int y){
		// this will check if no piece is chosen
		boolean validator = true;
		if(curP.get_piece(x, y)==null){
			validator = false;
			//System.out.println("No piece selected");
		}		
		return  validator;
	}


	public void pass_link(chess_linked load){
		curP = load;
	}



	public int turnCheck(int x, int y){
		int colr = 3;
		if(curP.get_piece(x, y)!=null){
			colr  = curP.get_piece(x, y).getColour();
		}

		return colr;


	}
	
	

	
	
	public boolean kingmover(int color){ // make this a boolean
		// get king location
		int x; int y;//initial moves
		int x1=0; int y1=0;//possible moves
		//get king values
		if(color ==1){
			//black
			x = curP.get_piece_on_c(6, 0).getX();
			y = curP.get_piece_on_c(6, 0).getY();
		}else{
			//white
			x = curP.get_piece_on_c(6, 1).getX();
			y = curP.get_piece_on_c(6, 1).getY();
		}
	boolean validMove = false;
		
		for(int i = 0; i<8;i++){
			
		switch(i){
		case 0:
			x1 = x-1;
			y1 = y+1;
			break;
		case 1:
			x1 = x;
			y1 = y+1;
			break;
		case 2:
			x1 = x+1;
			y1 = y+1;
			break;
		case 3:
			x1 = x-1;
			y1 = y;
			break;
		case 4:
			x1 = x+1;
			y1 = y;
			break;
		case 5:
			x1 = x-1;
			y1 = y-1;
			break;
		case 6:
			x1 = x;
			y1 = y-1;
			break;
		case 7:
			x1 = x+1;
			y1 = y-1;
			break;
		
		} // end of switch
		//check valid move	
		if(valid_move(x, y, x1, y1,0)==true){
			//then we proceed means the king is able to move
			//we check if any of the opposing team can kill this	
			if(can_go_there(color,x1,y1)==false){
				// there is no piece of the opposite can go there
				validMove = true;
				break;//break loops
			}	
		
		}
		
		}	
		
		return validMove;
		//return
	}
	
	
	
	
	
	
	public boolean block(int kx, int ky, int x, int y,int color){
		boolean val  = false;
		
		
		int op_col;
		if(color==1){
			op_col=0;
		}else{
			op_col=1;
		}
		//8 types of conditions
		
		int cx = x-kx; //change in x
		int cy = y-ky; // change in y
		
		if(cx  == 0){
			// then it is vertical
			if(cy>0){
				// we want to get from x to x1. 

				for(int i = ky+1; i<y;i++){
					
					if(can_go_there(color,x,i)){
						// there is bloakcing piece
						
						if(can_go_there(op_col,kx,ky)==false){
							
							//no one can reach the king
							val = true;
							break;
						}
					}
				}
			}else{


				for(int i = ky-1; i>y;i--){
					//some if
					if(can_go_there(color,x,i)){
						// there is bloakcing piece
						
						if(can_go_there(op_col,kx,ky)==false){
							
							//no one can reach the king
							val = true;
							break;
						}
					}
					
				}
			}
			
		}
		
		if(cy  == 0){
			// check horizonatal
		
			
			
			
			
			if(cx>0){
				// we want to get from x to x1. 

				for(int i = kx+1; i<x;i++){
					if(can_go_there(color,x,i)){
						// there is bloakcing piece
						
						if(can_go_there(op_col,kx,ky)==false){
							
							//no one can reach the king
							val = true;
							break;
						}
					}

				}
			}else{


				for(int i = kx-1; i>x;i--){
					if(can_go_there(color,x,i)){
						// there is bloakcing piece
						
						if(can_go_there(op_col,kx,ky)==false){
							
							//no one can reach the king
							val = true;
							break;
						}
					}

				}
			}		
			
		}
		
		
		
		
		if(cx>0&&cy>0){

			for (int i = 1;i<cx;i++){

				
				
				
				if(can_go_there(color,kx+i,ky+i)){
					// there is bloakcing piece
					
					if(can_go_there(op_col,kx,ky)==false){
						
						//no one can reach the king
						val = true;
						break;
					}
				}

			}


		}
		//second case
		if(cx>0&&cy<0){

			for (int i = 1;i<cx;i++){

				
				if(can_go_there(color,kx+i,ky-i)){
					// there is bloakcing piece
					
					if(can_go_there(op_col,kx,ky)==false){
						
						//no one can reach the king
						val = true;
						break;
					}
				}
				
	

			}

		}
		//third casew
		if(cx<0&&cy>0){

			for (int i = 1;i<cy;i++){

				
				
				if(can_go_there(color,x-i,y+i)){
					// there is bloakcing piece
					
					if(can_go_there(op_col,kx,ky)==false){
						
						//no one can reach the king
						val = true;
						break;
					}
				}

			}


		}
		//last case
		if(cx<0&&cy<0){

			for (int i = -1;i>cx;i--){

				
				
				if(can_go_there(color,kx+i,ky+i)){
					// there is bloakcing piece
					
					if(can_go_there(op_col,kx,ky)==false){
						
						//no one can reach the king
						val = true;
						break;
					}
				}
				
			}


		}
		
		
		
		
		return val;
		
		
	}
	
	
	public boolean test_3(int color){
		//blocking test	
		boolean val = true;
		int x1;
		int y1;
		int kx; int ky;
		
		
		// get king coord
		if(color ==1){
			//black
		
			kx = curP.get_piece_on_c(6, 0).getX();
			ky = curP.get_piece_on_c(6, 0).getY();
		}else{
			//white
		
			kx = curP.get_piece_on_c(6, 1).getX();
			ky = curP.get_piece_on_c(6, 1).getY();
		}
		
		
		for(int i = 0; i<ac; i++){
			x1 = checkArray[i][0];
			y1 = checkArray[i][1];
			// find distance from this pieces to king and return if anypiece can block it 
			 if(block(kx,ky,x1,y1,color)==false){
				 val = false;
			 }
			
		}
		
		return val;
	}
	
	
	public boolean checkMate(int color){
		// checks if black is checkmate		
		//if color is 1 we are killing black
		//if 0 we are killing white
		int kx,ky;
		int op_col;
		if(color ==1){
			//black
			op_col=0;
			kx = curP.get_piece_on_c(6, 0).getX();
			ky = curP.get_piece_on_c(6, 0).getY();
		}else{
			//white
			op_col=1;
			kx = curP.get_piece_on_c(6, 1).getX();
			ky = curP.get_piece_on_c(6, 1).getY();
		}
		
		//color is OC
		int x1;// coordinates
		int y1;//coordinates
		boolean val = false;
		
		boolean test_1 = kingmover(color); // king test
		boolean test_2 = true;
		boolean test_3 = test_3(color); // blocking test
		
		
		for(int i = 0; i<ac; i++){
			x1 = checkArray[i][0];
			y1 = checkArray[i][1];
			//so we pretty much traverse until we find all the pieces that have this in check
			// we must check every one									
			if(can_go_there(color, x1,y1)==false){				
				test_2 = false;				
			}else{								
					
				if(can_go_there(op_col,kx,ky)==true){					
						//one can reach the king
						test_2 = false;					
					}
				
							
			}
						
		}
		 test_1=kingmover(color); // this must be true
		 
		 
		 if(test_2==true||test_1==true||test_3==true){
			 val = true;
		 }
		 
		 
		 
 if(test_1){
			//System.out.println("Test_1 passed"); 
		 }
		 if(test_2){
			// System.out.println("Test_2 passed");
		 }
 if(test_3){
	// System.out.println("Test_3 passed");
		 }
		 return val; // this will determin if there is a check mate
		 //false if it a checkmate
		 
	}
	
	
	
	public boolean can_go_there(int color, int x1, int y1){
		boolean chek = false;
		int o_c;
		
		if(color ==1){
			o_c=0;
		}else{
			o_c=1;
		}		
		int x;int y;
		int type;
		//finad all pices that are 0;

		for(int i = 0;i<8;i++){
			for(int j = 0;j<8;j++){
				if(curP.get_piece(j, i)!=null){
					//hey, there is a pice here
					if(curP.get_piece(j,i).getColour()==o_c){
						//hey we have a white
						type = curP.get_piece(j, i).getPiece_type();
						x = curP.get_piece_on_c(type, o_c).getX();
						y = curP.get_piece_on_c(type, o_c).getY();
						if(checkPice_move(type,x,y,x1,y1)==true){
							chek = true;
							break;
						}
					}

				}
			}
		}
	return chek;
	}
	
	
int checkArray[][] = new int [2][16]; //max16 pieces of opponent 
int ac;//array countl	
	public boolean checkCheckB(){
		ac = 0; //resets ac
		boolean chek = false;
		int x1 = curP.get_piece_on_c(6, 1).getX();
		int y1 = curP.get_piece_on_c(6, 1).getY();
		int o_c = 0;
		int x;int y;
		int type;
		//finad all pices that are 0;

		for(int i = 0;i<8;i++){
			for(int j = 0;j<8;j++){
				if(curP.get_piece(j, i)!=null){
					//hey, there is a pice here
					if(curP.get_piece(j,i).getColour()==o_c){
						//hey we have a white
						type = curP.get_piece(j, i).getPiece_type();
						x = curP.get_piece_on_c(type, o_c).getX();
						y = curP.get_piece_on_c(type, o_c).getY();

						if(checkPice_move(type,x,y,x1,y1)==true){
							chek = true;
							checkArray[ac][0] = x1;
							checkArray[ac][1] = y1;
							ac++;
						}


					}

				}
			}
		}

		//traverse though the linked lsit for every piece adn move it to x1, xy;
		return chek;
	}

	public boolean checkCheckW(){

		boolean chek = false;

		int y1 = curP.get_piece_on_c(6, 0).getY();
		int x1 = curP.get_piece_on_c(6, 0).getX();
		int o_c = 1;
		int x;int y;
		int type;
		//finad all pices that are 0;

		for(int i = 0;i<8;i++){
			for(int j = 0;j<8;j++){
				if(curP.get_piece(j, i)!=null){
					//hey, there is a pice here
					if(curP.get_piece(j,i).getColour()==o_c){
						//hey we have a white
						type = curP.get_piece(j, i).getPiece_type();
						x = curP.get_piece_on_c(type, o_c).getX();
						y = curP.get_piece_on_c(type, o_c).getY();

						if(checkPice_move(type,x,y,x1,y1)==true){
							
							chek = true;
							checkArray[ac][0] = x1;
							checkArray[ac][1] = y1;
							ac++;
						}


					}

				}
			}
		}


		//traverse though the linked lsit for every piece adn move it to x1, xy;



		return chek;


	}

	
	
	

	public boolean valid_move(int x, int y, int x1, int y1, int turncur){
		// based on the pice about to be moved
		
		boolean val=true;
		//check if pice are chose

		enPP = false;
		curTurn=turncur;
		
		if(out_of_bounds(x,y,x1,y1)==false){
			val = false;
		}else{
			//pice is chosen
			//we check out of bounds 
			if(no_piece_chosen(x,y)==false){
				val = false;
			}else{
				//withing bounds
				//so we check same colou
				if(same_color(x,y,x1,y1)==false){
					val = false;
				}else{

					int piecechose = curP.get_piece(x, y).getPiece_type();


					if(checkPice_move(piecechose,x,y,x1,y1)==false){
						val = false;
					}

				}
			}


		}

		return val;
	}



	public boolean checkKnight(int x, int y, int x1, int y1){
		boolean val=true;
		int xmove = x-x1;//must be zero
		int ymove = y1-y;//
		xmove = xmove *xmove ; // square it
		ymove = ymove *ymove ; // square it


	//	System.out.println("x"+xmove);
		//System.out.println("y"+ymove);

		/*if(xmove!=1 || xmove!=4||ymove!=1||ymove!=4){
			System.out.println("error 1");
			val = false;
		}*/

		if (ymove > 4 || xmove > 4) {
			val = false;
		}

		if (ymove==0 || xmove ==0){
			val = false;
		}

		if(xmove==1&&ymove!=4){
		//	System.out.println("error 2");
			val=false;
		}

		if(xmove==4&&ymove!=1){
			//System.out.println("error 3");
			val=false;
		}
		if(ymove==4&&xmove!=1){
			//System.out.println("error 4");
			val=false;
		}
		if(ymove==1&&xmove!=4){
			//System.out.println("error 5");
			val=false;
		}

		return val;
	}



	public boolean king_move(int x, int y, int x1, int y1){
		boolean validator = true;

		int xmove = x-x1;//must be zero
		int ymove = y1-y;//
		xmove = xmove *xmove ; // square it
		ymove = ymove *ymove ; // square it


		if(xmove ==0 && ymove==0){
			//hasn't moved
			validator = false;
		}
		if(xmove >1 || ymove>1){
			//move grater than it's hizon
			validator = false;
		}

		
	
		
		return validator;
	}


	public boolean rook_move(int x, int y, int x1, int y1){
		boolean validator = true;

		int xmove = x1-x;//must be zero
		int ymove = y1-y;//
		xmove = xmove*xmove;
		ymove = ymove*ymove;


		//both can't be zero
		// once must be at zero
	//	System.out.println("here");
		if(xmove ==0 && ymove==0){
			//hasn't moved
			validator = false;
		}

		if (xmove > 0 && ymove > 0){
			validator = false;
		}




		return validator;
	}

	public boolean QueenMove(int x, int y, int x1, int y1){

		boolean hor = rook_move(x,y,x1,y1);
		boolean vert = checkBishup(x,y,x1,y1);

		boolean val = true;

		int xmove = x1 - x;
		int ymove = y1 - y;
		xmove = xmove*xmove;
		ymove = ymove*ymove;

		if (xmove > 0 && ymove > 0){
			if (!checkBlockDn(x,x1,y,y1)){
				val = false;
			}
		} else {
			if (ymove > 0){
				if (!ccheckBlockV(y,y1,x)){
					val = false;
				}
			} else {
				if (xmove > 0){
					if (!ccheckBlockH(x,x1,y)){
						val = false;
					}
				}
			}
		}


		if(hor==false&&vert==false){
			val = false;
		}





		return val;

	}

	public boolean checkBlockDn(int x, int x1, int y, int y1){


		boolean val = true;
		int change_x = x1-x;
		int change_y =y1-y;


		//System.out.println("x" + " " + change_x);
		//System.out.println("y" + " " + change_y);
		//first case
		if(change_x>0&&change_y>0){

			for (int i = 1;i<change_x;i++){

				if(curP.get_piece(x+i,y+i)!=null){
			//		System.out.println("error 1");
					val = false;
				}

			}


		}
		//second case
		if(change_x>0&&change_y<0){

			for (int i = 1;i<change_x;i++){

				if(curP.get_piece(x+i,y-i)!=null){
					//System.out.println("error 2");
					val = false;
				}

			}

		}
		//third casew
		if(change_x<0&&change_y>0){

			for (int i = 1;i<change_y;i++){

				if(curP.get_piece(x-i,y+i)!=null){
			//		System.out.println("error 3");
					val = false;
				}

			}


		}
		//last case
		if(change_x<0&&change_y<0){

			for (int i = -1;i>change_x;i--){

				if(curP.get_piece(x+i,y+i)!=null){
					//System.out.println("error 4");
					val = false;
				}

			}


		}



		return val;



	}

	public boolean ccheckBlockV(int y,int y1,int x){

		boolean val = true;
		int change = y1-y;

		if(change>0){
			// we want to get from x to x1. 

			for(int i = y+1; i<y1;i++){
				if(curP.get_piece(x, i)!=null){
					val = false;
					break;
				}
			}
		}else{

			for(int i = y-1; i>y1;i--){
				if(curP.get_piece(x,i)!=null){
					val = false;
					break;
				}
			}
		}
		return val;
	}

	public boolean ccheckBlockH(int x,int x1,int y){
		boolean val = true;
		int change = x1-x;



		if(change>0){
			// we want to get from x to x1. 

			for(int i = x+1; i<x1;i++){
				if(curP.get_piece(i, y)!=null){
					val = false;
					break;
				}

			}
		}else{


			for(int i = x-1; i>x1;i--){
				if(curP.get_piece(i, y)!=null){
					val = false;
					break;
				}

			}
		}

		return val;



	}


	
	public boolean unpp(int x, int y,int choice){
		boolean val  = false;
		int cx;
		if(choice==1){
			cx = 1;
		}else{
			cx = -1;
		}
		
	
		if(curP.get_piece(x+cx, y)!=null){
			
			if(curP.get_piece(x+cx, y).getPiece_type()==1){
				
				// it is a pawn 
				int turnValidator = curTurn - curP.get_piece(x+cx, y).get_turn_moved();
				
				if(turnValidator==1&&(curP.get_piece(x, y).getColour()!=curP.get_piece(x+cx, y).getColour())){
					//was moved last turn
					val = true;
					setEP();
					
				}
				
			}
			
		}
		
		
		// we are proving if it true
		return val;
	}
	
	
	public boolean Pawn_Move(int x, int y, int x1, int y1){
		boolean validator = true;
		int color = curP.get_piece(x, y).getColour();
		int turn = curP.get_piece(x, y).getTurn();
		int xmove = x1-x;//must be zero
		int ymove = y1-y;
		//can't go backwards
		//can move up 2 on the first move

		int sqr = ymove*ymove;
		int sqrx = xmove*xmove;
		boolean true_way = true;

		if(color==1){
			//must be pos
			if(ymove<0){
				validator = false;
				true_way = false;
			}

		}else{

			if(ymove>0){
				validator = false;
				true_way= false;
			}
		}

		if(xmove ==0 && ymove==0){
			// did not move
			validator = false;
		}

		if(turn!=0&&sqr>1){
			//move more then one on the first turn
			validator = false;

		}
		if(sqr>4){

			//for anything 
			validator = false;
		}

		if (sqrx > 1) {
			validator = false;
		}

		if (sqrx == 1) {
			if (sqr != 1){
				validator = false;
			} else {
				if  (curP.get_piece(x1, y1)==null) {
					
					validator = false;
				}										
			}
		}
		
		
		//unpossant override.
	
		if(sqr==1&&sqrx==1&&curP.get_piece(x1, y1)==null&&true_way==true){
			//we are moving diagnoal here=
			//right now this should yield a false
			if(ymove<0){
				// white move sup to black
				
				if(xmove<0){
					
				
					validator = unpp(x,y,2);
					
				}else{
					validator = unpp(x,y,1);
				}
				
				
			}else{
				//change in y = positive
				
				if(xmove<0){
					validator = unpp(x,y,2);
				}else{
					validator = unpp(x,y,1);
				}
			}
			
			
			
			
		}
			
		return validator;
	}


	public boolean checkBishup(int x, int y, int x1, int y1){
		boolean val = true;
		//if slope is not one, then it does not work;
		int xmove = x-x1;//must be zero
		int ymove = y1-y;
		// square both of them to remove the (-)

		int d1= xmove*xmove;
		int d2= ymove*ymove;
		if(xmove ==0 && ymove==0){
			//hasn't moved
			val = false;
		}		

		if(ymove!=0||xmove!=0){
		//	System.out.println("does it work?");
		//	System.out.println("d1 "+d1+"d2 "+d2);

			if(d1!=d2){
				//slope must be 1 thus y,must ==x
				val = false;
			}			
		}
		return val;

	}

	
public boolean castle_check(int x, int x1, int y, int y1){
	
	boolean val  = false;
	
	//special cases for rook
			//both king and rook must exist
			//must be the same colour
			// must have not be moves from the board
		if(curP.get_piece(x, y)!=null&&curP.get_piece(x1, y1)!=null){
			//both  pioeces must exist
			
			if(curP.get_piece(x, y).getPiece_type()==6&&curP.get_piece(x1, y1).getPiece_type()==2){
				//both  muyst be king and rook
				if(curP.get_piece(x, y).getTurn()==0&&curP.get_piece(x1, y1).getTurn()==0){
					//both  must have not moved
					if(curP.get_piece(x, y).getColour()==curP.get_piece(x1, y1).getColour()){
						//both  must be the same colour
						val = true;
						
						
					}
					
					
				}
				
			}
			
			
			
		}
		
	return val;
}
	
	public boolean checkPice_move(int type,int x, int y, int x1,int y1){
		//checsk if the move is even valid based on the piece
		boolean val = true;
		int cx = x-x1;
		int cy = y-y1;
		cx = cx*cx;
		cy = cy*cy;
		switch(type){
		case 1:
			//pawn
			if(ccheckBlockV(y,y1,x)==false){
		//		System.out.println("Pawn_error: horrizonal");
				val = false;
			}else{
				if(Pawn_Move(x,y,x1,y1)==false){
			//		System.out.println("Pawn_error: pawn move");
					val = false;
				}
			}
			break;
		case 2:
			//rook

			if(cy>0){
			//	System.out.println("check 1");
				if(ccheckBlockV(y,y1,x)==false){
				//	System.out.println("rook_error: Vert");
					val = false;
				}else{
					if(rook_move(x,y,x1,y1)==false){
					//	System.out.println("rook_error: rook move");
						val = false;
					}
				}
			}else{
				if(cx>0){
				//	System.out.println("check 2");
					if(ccheckBlockH(x,x1,y)==false){
					//	System.out.println("rook_error: hor");
						val = false;
					}else{
						if(rook_move(x,y,x1,y1)==false){
					//		System.out.println("rook_error: rook move");
							val = false;
						}
					}
				}

			}
			break;
		case 3:
			//knight
			if(checkKnight(x,y,x1,y1)==false){
			//	System.out.println("knight error: knight move");
				val = false;
			}
			break;
		case 4:
			//bissup
		//	System.out.println("checking bissup");
			if(checkBlockDn(x,x1,y,y1)==false){
			//	System.out.println("Bisshup error: diagn");
				val = false;
			}else{
				if(checkBishup(x,y,x1,y1)==false){
				//	System.out.println("Bisshup error: bishp move");
					val = false;
				}	

			}	
			break;
		case 5:
			//queeen			
			if(	QueenMove(x,y,x1,y1)==false){
				//System.out.println("queen error: queen move");
				val = false;
			}	
			break;
		case 6:

			if(king_move(x,y,x1,y1)==false){
				//System.out.println("King error: king move");
				val = false;
			}


			//kking		
			break;	
		}		
		return val;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
