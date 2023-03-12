package src;

public class CubeSpot 
{
	private int row; private int col;
	public CubeSpot(int r, int c)
	{
		row=r; col=c;
	}
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	public boolean equals(Object obj)
	{
		if (obj==this)
			return true;
		if(!(obj instanceof CubeSpot))
			return false;
		return ((CubeSpot)obj).row==row&&((CubeSpot)obj).col==col;
		
	}
	public int hashCode()
	{
		return row*1000+col*10;
	}
	public String toString()
	{
		return "("+row+","+col+")";
	}
}
