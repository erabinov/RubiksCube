package src;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Date;
import TerminalIO.KeyboardReader;
public class Dialog extends JDialog 
{
	private rubiksCube ourCube;
	private inputPainting ourPan;
	public Dialog(rubiksCube cube,JFrame frame)
	{
		super(frame,"User Input Solver");
		setSize(610,485);
		ourCube = cube;
		Container container = getContentPane();
		ourPan = new inputPainting(Color.GRAY,cube);
		container.add(ourPan);
		addKeyListener(new KeyListen());
	}
	private class KeyListen extends KeyAdapter
	{
		public void keyPressed(KeyEvent k)
		{
			if(k.getKeyCode()==KeyEvent.VK_D)
			{
				char[][] cubeArr = new rubiksCube().getCube();
				stickerCreator[][] backArray = ourPan.getBackArray();
				for(int i=0;i<backArray.length;i++)
					for(int j=0;j<backArray[i].length;j++)
					{
						Color col = backArray[i][j].getColor();
						if(col.equals(Color.RED))
							cubeArr[i][j]='R';
						if(col.equals(Color.ORANGE))
							cubeArr[i][j]='O';
						if(col.equals(Color.YELLOW))
							cubeArr[i][j]='Y';
						if(col.equals(Color.WHITE))
							cubeArr[i][j]='W';
						if(col.equals(Color.GREEN))
							cubeArr[i][j]='G';
						if(col.equals(Color.BLUE))
							cubeArr[i][j]='B';
					}
				char[][] template = {{'G','R','O'},{'W','R','Y'},{'G','O','O'},{'R','B','R','W','W','W','B','B','G','W','Y','Y'},{'G','B','B','R','W','O','B','G','R','W','Y','Y'},{'O','G','R','W','Y','Y','R','G','O','Y','R','Y'},{'B','O','B'},{'W','O','O'},{'G','G','B'}};
				ourCube = new rubiksCube(cubeArr);
				if(!ourCube.failedConstruction())
				{
					hide(); getParent().hide();
					ourCube.solveMoves();
					new KeyboardReader().pause();
					show(); getParent().show();
				}
			}
			else if(k.getKeyCode()==KeyEvent.VK_U)
			{
				hide();
				ourCube.solveMoves();
				show();
			}
		}	
	}
}
