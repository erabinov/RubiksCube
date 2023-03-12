package src;
import javax.swing.*;
import java.awt.*;
public class panelExtension extends JPanel{
	private rubiksCube cube;
	private stickerCreator[][] stickArray;
	public panelExtension(Color backColor, rubiksCube rc)
	{
		setBackground(backColor);
		cube = rc;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
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

	}
	public void assignment(Graphics g)
	{
		super.paintComponent(g);
		//Populates the cells with stickerCreator objects.
		cube.faceTurn(3);
		int x=50, y=175;
		int x2=150, y2=275;
		int x3=600, y3=293, x4=x3+50, y4=150;
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
			for(int k=2;k>=0;k--)
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
				y2+=56;
			}
			x2=xTemp2;y2=yTemp2;
			x2-=50;y2-=25;
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
				y3+=25; x3-=50;
			}
			x3=xTemp3;y3=yTemp3;
			x3+=50;y3+=25;
			for(int k=6;k<9;k++)
			{
				switch(cube.getColor(j,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("left",x4,y4,Color.white);
					stickArray[j][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("left",x4,y4,Color.yellow);
					stickArray[j][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("left",x4,y4,Color.blue);
					stickArray[j][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("left",x4,y4,Color.green);
					stickArray[j][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("left",x,y4,Color.red);
					stickArray[j][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("left",x4,y4,Color.orange);
					stickArray[j][k]=stick;
					break;
					default: stick= new stickerCreator("left",x,y4,Color.orange); break;
				}
				x4+=50;y4+=25;
			}
			x4=xTemp4; y4=yTemp4;
			y4+=56;
		}
		x=150; y=275;
		for(int l=6; l<9;l++)
		{
			int xTemp=x, yTemp=y;
			for(int k=0;k<3;k++)
			{
				switch(cube.getColor(l,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("right",x,y,Color.white);
					stickArray[l][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("right",x,y,Color.yellow);
					stickArray[l][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("right",x,y,Color.blue);
					stickArray[l][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("right",x,y,Color.green);
					stickArray[l][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("right",x,y,Color.red);
					stickArray[l][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("right",x,y,Color.orange);
					stickArray[l][k]=stick;
					break;
					default: stick= new stickerCreator("right",x,y,Color.orange); break;
				}
				x+=50;y-=25;
			}
		x=xTemp; y=yTemp;
		y+=56;
		}
	x=450; y=200;
	for(int l=0; l<3;l++)
		{
			int xTemp=x, yTemp=y;
			for(int k=0;k<3;k++)
			{
				switch(cube.getColor(l,k))
				{
					case 'W':
					stickerCreator stick= new stickerCreator("right",x,y,Color.white);
					stickArray[l][k]=stick;
					break;
					case 'Y':
					stick= new stickerCreator("right",x,y,Color.yellow);
					stickArray[l][k]=stick;					
					break;
					case 'B':
					stick= new stickerCreator("right",x,y,Color.blue);
					stickArray[l][k]=stick;					
					break;
					case 'G':
					stick= new stickerCreator("right",x,y,Color.green);
					stickArray[l][k]=stick;					
					break;
					case 'R':
					stick= new stickerCreator("right",x,y,Color.red);
					stickArray[l][k]=stick;					
					break;
					case 'O':
					stick= new stickerCreator("right",x,y,Color.orange);
					stickArray[l][k]=stick;
					break;
					default: stick= new stickerCreator("right",x,y,Color.orange); break;
				}
				x+=50;y-=25;
			}
		x=xTemp; y=yTemp;
		y+=56;
		}
	}
}
