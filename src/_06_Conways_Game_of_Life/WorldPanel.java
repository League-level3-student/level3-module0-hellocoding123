package _06_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
    private static final long serialVersionUID = 1L;
    private int cellsPerRow;
    private int cellSize;

    private Timer timer;

    // 1. Create a 2D array of Cells. Do not initialize it.
    Cell[][]cells;

    public WorldPanel(int w, int h, int cpr) {
        setPreferredSize(new Dimension(w, h));
        addMouseListener(this);
        timer = new Timer(500, this);
        this.cellsPerRow = cpr;

        // 2. Calculate the cell size.
        this.cellSize = w/cellsPerRow;
        // 3a. Initialize the cell array to the appropriate size.
        cells = new Cell[cellsPerRow][cellsPerRow];
        // 3b. Iterate through the array and initialize each cell.
        //    Don't forget to consider the cell's dimensions when 
        //    passing in the location.
        for(int r = 0; r < cellsPerRow; r++) {
        	for (int c = 0; c < cellsPerRow; c++) {
        		int x = c*cellSize;
        		int y = r*cellSize;
        		cells[r][c] = new Cell(x,y,cellSize);
        	}
        }
    }

    public void randomizeCells() {
        // 4. Iterate through each cell and randomly set each
        //    cell's isAlive memeber to true or false
    	 for(int r = 0; r < cellsPerRow; r++) {
         	for (int c = 0; c < cellsPerRow; c++) {
         		int rand = (int)Math.random()*2;
         		if(rand == 1) {
         			cells[r][c].isAlive = true;
         		}
         		if(rand==2) {
         			cells[r][c].isAlive = false;
         		}
         	}
         }
        repaint();
    }

    public void clearCells() {
        // 5. Iterate through the cells and set them all to dead.
    	 for(int r = 0; r < cellsPerRow; r++) {
         	for (int c = 0; c < cellsPerRow; c++) {
         		
         		cells[r][c].isAlive=false;
         	}
         }
        repaint();
    }

    public void startAnimation() {
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }

    public void setAnimationDelay(int sp) {
        timer.setDelay(sp);
    }

    @Override
    public void paintComponent(Graphics g) {
        // 6. Iterate through the cells and draw them all
    	 for(int r = 0; r < cellsPerRow; r++) {
         	for (int c = 0; c < cellsPerRow; c++) {
         		cells[r][c].draw(g);
         	}
         }

        // Draw the perimeter of the grid
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    // Advances world one step
    public void step() {
        // 7. iterate through cells and fill in the livingNeighbors array
        //    using the getLivingNeighbors method.
        int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];

       
        for(int r = 0; r < cellsPerRow; r++) {
         	for (int c = 0; c < cellsPerRow; c++) {
         		livingNeighbors[r][c] = getLivingNeighbors(cells, r, c);
         	}
         }
        
         // 8. check if each cell should live or die
        for(int r = 0; r < cellsPerRow; r++) {
         	for (int c = 0; c < cellsPerRow; c++) {
         		cells[r][c].liveOrDie(livingNeighbors[r][c]);
         	}
         }
        repaint();
    }

    // The method below gets the number of living neighbors around a
    // particular cell in the 2D array. A cell can have up to 8 neighbors.
    //        1   2    3
    //        4  cell  5
    //        6   7    8
    public int getLivingNeighbors(Cell[][] cells, int cellRow, int cellCol){
        int livingNeighbors = 0;
        
        if(cellRow != 0) {
            if(cells[cellRow - 1][cellCol].isAlive) livingNeighbors++;
        }
        
        if(cellRow != cellsPerRow - 1) {
            if(cells[cellRow + 1][cellCol].isAlive) livingNeighbors++;
        }
        if(cellCol != 0) {
            if(cells[cellRow][cellCol - 1].isAlive) livingNeighbors++;
        }
        if(cellCol != cellsPerRow - 1) {
            if(cells[cellRow][cellCol + 1].isAlive) livingNeighbors++;
        }
        if(cellRow != 0 && cellCol != 0) {
            if(cells[cellRow - 1][cellCol - 1].isAlive) livingNeighbors++;
        }
        if(cellRow != cellsPerRow - 1 && cellCol != cellsPerRow - 1) {
            if(cells[cellRow + 1][cellCol + 1].isAlive) livingNeighbors++;
        }
        if(cellRow != 0 && cellCol != cellsPerRow - 1) {
            if(cells[cellRow - 1][cellCol + 1].isAlive) livingNeighbors++;
        }
        if(cellRow != cellsPerRow - 1 && cellCol != 0) {
            if(cells[cellRow + 1][cellCol - 1].isAlive) livingNeighbors++;
        }
        
        return livingNeighbors;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // 9. Use e.getX() and e.getY() to get the mouse PIXEL (not cell)
        //    location that was clicked. Each cell is a square with a length of
        //    cellSize, meaning it's possible to click inside of a cell. You
        //    have to determine the cell that was clicked from the pixel
        //    location and toggle the 'isAlive' variable for that cell.
    	for(int row = 0; row < cellsPerRow; row++) {
        	for(int col = 0; col < cellsPerRow; col++) {
        		if(((e.getY() >= (cellSize*row)) && (e.getY() < ((cellSize*row)+cellSize))) && ((e.getX() >= (cellSize*col)) && (e.getX() < ((cellSize*col)+cellSize)))) {
        			
        			cells[row][col].isAlive = !cells[row][col].isAlive;
        			
        			//check parenthesis in if statement
        		}
        	}
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }
}
