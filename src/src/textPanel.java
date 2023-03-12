package src;
import javax.swing.*;
import java.awt.*;
public class textPanel extends JPanel{
	private rubiksCube cube;
	public textPanel(Color backColor, rubiksCube rc)
	{
		setBackground(backColor);
		cube = rc;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		if(cube.userSolve()==true)
		{
			g.drawString("Congratulations! You solved the cube!",400,600);
		}
		else
		{
			g.drawString("Keep working!",400,600);
		}
	}
}