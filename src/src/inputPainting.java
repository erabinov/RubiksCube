package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyAdapter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
public class inputPainting extends JPanel {

	public static rubiksCube blankCube=new rubiksCube();
	private rubiksCube cube;
	private stickerCreator[][] backArray;
	private HashMap<listCoordinates,listCoordinates> coordMap = new HashMap<listCoordinates,listCoordinates>();
	private Set<listCoordinates> centerSet;
	public inputPainting(Color backColor, rubiksCube newCube) 
	{
		centerSet = new HashSet<listCoordinates>();
		centerSet.add(new listCoordinates(1,1));
		centerSet.add(new listCoordinates(4,1));
		centerSet.add(new listCoordinates(4,4));
		centerSet.add(new listCoordinates(4,7));
		centerSet.add(new listCoordinates(4,10));
		centerSet.add(new listCoordinates(7,1));
		setBackground(backColor);
		//cube = newCube;
		int yStart=0;
		char[][] cubeArr = blankCube.getCube();
		backArray = new stickerCreator[cubeArr.length][];
		for(int i=0;i<cubeArr.length;i++)
			backArray[i] = new stickerCreator[cubeArr[i].length];
		for(int row=0;row<cubeArr.length;row++)
		{
			int xStart;
			if(row>2&&row<6)
				xStart=0;
			else
				xStart=150;
			for(int col=0;col<cubeArr[row].length;col++)
			{
				Color color;
				switch(cubeArr[row][col])
				{
					case 'R':
					color=Color.RED;
					break;
					case 'O':
					color=Color.ORANGE;
					break;
					case 'W':
					color=Color.WHITE;
					break;
					case 'Y':
					color=Color.YELLOW;
					break;
					case 'G':
					color=Color.GREEN;
					break;
					case 'B':
					color=Color.BLUE;
					break;
					default:
					color=Color.GRAY;
					break;
				}
				stickerCreator newStick = new stickerCreator("square",xStart,yStart,color);
				backArray[row][col] = newStick;
				coordMap.put(new listCoordinates(xStart,yStart),new listCoordinates(row,col));
				xStart+=50;
			}
			yStart+=50;
			}
			addMouseListener(new stickerListener());
}
	public void paintComponent(Graphics g)
	{
		g.setColor(getBackground());
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman",Font.BOLD,18));
		g.drawString("Press D when you are done.",325,385);
		for(stickerCreator[] sCArr:backArray)
			for(stickerCreator sC: sCArr)
				sC.paintSticker(g);

	}
	public stickerCreator[][] getBackArray(){return backArray;}
	private class stickerListener implements MouseListener
	{
		private Color[] colArr={Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.WHITE};
		public void mouseClicked(MouseEvent e)
		{
			repaint();
			int xCl = e.getX();
			int yCl = e.getY();
			if(xCl<150&&(yCl<150||yCl>300))
				return;
			if(xCl>300&&(yCl<150||yCl>300))
				return;
			double minDist = Math.sqrt(Math.pow(600-xCl,2)+Math.pow(600-yCl,2));
			listCoordinates minLoc = new listCoordinates(600,600);
			for(listCoordinates lC:coordMap.keySet())
			{
				int newXVal = lC.getRow()+25-xCl;
				int newYVal = lC.getCol()+25-yCl;
				if(Math.sqrt(Math.pow(newXVal,2)+Math.pow(newYVal,2))<minDist)
				{
					minLoc = lC;
					minDist = Math.sqrt(Math.pow(newXVal,2)+Math.pow(newYVal,2));
				}
			}
			stickerCreator sC = backArray[coordMap.get(minLoc).getRow()][coordMap.get(minLoc).getCol()];
			if(centerSet.contains(coordMap.get(minLoc)))
				return;
			int i;
			for(i=0;i<colArr.length;i++)
			{	if(sC.getColor().equals(colArr[i]))
					{break;}
			}
			backArray[coordMap.get(minLoc).getRow()][coordMap.get(minLoc).getCol()].changeColor(colArr[(i+1)%colArr.length]);
			inputPainting.this.repaint();
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
	}	
}
