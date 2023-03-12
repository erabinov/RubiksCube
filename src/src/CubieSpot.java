//A three-dimensional co-ordinate for cubies
//Contains the static method that associates CubieSpot objects with the CubeSpot (sticker) objects that are on the same cubie.
package src;
import java.util.*;
public class CubieSpot
{
	private int x;
	private int y;
	private int z;
	private int twist;
	private int size;
	public CubieSpot(int one, int two, int three,int sz)
	{
		x=one; y=two; z=three; twist=0; size=sz;
	}
	public CubieSpot(int one, int two, int three, int four, int sz)
	{
		x=one; y=two; z=three;  if(sz!=0){twist=(four%sz);} size=sz;
	}
	public int getX(){ return x;}
	public int getSize(){ return size;}
	public int getY(){ return y;}
	public int getZ(){ return z;}
	//Maps all cubies with three dimensional coordinates to the two-dimensional coordinates of the stickers that make up each cubie.
	public static HashMap<CubieSpot, ArrayList<CubeSpot>> spotMap()
	{
		HashMap<CubieSpot, ArrayList<CubeSpot>> returnMap = new HashMap<CubieSpot, ArrayList<CubeSpot>>();
		int[] rowArray = {4,4,5,6,4,4,3,2,7,5,1,3,1,3,7,5,4,4,5,8,4,4,3,0};
		int[] colArray = {3,2,4,1,5,6,4,1,2,7,2,7,0,1,0,1,9,8,10,1,11,0,10,1};
		int[] xArray = {0,1,2,1,2,2,0,0,2,1,0,1};
		int[] yArray = {2,2,2,2,1,1,1,1,0,0,0,0};
		int[] zArray = {1,0,1,2,0,2,2,0,1,0,1,2};
		for(int traversal=0;traversal<xArray.length;traversal++)
		{
			ArrayList<CubeSpot> list = new ArrayList<CubeSpot>();
			int correspond = 2*traversal;
			list.add(new CubeSpot(rowArray[correspond],colArray[correspond]));
			list.add(new CubeSpot(rowArray[correspond+1],colArray[correspond+1]));
			returnMap.put(new CubieSpot(xArray[traversal],yArray[traversal],zArray[traversal],2),list);
		}
		int[] newRowArray = {4,4,4,4,1,7};
		int[] newColArray = {1,4,7,10,1,1};
		int[] xArray2 = {0,1,2,1,1,1};
		int[] yArray2 = {1,2,1,0,1,1};
		int[] zArray2 = {1,1,1,1,2,0};
		for(int i=0;i<xArray2.length;i++)
		{
			ArrayList<CubeSpot> list = new ArrayList<CubeSpot>();
			list.add(new CubeSpot(newRowArray[i],newColArray[i]));
			returnMap.put(new CubieSpot(xArray2[i],yArray2[i],zArray2[i],1),list);
		}
		int[] RowArray3 = {3,2,3,5,5,6,5,6,5,3,3,2,3,3,0,5,8,5,5,5,8,3,0,3};
		int[] ColArray3 = {5,2,6,5,6,2,3,0,2,3,2,0,9,8,2,9,2,8,11,0,0,11,0,0};
		int[] xArray3 = {2,2,0,0,2,2,0,0};
		int[] yArray3 = {2,2,2,2,0,0,0,0};
		int[] zArray3 = {2,0,0,2,2,0,0,2};
		for(int i=0;i<xArray3.length;i++)
		{
			ArrayList<CubeSpot> list = new ArrayList<CubeSpot>();
			int corr = 3*i;
			list.add(new CubeSpot(RowArray3[corr],ColArray3[corr]));
			list.add(new CubeSpot(RowArray3[corr+1],ColArray3[corr+1]));
			list.add(new CubeSpot(RowArray3[corr+2],ColArray3[corr+2]));
			returnMap.put(new CubieSpot(xArray3[i],yArray3[i],zArray3[i],3),list);
		}
		returnMap.put(new CubieSpot(1,1,1,-1),null);
		return returnMap;
	}
	public boolean equals(Object other)
	{
		if(!(other instanceof CubieSpot)||other==null)
			return false;
		else
		{
			return x==((CubieSpot)other).x && y==((CubieSpot)other).y && z==((CubieSpot)other).z /*&& twist == ((CubieSpot)other).twist*/;
		}
	}
	public int getTwist()
	{
		return twist;
	}
	public void twist(int tw)
	{
		twist=(twist+tw)%size;
	}
	public String toString()
	{
		String s = "";
		s+="{"+x+","+y+","+z+"}: Twist: "+twist;
		return s;
	}
	public int hashCode()
	{
		return x*100+y*10+z;
	}
}