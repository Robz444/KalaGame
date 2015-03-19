import java.util.Scanner;

import com.source.TwitterMessenger;

public class Kalah {
	public static boolean player = true;
	public static int[] board = new int[] {3,3,3,3,3,3,0,3,3,3,3,3,3,0};
	public static int turn = 0;
	public static void main(String[] args){
		boolean game=true;

		String[] moves = new String[40];
		Scanner reader = new Scanner(System.in);
		
		String move;
		int ind = 0;
        System.out.println("Ez algo ez lyfe");

        while (game){
        	for (int x=13;x>6;x--){
            	System.out.print(board[x]);	
        	}
        	System.out.println(" ");
        	System.out.print(" ");
        	for (int x=0;x<7;x++){
        		System.out.print(board[x]);
        	}
        	System.out.println("");
        	if (player) System.out.println("Player L's Move");
        	else System.out.println("Player R's Move");
        	move = reader.next();
        	System.out.println(move.substring(0,1));
        	System.out.println(Integer.parseInt(move.substring(1,2)));
        	if ((move.substring(0,1).equals("L")||move.substring(0,1).equals("R"))&&((Integer.parseInt(move.substring(1,2))>0)&&(Integer.parseInt(move.substring(1,2))<7))){
        		if (move.substring(0,1).equals("L")){
        			ind = Integer.parseInt(move.substring(1))-1;
        			if (board[ind]>0){
        				moves[turn] = Move(ind,move);
        				game = Gamegoing(board);
        			}
        			else System.out.println("Move invalid.");

        		}
        		else if (move.substring(0,1).equals("R")){      		
        			ind = Integer.parseInt(move.substring(1))+6;
        			if (board[ind]>0){
        				moves[turn] = Move(ind,move);
        				game = Gamegoing(board);
        			}
        			else System.out.println("Move invalid.");
        		}
        	}
        	else System.out.println("Move invalid.");
        	
        
    }
	}
	static String Move (int arnum,String mov){
		int pointer = arnum;
		while (board[arnum]>0){
			pointer++;
			if (player == true && pointer == 13) pointer++;
			if (player == false && pointer == 6) pointer++;
			if (pointer == 14) pointer = 0;

			board[pointer]++;
			board[arnum]--;
			
		}
		String boardstring="";

		if (pointer == 6 || pointer == 13){	
			for (int x=0;x<14;x++){
				boardstring=boardstring+board[x];
			}
			return (mov+", "+boardstring);
		}
		else if (board[pointer]==1){
			if (pointer>6 && !player && board[pointer-((pointer-6)*2)]>0){
				board[13]+=board[pointer]+board[pointer-((pointer-6)*2)];
				board[pointer]=0;
				board[pointer-((pointer-6)*2)]=0;

			}
			else if(pointer<6 && player && board[pointer+((6-pointer)*2)]>0){
				board[6]+=board[pointer]+board[pointer+((6-pointer)*2)];
				board[pointer]=0;
				board[pointer+((6-pointer)*2)]=0;
			}
		}
		for (int x=0;x<14;x++){
			boardstring=boardstring+board[x];
		}
		player = !player;
		turn++;
		return mov+", "+boardstring;}
	static boolean Gamegoing(int[] gameboard){
		boolean end=true;
		for (int x=0;x<6;x++){
			if (gameboard[x]>0) end=false;
		}
		if (end=true) return false;
		for (int x=12;x>6;x--){
			if (gameboard[x]>0) end=false;
		}
		if (end=true) return false;
		return true;
	}
}
