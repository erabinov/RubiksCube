package src;
import javax.swing.*;
import java.awt.*;

import src.Move;
import src.rubiksCube;
import TerminalIO.KeyboardReader;
public class GUIshell 
{
	public static void main(String [] args)
	{
		System.out.println("Welcome to the Rubik's Cube Program!");
		System.out.println("When you finish reading these instructions, \na window will appear with a cube on the screen.\nThe image on the left is that of the top, left, and front faces of the cube.\nThe image on the right is of the rest of the faces.");
		System.out.println("The controls for manipulating the cube are as follows:");
		System.out.println("Q-<Z'>  W-B     E-L'    R-      T-      Y-<X>  U-      I-R     O-B'    P-<Z>");
		System.out.println("A-<Y'>  S-D     D-L     F-U'    G-F'    H-F    J-U     K-R'    L-D'    ;-<Y'>");		
		System.out.println("Z-      X-      C-      V-      B-      N-<X'> M-      ,-      .-      /-<Y'>");
		System.out.println("The letters of the keyboard are followed by a dash and a character that\nrepresents the move that is performed by pressing that key.\nEach letter stands for a quarter turn of the given face:\nU stands for Up, D for Down, R for Right, L for Left, F for Front, B for Back.");
		System.out.println("An apostrophe after the move means a quarter turn counter-clockwise.\nA move between <> means a turn of the whole cube along the given axis.");
		System.out.println("These moves, as well as the scramble and solve commands, can also be found in the menu of the main window.");
		System.out.println("================================================================================");
		System.out.println("Instructions for user input:");
		System.out.println("When you select File->User Input, a net of a solved Rubik's Cube appears.\nThe center pieces cannot be changed, but the rest of the pieces can be changed\nby clicking.\nThe pieces cycle through the colors in rainbow order.\nIn this way enter your scrambled cube and press D.");
		System.out.println("The program will now solve the Rubik's Cube.\nBe patient, because this takes some time. Moves will appear in chunks.\nWait until the other windows reappear to apply these moves to your cube.");
		char rand = new KeyboardReader().readChar("Enter 'R' if you would like to be given random moves to scramble the cube:");
		if(rand=='R')
			for(int i=0;i<25;i++)
			{
				int move = (int)(Math.random()*12);
				System.out.print(Move.values()[move]+" ");
			}
		new KeyboardReader().pause();
		rubiksCube cubie = new rubiksCube();
		/*int numTimes = new KeyboardReader().readInt("Enter Number:");
		for(int i=0;i<numTimes;i++)
		{
			cubie.scramble();
			cubie.solveMoves();
			cubie.solve();
		}*/
		GUIFrame cube = new GUIFrame(cubie);
		JFrame frame = new JFrame();
		cube.setTitle("Rubik's cube program");
		cube.setSize(800,800);
		cube.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cube.setVisible(true);
	}
}
