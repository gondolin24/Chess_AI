package chess;
import java.util.*;
public class set_of_pieces {
	
	
	
	Valid_Chess_Moves error_checker = new Valid_Chess_Moves(); //checks if moves are valid
	chess_linked current_pieces;
	Scanner kbrd = new Scanner(System.in);
	// arrayList
	ArrayList<Possible_move> PMA = new ArrayList<Possible_move>();
	int Gx;
	int Gy;
	int Gx1;
	int Gy1;
	int GlobalTurn;
	///false if colour not check
	//true is colour  check+
	boolean blackCheck;
	boolean whiteCheck;	
	public void Initate_pieces(){
		GlobalTurn = 0;
		current_pieces = new chess_linked();
		// place pawns
		current_pieces.add_piece(100,100,100,100,100); //head
		for(int i = 0; i<8;i++){
			//adding pawns
			current_pieces.add_piece(i, 6, 0, 100, 1);

		
			current_pieces.add_piece(i, 1, 1, 100, 1);
		}
							
		//place rooks	
		current_pieces.add_piece(0, 0, 1, 600, 2);
		current_pieces.add_piece(0, 7, 0, 600, 2);
		current_pieces.add_piece(7, 0, 1, 600, 2);
		current_pieces.add_piece(7, 7, 0, 600, 2);
		//place knights
		current_pieces.add_piece(1, 0, 1, 400, 3);
		current_pieces.add_piece(6, 0, 1, 400, 3);
		current_pieces.add_piece(1, 7, 0, 400, 3);
		current_pieces.add_piece(6, 7, 0, 400, 3);
		//place bishups
		current_pieces.add_piece(2, 0, 1, 400, 4);
		current_pieces.add_piece(5, 0, 1, 400, 4);
		current_pieces.add_piece(2, 7, 0, 400, 4);
		current_pieces.add_piece(5, 7, 0, 400, 4);
		//place queen
		current_pieces.add_piece(3, 7, 0, 1200, 5);
		current_pieces.add_piece(3, 0, 1, 1200, 5);
		//place kings
		current_pieces.add_piece(4, 7, 0, 10000, 6);
		current_pieces.add_piece(4, 0, 1, 10000, 6);
		blackCheck = false;// sety upo booleans
		whiteCheck = false;
		//current_pieces.remove_piece(0, 1, 1);
		 
		
	}

	public void printBoard(){
		System.out.println("");
		System.out.println("YX:0  1  2  3  4  5  6  7");
		for(int i = 0;i<8;i++){ // y
			System.out.print(""+i+" ");
			for(int j = 0;j<8;j++){ //x
				if(current_pieces.get_piece(j, i)==null){

					if(i%2==0){
						if(j%2==0){
							System.out.print(" . ");
						}else{
							System.out.print(" - ");
						}
					}else{

						if(j%2==0){
							System.out.print(" - ");
						}else{
							System.out.print(" . ");
						}


					}


				}else{
					System.out.print(" "+print_P(current_pieces.get_piece(j, i).getPiece_type(),current_pieces.get_piece(j, i).getColour())+" ");
				}

			}
			System.out.println("");
		}
	}
	
	public void castle_backTrack(int x, int x1, int y, int y1){
		//backtracks incase of check
		//x,y
		if(x==4&&x1==6&&y==7&&y1==7){
				//move rook
			move_piece(5,7,7,7);
			
		}
		if(x==4&&x1==2&&y==7&&y1==7){
			move_piece(3,7,0,7);
			
		}
		if(x==4&&x1==6&&y==0&&y1==0){
			move_piece(5,0,7,0);
			
		}
		if(x==4&&x1==2&&y==0&&y1==0){
			move_piece(3,0,0,0);
		}
		
	}
	
	
	public void castle_move(int x, int x1, int y, int y1){
		//x,y
		if(x==4&&x1==6&&y==7&&y1==7){
				//move rook
			move_piece(7,7,5,7);
			
		}
		if(x==4&&x1==2&&y==7&&y1==7){
			move_piece(0,7,3,7);
			
		}
		if(x==4&&x1==6&&y==0&&y1==0){
			move_piece(7,0,5,0);
			
		}
		if(x==4&&x1==2&&y==0&&y1==0){
			move_piece(0,0,3,0);
		}
		
	}
	
	
	public boolean special_case(int x, int x1, int y, int y1,int turn){
		boolean val = false;
		//x, and y1 must be king
		
		boolean check  = false;
		
		
		if(turn==0&&blackCheck!=true){
			check = true;
		}
		if(turn==1&&whiteCheck!=true){
			check = true;
		}
	
		if(check==true){
			
			if(x==4&&x1==6&&y==7&&y1==7){
				
				if(error_checker.castle_check(x, 7, y, 7)){	
					val = true;
				}		
				
			}
			if(x==4&&x1==2&&y==7&&y1==7){
				if(error_checker.castle_check(x, 0, y, 7)){	
					val = true;
				}
			}
			if(x==4&&x1==6&&y==0&&y1==0){
				if(error_checker.castle_check(x, 7, y, 0)){	
					val = true;
				}	
			}
			if(x==4&&x1==2&&y==0&&y1==0){
				if(error_checker.castle_check(x, 7, y, 0)){	
					val = true;
				}	
			}
			
			
		}
		//check if not in check			
		return val;
		
	}


int[] poind = new int[7];
public void enP_temp (int x1, int y){
	// makes temp of pawn
	
	poind[0] = x1;
	poind[1] = y;
	poind[2] = current_pieces.get_piece(x1, y).turn_moved;
	poind[3] = current_pieces.get_piece(x1, y).color;
	poind[4] = current_pieces.get_piece(x1, y).piece_type;
	poind[5] = current_pieces.get_piece(x1, y).chess_value;
	poind[6] = current_pieces.get_piece(x1, y).turn;
	
		
}
	
	public void enPois_backTrack(int x1, int y){
	
		current_pieces.add_piece(poind[0], poind[1], poind[3], poind[5], poind[4]);
		//current_pieces.get_piece(x1, y).set_current_turn(poind[2]);
		
	}
	
	
	public void enpois( int x1, int y){
		System.out.println("X"+x1+"Y"+y);
		current_pieces.remove_piece(x1, y,current_pieces.get_piece(x1, y).getColour());				
		// this will remove the pawn					
	}	
	public void possiblePawn(int x, int y){
		
		//so the pawn can only move 4 places
		// add to the array list			
		//check x,y+1
		if(error_checker.valid_move(x, y, x, y+1, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x,y+1));
			
		}
		//check x, y+2
		if(error_checker.valid_move(x, y, x, y+2, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x,y+2));
		}
		//check x+1,y+1
		if(error_checker.valid_move(x, y, x+1, y+1, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x+1,y+1));
		}
		//check x-1, y+1
		if(error_checker.valid_move(x, y, x-1, y+1, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x-1,y+1));
		}
	}
		
	public void possibleRook(int x, int y){
			
		for(int i=0;i<8;i++){
			// move horizontal
			if(error_checker.valid_move(x, y, i, y, GlobalTurn)){
				PMA.add(new Possible_move(x,y,i,y));
			}
			//move diagnal
			if(error_checker.valid_move(x, y, x, i, GlobalTurn)){
				PMA.add(new Possible_move(x,y,x,i));
			}
		}
		
		
	}	
	public void possibleKight(int x, int y){
		// knights have a specific case
		
		
		if(error_checker.valid_move(x, y, x+1, y-2, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x+1,y-2));
		}
		//check x, y+2
		if(error_checker.valid_move(x, y, x+2, y-1, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x+2,y-1));
		}
		//check x+1,y+1
		if(error_checker.valid_move(x, y, x+2, y+1, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x+2,y+1));
		}
		//check x-1, y+1
		if(error_checker.valid_move(x, y, x+1, y+2, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x+1,y+2));
		}
		
		
		
		
		
		if(error_checker.valid_move(x, y, x-1, y-2, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x-1,y-2));
		}
		//check x, y+2
		if(error_checker.valid_move(x, y, x-2, y-1, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x-2,y-1));
		}
		//check x+1,y+1
		if(error_checker.valid_move(x, y, x-2, y+1, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x-2,y+1));
		}
		//check x-1, y+1
		if(error_checker.valid_move(x, y, x-1, y+2, GlobalTurn)){
			PMA.add(new Possible_move(x,y,x-1,y+2));
		}
		
		
	}
	
	
	
	public void remove_non_valid(){
		
		// this will remove all non valid
	
	
		int x ;
		int y ;
		int y1 ;
		int x1 ;
		boolean der = false;
		for(int i = PMA.size()-1;i>=0;i--){
			
			x = PMA.get(i).getX();
			y = PMA.get(i).getY();
			y1 = PMA.get(i).getY1();
			x1 = PMA.get(i).getX1();
		
			bt.add(tempL());
			move_piece(x,y,x1,y1);
			error_checker.pass_link(current_pieces);//updates the board
			if(error_checker.checkCheckB()){
				der = true;		
			}	
			backtracklinked();	
			error_checker.pass_link(current_pieces);//updates the board

			if(der){
				PMA.remove(i);
			}
			der = false;
		}
	
		
			
			
		}
		
		

	
	public void possibleBissup(int x, int y){
		
		//4 conditions
		
		for(int i = 0;i<8;i++){
			
			if(error_checker.valid_move(x, y, x+i, y+i, GlobalTurn)){
				PMA.add(new Possible_move(x,y,x+i,y+i));
			}
			
			if(error_checker.valid_move(x, y, x+i, y-i, GlobalTurn)){
				PMA.add(new Possible_move(x,y,x+i,y-i));
			}
			if(error_checker.valid_move(x, y, x-i, y-i, GlobalTurn)){
				PMA.add(new Possible_move(x,y,x-i,y-i));
			}
			if(error_checker.valid_move(x, y, x-i, y+i, GlobalTurn)){
				PMA.add(new Possible_move(x,y,x-i,y+i));
			}			
		}
	
	}
	
	public void possibeKing(int x, int y){
		int i = -1;
		int j = -1;
		while(i<2){
			
			while(j<2){
				if(error_checker.valid_move(x, y, x+j, y+i, GlobalTurn)){
					PMA.add(new Possible_move(x,y,x+j,y+i));
				}
				j++;
			}
			i++;
		}
		
	}



	public void can_move(int p_type, int x, int y){
		
		switch(p_type){
		case 1: //pawn
			possiblePawn(x, y);
			break;
		case 2: //rook
			possibleRook(x,y);
			break;
		case 3: //knight
			possibleKight(x,y);
			break;
		case 4: //bisup
			possibleBissup(x,y);
			break;
		case 5: //queen
			possibleRook(x,y);
			possibleBissup(x,y);
			break;
			
		case 6: //king
			 possibeKing(x,y);
			break;
		}
		
		}
	
	public void get_possible_moves(int turn){
		//1 = ai--aka white
		PMA.clear();
		//System.out.println("--");
	//	printBoard();
	int p_t; //piece type
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(current_pieces.get_piece(j, i)!=null){
					if(current_pieces.get_piece(j, i).getColour()==turn){
						// it is white
						p_t = current_pieces.get_piece(j, i).getPiece_type();
						can_move(p_t,j,i);
					}	
					
				}
				
			}
		}
//			remove_non_valid(); //secondary filtration
		
	}
	
	
	
	public chess_linked tempL(){
		chess_linked temp = new chess_linked();
temp.add_piece(100, 100, 100, 100, 100); //head


int cc;
int val;
int p_type;
	
int turn_moved;
int turn;//turn...aka pawn turns

for(int i = 0; i<8;i++){
	for(int j = 0; j<8;j++){
		
		if(current_pieces.get_piece(j, i)!=null){
		cc = current_pieces.get_piece(j, i).getColour();
		val = current_pieces.get_piece(j, i).getChess_value();
		p_type = current_pieces.get_piece(j, i).getPiece_type();
		turn_moved = current_pieces.get_piece(j, i).get_turn_moved();
		turn = current_pieces.get_piece(j, i).getTurn();
		
			temp.add_piece(j, i, cc, val, p_type);					
			temp.get_piece(j, i).setTurn(turn);
			temp.get_piece(j, i).set_current_turn(turn_moved);
								
		}
		
	}	
}



		return temp;
	}
	
	
	public boolean aiMove(){
		// this is where the AI happens			
		// ai picks a move and return to player;
	
		/*
		error_checker.pass_link(current_pieces);//updates the board
		get_possible_moves();
		//AI_algorithm(10,1);
		Random rand = new Random();
		int r = rand.nextInt(PMA.size())-1;
		System.out.println("r"+r);
		Gx = PMA.get(r).getX();
		Gy = PMA.get(r).getY();
		Gx1 = PMA.get(r).getX1();
		Gy1 = PMA.get(r).getY1();
				//makeItTemp(Gx,Gy,Gx1,Gy1);
		
		
		//error_checker.pass_link(current_pieces);//updates the board
		PMA.clear(); //cleans
		
		*/
		minmax_algorithm(0,1);
		//Gx = 0;
		//Gy = 1;
		//Gx1 = 0;
	//	Gy1 = 3;
		
		bt.add(tempL());
		move_piece(Gx,Gy,Gx1,Gy1);
		//System.out.println("Just moved x"+Gx+" y"+Gy+" x1"+Gx1+" Y1"+Gy1);
		//printBoard();
		//backtracklinked();
		
		// select a piece and move it
		// ai is always color 0;
		return true;
	}
	
	

	
	public void play_game(){
		//JUST INTRO STUFF
		int first_player;
		System.out.println("Who is going first?");
		System.out.println("YOU: Press 1");
		System.out.println("AI: Press 0");
		first_player = kbrd.nextInt();
		
		if(first_player==0){
			System.out.println("AI THinking... ");
			aiMove(); // ai moves first;
			printBoard();
		}
		
		
		boolean endGame = true;	 // checkmate	
		boolean validMove =true; // valid move
		int x,y,x1,y1; // player input
		int kastle = 0; // castle symantics
		int colour = 1; //white
		boolean Bcheck = false; // check
		boolean Wcheck = false;//check
		int turn=0; 
		
		while(endGame){			
			turn = colour%2;
			if(turn ==0){
				System.out.println("AI TURN");
			}else{
				
				System.out.println("Player 1 go");
			}
			do{
						
				if(turn==1){
					//player turn
					
					error_checker.pass_link(current_pieces);//updates the board
					System.out.println("choose x ");
					x = kbrd.nextInt();
					System.out.println("choose y ");
					y = kbrd.nextInt();
					System.out.println("move to ");
					System.out.println("chose x ");
					x1 = kbrd.nextInt();
					System.out.println("choose y ");
					y1 = kbrd.nextInt();
					System.out.println("computing move.");				
					//add special case here	
						//check special case					
							validMove=error_checker.valid_move(x,y,x1,y1,GlobalTurn);								
							//moving king will yiel a false so...
							if(!validMove){
								//do a special case
								if(special_case(x,x1,y,y1,turn)){
									kastle = 1;
									castle_move(x,x1,y,y1);
									validMove = true;
								}
							}
						
						if(turn==error_checker.turnCheck(x, y)){
							validMove=false;
						}
						
					if(validMove==false){
						System.out.println("try again");

					}	else{
								
						//store temp
						bt.add(tempL());
						move_piece(x,y,x1,y1);
						error_checker.pass_link(current_pieces);//updates the board
						if(turn==1){
							Wcheck = error_checker.checkCheckW();
							if(Wcheck==true){
								validMove=false;
							}
						}else{
							Bcheck = error_checker.checkCheckB();

							if(Bcheck==true){
								validMove=false;
							}
						}
						
						backtracklinked();				
					}	
					//resets
					if(kastle==1&&validMove==false){
						castle_backTrack(x,x1,y,y1); //backtracks jsut in case
					}
					//resets en_p
					if(validMove==false&&error_checker.enPP==true){
						//set to false
						enPois_backTrack(x1, y);
						error_checker.removeEP();
					}else{
						
						if(error_checker.enPP==true){
							enPois_backTrack(x1, y);
						}
					}	
									
				}else{
					//ai turn
					validMove=aiMove();
				
					
					x = Gx;
					y=	Gy;
					x1 = Gx1;
					y1 = Gy1;
					System.out.println("x: "+x+" Y: "+y+" x1: "+x1+" Y1: "+y1);
					error_checker.pass_link(current_pieces);//updates the board
					System.out.println("AI");
				}
						 
			}while(validMove==false);
				
	
			
			if(turn==1){
				// this human move
				
				move_piece(x,y,x1,y1);	
				System.out.println("human");
				error_checker.pass_link(current_pieces);//updates the board
			}
			
			int incP = current_pieces.get_piece(x1, y1).getPiece_type();
			
			if(incP==1||incP==2||incP==6){
				current_pieces.get_piece(x1, y1).incTurn(); //imcrase turn
			}
			current_pieces.get_piece(x1, y1).set_current_turn(GlobalTurn);
		
			//after piece is placed this checks if the move is in check	
			if(turn==0){
				error_checker.pass_link(current_pieces);//updates the board
			//	Wcheck = error_checker.checkCheckW();
				if(Wcheck==true){
					System.out.println("White is in check");
					whiteCheck = true;
					//check if checkmate	
					endGame=error_checker.checkMate(1);
					
				}else{
					whiteCheck = false;
				}
			}else{
				Bcheck = error_checker.checkCheckB();
				if(Bcheck==true){
					System.out.println("Black is in check");
					blackCheck = true;				
					endGame=error_checker.checkMate(0);
					
				}else{
					whiteCheck = false;
				}
			}
			
			
			error_checker.removeEP();
			GlobalTurn ++;
			kastle=0;
			printBoard();
			colour++;
		}//end while
		
		
		//end game
		System.out.println("CHECKMATE");
	}

	public void move_piece(int x, int y, int x2,int y2){
		//removce pice 
		if(current_pieces.get_piece(x2, y2)!=null){
			current_pieces.remove_piece(x2, y2, current_pieces.get_piece(x2, y2).getColour());
		}
		//remove pice
		current_pieces.get_piece(x, y).set_coordinate(x2, y2);
			
		
		
		if(error_checker.enPP==true&&current_pieces.get_piece(x2, y2).getColour()==0){
			 enP_temp(x2,y);
		enpois(x2,y);
			
		}
		
		
		if(y2==7||y2==0&& current_pieces.get_piece(x2, y2).getPiece_type()==1){
			int cc = current_pieces.get_piece(x2, y2).getColour();
			
			 current_pieces.remove_piece(x2, y2, cc);
			 current_pieces.add_piece(x2, y2, cc, 1200, 5);
		}
		
		

	}



	
	public boolean AIWIN(){
	boolean val = true;
		for(int i = 0; i<8;i++){
		for(int j = 0; j<8;j++){
			
			
			if(current_pieces.get_piece(j, i)!=null){
				if(current_pieces.get_piece(j, i).getColour()==0){
					// it is white
					if(current_pieces.get_piece(j, i).getPiece_type()==6){
						// it is white
						val = false;
						break;
					}
					
				}	
				
			}
		
			
		}	
	}
		if(!val){
	//		System.out.println("HUMAN KING EXISTS");
		}
		return val;
	}
	
public boolean humanWIN(){
		
	boolean val = true;
	for(int i = 0; i<8;i++){
	for(int j = 0; j<8;j++){
		
		
		if(current_pieces.get_piece(j, i)!=null){
			if(current_pieces.get_piece(j, i).getColour()==1){
				// it is white
				if(current_pieces.get_piece(j, i).getPiece_type()==6){
					// it is white
					val = false;
					
					break;
				}
				
			}	
			
		}
	
		
	}	
}
	if(!val){
	//	System.out.println("ai KING EXISTS");
	}
	return val;
}
	

	public String print_P(int type,int C){
		// this is for printingg purposes...just kets the value of the pice
		String piece = "";
		
		
		switch(type){
		case 1:
			//pawn
			if(C==0){
				piece = "p";
			}else{
				piece = "P";
			}

			break;
		case 2:
			//rook
			if(C==0){
				piece = "r";
			}else{
				piece = "R";
			}
			break;
		case 3:
			if(C==0){
				piece = "n";
			}else{
				piece = "N";
			}
			//knight
			break;
		case 4:
			if(C==0){
				piece = "b";
			}else{
				piece = "B";
			}
			//bissup

			break;
		case 5:
			if(C==0){
				piece = "q";
			}else{
				piece = "Q";
			}
			//queeen

			break;
		case 6:
			if(C==0){
				piece = "k";
			}else{
				piece = "K";
			}
			//kking		
			break;	
		}		
		return piece;
	}
	
	
	

	//------------------------AI STUFF
	
	Random rand = new Random();
	public int[][] array(int turn){
		
		int[][] movesAvailable = null;	
		
		get_possible_moves(turn);
		int psize = PMA.size();
		if(psize>0){
			
			movesAvailable = new int[psize][4];
			
			for(int i = 0; i<psize;i++){
				// sets array with possible moves
				
				movesAvailable[i][0] = PMA.get(i).getX();
				movesAvailable[i][1] = PMA.get(i).getY();
				movesAvailable[i][2] = PMA.get(i).getX1();
				movesAvailable[i][3] = PMA.get(i).getY1();
				
			}	
			// good size
		}
		
		if(psize>1){
		//randommize	
		int swap = rand.nextInt(psize);
		
		int a,b,c,d;
		a =movesAvailable[swap][0];
		b =movesAvailable[swap][1];
		c =movesAvailable[swap][2];
		d =movesAvailable[swap][3];
		movesAvailable[swap][0] = movesAvailable[0][0];
		movesAvailable[swap][1] = movesAvailable[0][1];
		movesAvailable[swap][2] = movesAvailable[0][2];
		movesAvailable[swap][3] = movesAvailable[0][3];
		movesAvailable[0][0] = a;
		movesAvailable[0][1] = b;
		movesAvailable[0][2] = c;
		movesAvailable[0][3] = d;
		
		}
		return movesAvailable;		
	}
	
	
	

	
	
	
	
	
	public int EvaluationFunction(){
		int val = 0;
		int Bcount = 0;;
		int Wcount = 0;;
		
		for(int i = 0; i<8;i++){
			for(int j = 0; j<8;j++){
				
				if(current_pieces.get_piece(j, i)!=null){
					if(current_pieces.get_piece(j, i).getColour()==1){
						Wcount = Wcount+current_pieces.get_piece(j, i).getChess_value();
						
					}	else{
						
						Bcount = Bcount+current_pieces.get_piece(j, i).getChess_value();
					}
					
				}
				
			}	
		}
		
		
		if(Wcount>=Bcount){
			val = 1;
		}
		
		return val;
		
	}
	
	
	
	ArrayList<chess_linked> bt = new ArrayList<chess_linked>();
	
	
	
	public void backtracklinked(){
	//	System.out.println("BACK TRACKING");
		int cc;
		
	
		for(int i = 0; i<8;i++){
			for(int j = 0; j<8;j++){		
				if(current_pieces.get_piece(j, i)!=null){
				cc = current_pieces.get_piece(j, i).getColour();
				     current_pieces.remove_piece(j, i, cc);
				
				}
			}	
		}	
		
	
		int ci = bt.size()-1; // current index;

		int val;
		int p_type;
			
		int turn_moved;
		int turn;//turn...aka pawn turns
		
		for(int i = 0; i<8;i++){
			for(int j = 0; j<8;j++){
				
				if(bt.get(ci).get_piece(j, i)!=null){
				cc = bt.get(ci).get_piece(j, i).getColour();
				val = bt.get(ci).get_piece(j, i).getChess_value();
				p_type = bt.get(ci).get_piece(j, i).getPiece_type();
				turn_moved = bt.get(ci).get_piece(j, i).get_turn_moved();
				turn = bt.get(ci).get_piece(j, i).getTurn();
			//	System.out.println("added");
					current_pieces.add_piece(j, i, cc, val, p_type);					
					current_pieces.get_piece(j, i).setTurn(turn);
					current_pieces.get_piece(j, i).set_current_turn(turn_moved);
										
				}
				
			}	
		}
		
		
		bt.remove(ci);
		
		
		
	}
	
	
		public int minmax_algorithm(int depth, int player_turn){
			error_checker.pass_link(current_pieces);//updates the board
			
			int x;
			int y;
			int x1;
			int y1;
			int[][] temp = array(player_turn); // set moves available
			if(temp==null){
				//	System.out.println("array empty");
					return 0;
				}
		//	System.out.println("minmax called");
			if(depth>6){ // 6 ply
				
				if(EvaluationFunction()==0){
					
					return +1;
				}
				if(EvaluationFunction()==1){
				//	System.out.println("is it this");
					return -1;
				}
				//return 0;
			}
			
		
			 int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			
			for(int i = 0; i<temp.length;i++){
		
				x = temp[i][0];
				y = temp[i][1];
				x1 = temp[i][2];
				y1 = temp[i][3];
				
				if(player_turn==1){
					// ai move
					bt.add(tempL());
					move_piece(x,y,x1,y1);
					int curretnScore = minmax_algorithm(depth+1,0);
					max = Math.max(curretnScore, max);
					if(curretnScore>=0){
						if(depth==0){
							Gx = x;
							Gy = y;
							Gx1 = x1;
							Gy1 = y1;
							
						}
					}
					if(curretnScore==1){
						backtracklinked();
						break;
					}
					
					if(i == temp.length-1 && max < 0){
						if(depth == 0){
							Gx = x;
							Gy = y;
							Gx1 = x1;
							Gy1 = y1;
							
						}
						}
				}else{
					if(player_turn==0){
						bt.add(tempL());
						move_piece(x,y,x1,y1);
						int curretnScore = minmax_algorithm(depth+1,1);
						min = Math.min(curretnScore, min);
						if(min == -1){
							backtracklinked();
							break;
							}
					}
				}// end of else
				
				backtracklinked();
			}
			return player_turn == 1?max:min;
		}
	
	
	
	
}



