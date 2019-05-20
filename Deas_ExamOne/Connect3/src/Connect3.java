import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Connect3 extends JApplet{


private final int BLOCKSIZE		= 100; 	//size of blocks
	
	private final int BLOCK_VACANT	= 0; //empty square
	private final int PLAYER_ONE	= 1; //player one
	private final int PLAYER_TWO	= 2; //player two
	
	private final int GRIDROWS		= 4; //number of rows
	private final int GRIDCOLS		= 4; //number of columns
	
	private int currentPlayer		= 1 ; //current player
	private int winningPlayer		= 0; //winning player
	
	int[][] board 					= new int[GRIDROWS][GRIDCOLS]; //array for board
	
	public void init() {

		   // Set the background color to white.
	      getContentPane().setBackground(Color.white);
	      
	      // Add the mouse listener.
	      addMouseListener(new MyMouseListener());
	      
	      //call method to initialize game board
	      initGameBoard();
	      
	      //draw graphics
	      repaint();
	}
	
	public void initGameBoard(){
	      // Initialize game board
	      for (int rows = 0; rows < GRIDROWS; rows++) {
	    	  for (int cols = 0; cols < GRIDCOLS; cols++)
	    		  board[rows][cols]=0;
	    		  
	      }
	}
	

	public void paint( Graphics g) {
	
		//Call the superclass paint method
		 super.paint(g);

		 //set the size of x and y to 100
		 int currentX  = BLOCKSIZE;
		 int currentY  = BLOCKSIZE;
		 
		 int blockOccupant = 0;
		 
	     // Initialize game board
	     for (int cols = 0; cols < GRIDROWS; cols++) {
	    	  for (int rows = 0; rows < GRIDCOLS; rows++) {
	    		  g.setColor(Color.black);
	    		  g.drawRect(currentX, currentY, BLOCKSIZE, BLOCKSIZE);

	    	  	 //check if block is occupied
	    	  	  blockOccupant = board[rows][cols];
	    	  	  if (blockOccupant > 0) {
	    	  		
	    	  		  //set player one's color to red

	    	  		  if (blockOccupant == PLAYER_ONE) 
	    	  			g.setColor(Color.red);
	    	  		  
	    	  		 //set player two's color to blue
	    	  		  
	    	  		  else if (blockOccupant == PLAYER_TWO) 
		    	  			g.setColor(Color.blue);
	    	  		  
	    	  		  //draw circles the color of the current player
	    	  		  g.fillOval(currentX+2,  currentY+2,  BLOCKSIZE-2, BLOCKSIZE-2);
	    	  		  
	    	  		  
	    	  	  }

	    	  	  currentY=currentY + BLOCKSIZE ;
	    	  	  
	    	  }

	    	  // increment our row
	    	  currentX = currentX + BLOCKSIZE;
	    	  // increment column
	    	  currentY = BLOCKSIZE;
	      }
	      
	     //display the winner once 3 in a row is achieved
	      if (winningPlayer == PLAYER_ONE)
	    	  JOptionPane.showMessageDialog(null, "Player Red is the winner");
	      else if (winningPlayer == PLAYER_TWO)
	    	  JOptionPane.showMessageDialog(null, "Player Blue is the winner");

	}
	
	//method to check for 3 in a row
	private void checkForWinner( int curRow, int curCol) {
		
		int curColPlayer = board[curRow][curCol]; 
	
		int rowCount = 0;

		// Vertical winner
		for (int row=GRIDROWS-1; row > 0; row-- ) {
			if ( board[row][curCol] == curColPlayer) 
				rowCount++;
			else
				rowCount = 0;

			if (rowCount >= 3) 
				break;

		}

		// Horizontal winner
		int colCount = 0;
		
		for (int col=0; col < GRIDCOLS; col++) {
			if (board[curRow][col] == curColPlayer)
				colCount++;
			else
				colCount = 0;

			if (colCount >= 3) 
				break;
		}
		
		if (rowCount==3 || colCount == 3)  
			winningPlayer = curColPlayer;
	}
	
	//method for mouse click
	public class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (winningPlayer > BLOCK_VACANT) {
				return; //if game has winner return message
			}
			
			//get location of column and row clicked on
			int col = e.getX();
			int row = e.getY();
			
			int curCol = (col/BLOCKSIZE) - 1 ;
			
			//determine which block the circle will be in
			for (int curRow=GRIDROWS - 1; curRow >= 0; curRow--) {
				if ( board[curRow][curCol] == BLOCK_VACANT) {
					//add color to circle depending on player and check for winner
					board[curRow][curCol] = currentPlayer; 
					checkForWinner(curRow, curCol);
					break;	
				}
			}
			//change current player
			if (currentPlayer == PLAYER_ONE) 
				currentPlayer = PLAYER_TWO;
			else
				currentPlayer = PLAYER_ONE;
			//draw updated board
			repaint();
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
}
