import java.util.*;
import java.lang.*;

// Tic Tac Toe game using MinMax algorithm based in AI
// Time complexity : O(b^d)

class InvalidInputException extends Exception
{
	public String toString()
	{
		return ("Inavlid input");
	}
}

class Game
{
	private char board[][] = {{'1','2','3'},{'4','5','6'},{'7','8','9'}};
	private char ch;
	
	Game(char ch)
	{
		this.ch = ch;
	}
	
	void setMove(int n) throws InvalidInputException
	{
		int i=-1,j=-1;
		if(n<=0)
			throw new InvalidInputException();
		else if(n<4)
		{
			i=0;j=(n-1)%3;
		}
		else if(n<7)
		{
			i=1;j=(n-1)%3;
		}
		else if(n<10)
		{
			i=2;j=(n-1)%3;
		}
		else
			throw new InvalidInputException();
		
		if(board[i][j]=='X' || board[i][j]=='O')
			throw new InvalidInputException();
		
		board[i][j] = (ch=='X')?'O':'X';
	}
		
	void bestMove()
	{
		int bestScore = Integer.MIN_VALUE;
		int iIndex=-1,jIndex=-1;
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j] != 'X' && board[i][j] != 'O')
				{
					char temp = board[i][j];
					board[i][j] = ch;
					int score = minmax(false);
					board[i][j] = temp;
					
					if(score>bestScore)
					{
						iIndex = i;jIndex = j;
						bestScore = score;
					}
				}
			}
		}
		if(iIndex!=-1 && jIndex!=-1)
		board[iIndex][jIndex] = ch;
	}
	
	private int minmax(boolean isMaximizing)
	{
		char result = checkWinner();
		
		if(result == ch)
			return 1;
		else if(result == ((ch=='X')?'O':'X'))
			return -1;
		else if(result == 't')
			return 0;
		
		if(isMaximizing)
		{
			int bestScore = Integer.MIN_VALUE;
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(board[i][j] != 'X' && board[i][j] != 'O')
					{
						char temp = board[i][j];
						board[i][j] = ch;
						int score = minmax(false);
						board[i][j] = temp;
						bestScore = Math.max(score,bestScore);
					}
				}
			}
			return bestScore;
		}
		else
		{
			int bestScore = Integer.MAX_VALUE;
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(board[i][j] != 'X' && board[i][j] != 'O')
					{
						char temp = board[i][j];
						board[i][j] = (ch=='X')?'O':'X';
						int score = minmax(true);
						board[i][j] = temp;
						bestScore = Math.min(score,bestScore);
					}
				}
			}
			return bestScore;	
		}
	}
	
	char checkWinner()
	{
		String tmp1,tmp2;

		boolean tie = true;
		for(int i=0;i<3;i++)
		{
			tmp1="";tmp2="";
			for(int j=0;j<3;j++)
			{
				tmp1 = tmp1+board[i][j];
				tmp2 = tmp2+board[j][i];
				if(board[i][j] != 'X' && board[i][j] != 'O')
					tie = false;
			}

			if(tmp1.equals("XXX") || tmp2.equals("XXX"))
				return 'X';
			else if(tmp1.equals("OOO") || tmp2.equals("OOO"))
				return 'O';
		}
		tmp1="";tmp2="";
		tmp1 = tmp1+board[0][0]+board[1][1]+board[2][2];
		tmp2 = tmp2+board[0][2]+board[1][1]+board[2][0];
		
		if(tmp1.equals("XXX") || tmp2.equals("XXX"))
			return 'X';
		else if(tmp1.equals("OOO") || tmp2.equals("OOO"))
			return 'O';
		else if(tie)
			return 't';
		else return ' ';
	}
	
	void display()
	{
		System.out.println();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++)
				System.out.print("   "+board[i][j]+"\t|");
			if(i<2)
			System.out.println("\n--------------------------");
		}
		System.out.println();
	}
}

public class TicTacToe
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int temp[] = new int[9];
		
		System.out.println("\n---------------------------WELCOME!! to the TIC TAC TOE Game-------------------------------");
		System.out.println("\nDisclaimer : this is an AI based game so please don't feel bad if you lose.....which is going to happen always");
			try{
				System.out.println("I am giving u choice so that you don't make excuses after losing the game....hahaha!!");
				System.out.println("\nWhat you want to take ( X or O )? :");
				char ch = sc.next().charAt(0);
				if(ch=='x') ch='X';
				else if (ch=='o') ch='O';
				else throw new InvalidInputException();
				
				System.out.println("do you want to play first? ( y or n ) :");
				char tmp = sc.next().charAt(0);
				Game g = (ch=='X')?new Game('O'):new Game('X');
				
				switch(tmp)
				{
					case 'y':
					case 'Y':
					g.display();
					for(int i=0;i<8;i++){
					System.out.println("Your turn =>");
					System.out.print("enter slot number :");
					if((int)(Math.random()*10)==5){
						Thread.sleep((int)(Math.random()*501)+2000);
						System.out.println("chal bhi de bhai???");
					}
					int n = sc.nextInt();
					g.setMove(n);
					char result=g.checkWinner();
					if(result!=' '){
						if(result=='t'){ System.out.println("Kitna bachoge jaaniii......");System.exit(0);}
						else{
							System.out.print("\nI ");
							Thread.sleep(200);
							System.out.print("W");
							Thread.sleep(200);
							System.out.print("O");
							Thread.sleep(200);
							System.out.print("N");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print("H");
							Thread.sleep(200);
							System.out.print("U");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("A");
							Thread.sleep(200);
							System.out.print("Y");
							Thread.sleep(50);
							System.out.print("!");
							Thread.sleep(50);
							System.out.print("!");
						System.exit(0);
						}
					}
					if((int)(Math.random()*6) == 5){
						System.out.println("Let me think......");
						Thread.sleep(2000);
					}
					if((int)(Math.random()*10) == 8)
						System.out.println("Nice move but you will not win....LOL!");
					System.out.println("\nHere i go =>");
					g.bestMove();
					g.display();
					result=g.checkWinner();
					if(result!=' '){
						if(result=='t'){ System.out.println("Kitna bachoge jaaniii......");System.exit(0);}
						else{
							System.out.print("\nI ");
							Thread.sleep(200);
							System.out.print("W");
							Thread.sleep(200);
							System.out.print("O");
							Thread.sleep(200);
							System.out.print("N");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print("H");
							Thread.sleep(200);
							System.out.print("U");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("A");
							Thread.sleep(200);
							System.out.print("Y");
							Thread.sleep(50);
							System.out.print("!");
							Thread.sleep(50);
							System.out.print("!");
						System.exit(0);
						}
					}
					}
					break;
					
					case 'n':
					case 'N':
					if((int)(Math.random()*10)==3)
					System.out.println("Dar gye kya????......hahahhahahha......");
					Thread.sleep(100);
					for(int i=0;i<8;i++){
					g.bestMove();
					g.display();
					char result=g.checkWinner();
					if(result!=' '){
						if(result=='t'){ System.out.println("Kitna bachoge jaaniii......");System.exit(0);}
						else{
							System.out.print("\nI ");
							Thread.sleep(200);
							System.out.print("W");
							Thread.sleep(200);
							System.out.print("O");
							Thread.sleep(200);
							System.out.print("N");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print("H");
							Thread.sleep(200);
							System.out.print("U");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("A");
							Thread.sleep(200);
							System.out.print("Y");
							Thread.sleep(50);
							System.out.print("!");
							Thread.sleep(50);
							System.out.print("!");
						System.exit(0);
						}
					}
					System.out.println("Your turn =>");
					System.out.print("enter slot number :");
					if((int)(Math.random()*10)==5){
						Thread.sleep((int)(Math.random()*501)+500);
						System.out.println("chal bhi de bhai???");
					}
					int n = sc.nextInt();
					g.setMove(n);
					result=g.checkWinner();
					if(result!=' '){
						if(result=='t'){ System.out.println("Kitna bachoge jaaniii......");System.exit(0);}
						else{
							System.out.print("\nI ");
							Thread.sleep(200);
							System.out.print("W");
							Thread.sleep(200);
							System.out.print("O");
							Thread.sleep(200);
							System.out.print("N");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(100);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print(".");
							Thread.sleep(200);
							System.out.print("H");
							Thread.sleep(200);
							System.out.print("U");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("R");
							Thread.sleep(200);
							System.out.print("A");
							Thread.sleep(200);
							System.out.print("Y");
							Thread.sleep(50);
							System.out.print("!");
							Thread.sleep(50);
							System.out.print("!");
						System.exit(0);
						}
					}
					if((int)(Math.random()*6) == 5){
						System.out.println("Let me think......");
						Thread.sleep(2000);
					}
					if((int)(Math.random()*10) == 8)
						System.out.println("Nice move but you will not win....LOL!");
					System.out.println("\nHere i go =>");
					
					}
					break;
					
					default:
					System.out.println("Wrong choice!");
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}
}