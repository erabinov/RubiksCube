package src;
import java.util.*;
public class CubieCoordinate 
{
	protected CubiePermutation perm;
	protected int cornerOrient;
	protected int edgeOrient;
	protected int cornerPerm;
	protected int edgePerm;
	protected int UDCoord;
	public CubieCoordinate(CubiePermutation permutation)
	{
		createCoord(permutation);
	}
	public int getCornerOrient(){return cornerOrient;}
	public int getEdgeOrient(){return edgeOrient;}
	public int getCornerPerm(){return cornerPerm;}
	public int getEdgePerm(){return edgePerm;}
	public int getUDCoord(){return UDCoord;}
	//Arranges the corners in a hierarchy in the following order
	//URF<UFL<ULB<UBR<DFR<DLF<DBL<DRB
	public static LinkedList<CubieSpot> cornerHierarchy() 
	{
		LinkedList<CubieSpot> returnList=new LinkedList<CubieSpot>();
		returnList.add(new CubieSpot(2,2,0,3));
		returnList.add(new CubieSpot(0,2,0,3));
		returnList.add(new CubieSpot(0,2,2,3));
		returnList.add(new CubieSpot(2,2,2,3));
		returnList.add(new CubieSpot(2,0,0,3));
		returnList.add(new CubieSpot(0,0,0,3));
		returnList.add(new CubieSpot(0,0,2,3));
		returnList.add(new CubieSpot(2,0,2,3));
		return returnList;
	}
	//Arranges the edges in a hierarchy in the following order
	//UR<UF<UL<UB<DR<DF<DL<DB<FR<FL<BL<BR
	public static LinkedList<CubieSpot> edgeHierarchy()
	{
		LinkedList<CubieSpot> returnList = new LinkedList<CubieSpot>();
		returnList.add(new CubieSpot(2,2,1,2));
		returnList.add(new CubieSpot(1,2,0,2));
		returnList.add(new CubieSpot(0,2,1,2));
		returnList.add(new CubieSpot(1,2,2,2));
		returnList.add(new CubieSpot(2,0,1,2));
		returnList.add(new CubieSpot(1,0,0,2));
		returnList.add(new CubieSpot(0,0,1,2));
		returnList.add(new CubieSpot(1,0,2,2));
		returnList.add(new CubieSpot(2,1,0,2));
		returnList.add(new CubieSpot(0,1,0,2));
		returnList.add(new CubieSpot(0,1,2,2));
		returnList.add(new CubieSpot(2,1,2,2));
		return returnList;
	}
	//Creates a coordinate based on the permutation that is passed in as a parameter.
	//cornerOrient is an int that represents the twists on the corners, and edgeOrient represents the orientation of the edges.
	//the Perm coordinates represent the arrangement of the cubies on the cube.
	public void createCoord(CubiePermutation permutation)
	{
		perm = permutation;
		cornerOrient=0;
		edgeOrient=0;
		cornerPerm=0;
		edgePerm=0;
		UDCoord=0;
		List<CubieSpot> cornerHierarchy = cornerHierarchy();
		List<CubieSpot> edgeHierarchy = edgeHierarchy();
		for(int i=6;i>=0;i--)
		{
			cornerOrient+=perm.get(cornerHierarchy.get(7-i-1)).getTwist()*Math.pow(3,i);
		}
		for(int i=10;i>=0;i--)
		{
			edgeOrient+=perm.get(edgeHierarchy.get(11-i-1)).getTwist()*Math.pow(2,i);
		}
		for(int i=1;i<=7;i++)
		{
			int count=0;
			for(int j=0;j<i;j++)
			{
				if(cornerHierarchy.indexOf(perm.get(cornerHierarchy.get(j)))>cornerHierarchy.indexOf(perm.get(cornerHierarchy.get(i))))
					count++;
			}
			cornerPerm+=count*factorial(i);
		}
		for(int i=1;i<=11;i++)
		{
			int count=0;
			for(int j=0;j<i;j++)
			{
				if(edgeHierarchy.indexOf(perm.get(edgeHierarchy.get(j)))>edgeHierarchy.indexOf(perm.get(edgeHierarchy.get(i-1))))
					count++;
			}
			edgePerm+=count*factorial(i);
		}
		int k=-1;
		for(int i=0;i<edgeHierarchy.size();i++)
		{
			if(edgeHierarchy.indexOf(perm.get(edgeHierarchy.get(i)))>=8)
				k++;
			else if(k>=0)
				UDCoord+= factorial(i)/(factorial(k)*factorial(i-k));
		}
	}
	protected int factorial(int n)
	{
		if(n==0)
			return 1;
		else
			return n*factorial(n-1);
	}
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(!(obj instanceof CubieCoordinate))
			return false;
		CubieCoordinate comparing = ((CubieCoordinate)obj);
		return comparing.edgeOrient==edgeOrient&&comparing.cornerOrient==cornerOrient&&comparing.edgePerm==edgePerm&&comparing.cornerPerm==cornerPerm;
	}
	public int hashCode()
	{
		return (int)(Math.pow(10,22)*cornerOrient+Math.pow(10,18)*edgeOrient+Math.pow(10,14)*cornerPerm+Math.pow(10,9)*edgePerm);
	}
	public String toString()
	{
		return "("+cornerOrient+","+edgeOrient+","+cornerPerm+","+edgePerm+/*","+/*UDCoord+*/")";
	}
}
