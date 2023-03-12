package src;
import java.awt.*;
import javax.swing.*;
public class stickerCreator
{
	private Color fillColor;
	private Polygon outline;
	private int [] xArray=new int [4],yArray = new int [4];
	private String type;
	private int x,y;
	public stickerCreator(String type,int x,int y, Color fillType)
	{
		this.type = type;
		fillColor=fillType;
		this.x=x;
		this.y=y;
	}
	public void paintSticker(Graphics g)
	{
		g.setColor(Color.black);
		if(type.equals("top"))
		{
			xArray[0] = x;
			xArray[1] = x+50;
			xArray[2] = x;
			xArray[3] = x-50;	
			yArray[0] = y;
			yArray[1] = y+25;
			yArray[2] = y+50;
			yArray[3] = y+25;
			for(int i=1;i<=5;i++)
			{
	
				outline=new Polygon(xArray, yArray, 4);
				g.drawPolygon(outline);
				xArray[1] -=2;
				xArray[3] +=2;
				yArray[0] ++;		
				yArray[2] --;
			}
			g.setColor(fillColor);
			g.fillPolygon(outline);
		}
		if(type.equals("right"))
		{
			xArray[0] = x;
			xArray[1] = x+50;
			xArray[2] = x+50;
			xArray[3] = x;
			yArray[0] = y;		
			yArray[1] = y-25;
			yArray[2] = y+31;
			yArray[3] = y+56;
			for(int i=1;i<=5;i++)
			{
				outline=new Polygon(xArray, yArray, 4);
				g.drawPolygon(outline);
				xArray[0] ++;
				xArray[1] --;
				xArray[2] --;
				xArray[3] ++;
				yArray[0] ++;
				yArray[1] +=2;		
				yArray[2] --;
				yArray[3] -=2;
			}
			g.setColor(fillColor);
			g.fillPolygon(outline);
		}
		if(type.equals("left"))
		{
			xArray[0] = x;
			xArray[1] = xArray[0];
			xArray[2] = xArray[0]-50;
			xArray[3] = xArray[0]-50;		
			yArray[0] = y;
			yArray[1] = yArray[0]+56;
			yArray[2] = yArray[0]+31;
			yArray[3] = yArray[0]-25;
			for(int i=1;i<=5;i++)
			{
				outline=new Polygon(xArray, yArray, 4);
				g.drawPolygon(outline);
				xArray[0] --;
				xArray[1] --;
				xArray[2] ++;
				xArray[3] ++;
				yArray[0] ++;
				yArray[1] -=2;		
				yArray[2] --;
				yArray[3] +=2;
			}
			g.setColor(fillColor);
			g.fillPolygon(outline);
		}
		if(type.equals("square"))
		{
			xArray[0] = x;
			xArray[1] = xArray[0];
			xArray[2] = xArray[0]+50;
			xArray[3] = xArray[0]+50;		
			yArray[0] = y;
			yArray[1] = yArray[0]+50;
			yArray[2] = yArray[0]+50;
			yArray[3] = yArray[0];
			for(int i=1;i<=5;i++)
			{
				outline=new Polygon(xArray, yArray, 4);
				g.drawPolygon(outline);
				xArray[0] ++;
				xArray[1] ++;
				xArray[2] --;
				xArray[3] --;
				yArray[0] ++;
				yArray[1] --;		
				yArray[2] --;
				yArray[3] ++;
			}
			g.setColor(fillColor);
			g.fillPolygon(outline);
		}
	}
	public Color getColor(){return fillColor;}
	public void changeColor(Color c)
	{
		fillColor=c;
	}
}

