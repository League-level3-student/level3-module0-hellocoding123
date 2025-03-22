package _00_Intro_To_Arrays;

import java.util.Random;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class _02_RobotRace {
    // 1. make a main method
	public static void main(String[] args) {
		

        // 2. create an array of 5 robots.
		Robot[] robots = new Robot[5];
        // 3. use a for loop to initialize the robots.
		for(int i = 0; i < robots.length; i++) {
			robots[i] = new Robot();
		}
        // 4. make each robot start at the bottom of the screen, side by side, facing up
		for(int i = 0; i < robots.length; i++) {
			robots[i].moveTo(155+150*i, 540);
			robots[i].setSpeed(30);
		}
        // 5. use another for loop to iterate through the array and make each robot move
        // a random amount less than 50.
		boolean notwin = true;
		int winner = 0;
		
		while(notwin) {
			for(int i = 0; i < robots.length; i++) {
				robots[i].move((int)(Math.random()*49));
				if(robots[i].getY() < 100) {
					notwin = false;
					winner = i;
				}
			}
		}
        // 6. use a while loop to repeat step 5 until a robot has reached the top of the
        // screen.
    
        // 7. declare that robot the winner and throw it a party!
		if(!notwin) {
			for(int i = 0; i < 90; i++) {
				robots[winner].turn(10);
			}
		}
		for(int i = 0; i < robots.length; i++) {
			robots[i].clear();
			robots[i].hide();
		}
		
        // 8. try different races with different amounts of robots.
		Robot[] robots2 = new Robot[2];
        // 9. make the robots race around a circular track.
		for(int i = 0; i < robots2.length; i++) {
			robots2[i].moveTo(240, 340 + 100*i);
		}
		
		notwin = true;
		winner = 0;
		int angle = 0;
		
		while(notwin) {
			for(int i = 0; i < robots2.length; i++) {
				for(int i2 = 0; i2 < (int)(Math.random()*49); i2++) {
					robots2[i].move(1);
					angle++;
					robots2[i].setAngle(angle);
					if(angle >= 360); {
						notwin = false;
						winner = i;
					}
				}
			}
		}
	}
}
