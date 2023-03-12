package src;
import javax.swing.*;
import java.awt.*;
import src.rubiksCube;
public class cubePainting extends JPanel{
	private rubiksCube cube;
	private stickerCreator[][] stickArray;
	public cubePainting(Color backColor, rubiksCube rc)
	{
		setBackground(backColor);
		cube = rc;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font("Times New Roman",Font.PLAIN,12));
		//g.drawString("Q-<Z'>  W-B     E-L'    R-      T-      Y-<X>  U-      I-R     O-B'    P-<Z>",200,120);
		//g.drawString("A-<Y'>  S-D     D-L     F-U'    G-F'    H-F    J-U     K-R'    L-D'    ;-<Y'>",200,160);		
		//g.drawString("Z-      X-      C-      V-      B-      N-<X'> M-      ,-      .-      /-<Y'>",200,200);		
		stickArray= new stickerCreator[9][];
		int i;
		for(i=0;i<3;i++)
		{
			stickArray[i]=new stickerCreator[3];
		}
		for(i=i;i<6;i++)
		{
			stickArray[i]=new stickerCreator[12];
		}
		for(i=i;i<9;i++)
		{
			stickArray[i]=new stickerCreator[3];
		}
		//Populates the cells with stickerCreator objects.
		int x=90, y=285;
		int x2=x, y2=y+163;
		int x3=x+510, y3=y+110, x4=x+460, y4=y-35;
		int x6=x+660;int y6=y+129; int x5=x+100;int y5=y+100;
		for(int j=3;j<6;j++)
		{
			int xTemp=x, yTemp=y, xTemp2=x2, yTemp2=y2, xTemp3=x3, yTemp3=y3, xTemp4=x4, yTemp4=y4;
			for(int k=3;k<6;k++)
				{
				switch(cube.getColor(j,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("top",x,y,Color.white);
					stickArray[j][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("top",x,y,Color.yellow);
					stickArray[j][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("top",x,y,Color.blue);
					stickArray[j][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("top",x,y,Color.green);
					stickArray[j][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("top",x,y,Color.red);
					stickArray[j][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("top",x,y,Color.orange);
					stickArray[j][k]=stick;
					break;
					default: stick= new stickerCreator("top",x,y,Color.orange); break;
				}
				x+=50;
				y-=25;						
				}
			x=xTemp;y=yTemp;
			x+=50; y+=25;
			for(int k=0;k<3;k++)
			{
				switch(cube.getColor(j,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("left",x2,y2,Color.white);
					stickArray[j][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("left",x2,y2,Color.yellow);
					stickArray[j][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("left",x2,y2,Color.blue);
					stickArray[j][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("left",x2,y2,Color.green);
					stickArray[j][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("left",x2,y2,Color.red);
					stickArray[j][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("left",x2,y2,Color.orange);
					stickArray[j][k]=stick;
					break;
					default: stick= new stickerCreator("left",x2,y2,Color.orange); break;
				}
				y2-=56;
			}
			x2=xTemp2;y2=yTemp2;
			x2+=50;y2+=25;
			for(int k=9;k<12;k++)
			{
				switch(cube.getColor(j,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("top",x3,y3,Color.white);
					stickArray[j][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("top",x3,y3,Color.yellow);
					stickArray[j][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("top",x3,y3,Color.blue);
					stickArray[j][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("top",x3,y3,Color.green);
					stickArray[j][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("top",x3,y3,Color.red);
					stickArray[j][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("top",x3,y3,Color.orange);
					stickArray[j][k]=stick;
					break;
					default: stick= new stickerCreator("left",x3,y3,Color.orange); break;
				}
				y3+=25; x3+=50;
			}
			x3=xTemp3;y3=yTemp3;
			x3-=50;y3+=25;
			for(int k=6;k<9;k++)
			{
				switch(cube.getColor(j,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("right",x4,y4,Color.white);
					stickArray[j][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("right",x4,y4,Color.yellow);
					stickArray[j][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("right",x4,y4,Color.blue);
					stickArray[j][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("right",x4,y4,Color.green);
					stickArray[j][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("right",x4,y4,Color.red);
					stickArray[j][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("right",x4,y4,Color.orange);
					stickArray[j][k]=stick;
					break;
					default: stick= new stickerCreator("right",x4,y4,Color.orange); break;
				}
				y4+=56;
			}
			x4=xTemp4; y4=yTemp4;
			x4-=50; y4+=25;
		}
		for(int l=6; l<9;l++)
		{
			int xTemp5=x5, yTemp5=y5;
			for(int k=0;k<3;k++)
			{
				switch(cube.getColor(l,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("right",x5,y5,Color.white);
					stickArray[l][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("right",x5,y5,Color.yellow);
					stickArray[l][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("right",x5,y5,Color.blue);
					stickArray[l][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("right",x5,y5,Color.green);
					stickArray[l][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("right",x5,y5,Color.red);
					stickArray[l][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("right",x5,y5,Color.orange);
					stickArray[l][k]=stick;
					break;
					default: stick= new stickerCreator("right",x5,y5,Color.orange); break;
				}
				x5+=50;y5-=25;
			}
		x5=xTemp5; y5=yTemp5;
		y5+=56;
		}
	for(int l=0; l<3;l++)
		{
			int xTemp6=x6, yTemp6=y6;
			for(int k=0;k<3;k++)
			{
				switch(cube.getColor(l,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("left",x6,y6,Color.white);
					stickArray[l][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("left",x6,y6,Color.yellow);
					stickArray[l][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("left",x6,y6,Color.blue);
					stickArray[l][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("left",x6,y6,Color.green);
					stickArray[l][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("left",x6,y6,Color.red);
					stickArray[l][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("left",x6,y6,Color.orange);
					stickArray[l][k]=stick;
					break;
					default: stick= new stickerCreator("left",x6,y6,Color.orange); break;
				}
					y6-=25; x6-=50;
			}
		x6=xTemp6; y6=yTemp6;
		y6-=56;
		}
		for(stickerCreator[] sCArr : stickArray)
			for(stickerCreator sC : sCArr)
				sC.paintSticker(g);
	}
}
