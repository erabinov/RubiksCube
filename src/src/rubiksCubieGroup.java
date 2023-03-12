package src;
import java.util.*;
public class rubiksCubieGroup implements Group
{
	public CubiePermutation identityElement()
	{
		return new CubiePermutation();
	}
	public ArrayList<CubiePermutation> generator()
	{
		ArrayList<CubiePermutation> generatorList = new ArrayList<CubiePermutation>();
		ArrayList<LinkedList<CubieSpot>> upFace = new ArrayList<LinkedList<CubieSpot>>();
		LinkedList<CubieSpot> upCorners = new LinkedList<CubieSpot>();
		LinkedList<CubieSpot> upEdges = new LinkedList<CubieSpot>();
		upFace.add(upCorners); upFace.add(upEdges);
		int[] xArray = {0,0,2,2,1,0,1,2};
		int[] yArray = {2,2,2,2,2,2,2,2};
		int[] zArray = {0,2,2,0,0,1,2,1};
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
				if(i==0)
					upFace.get(i).add(new CubieSpot(xArray[4*i+j],yArray[4*i+j],zArray[4*i+j],3));
				else
					upFace.get(i).add(new CubieSpot(xArray[4*i+j],yArray[4*i+j],zArray[4*i+j],2));
		HashMap<CubieSpot,CubieSpot> upFaceMap = new HashMap<CubieSpot,CubieSpot>();
		for(LinkedList<CubieSpot> list: upFace)
		{
			for(int i=0;i<list.size();i++)
			{
				int index=(i-1+list.size())%list.size();
				upFaceMap.put(list.get(i),list.get(index));
			}
		}
		CubiePermutation upPerm= new CubiePermutation(upFaceMap);
		ArrayList<LinkedList<CubieSpot>> downFace = new ArrayList<LinkedList<CubieSpot>>();
		LinkedList<CubieSpot> downCorners = new LinkedList<CubieSpot>();
		LinkedList<CubieSpot> downEdges = new LinkedList<CubieSpot>();
		downFace.add(downCorners); downFace.add(downEdges);
		int[] xArray2 = {0,0,2,2,0,1,2,1};
		int[] yArray2 = {0,0,0,0,0,0,0,0};
		int[] zArray2 = {2,0,0,2,1,0,1,2};
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
				if(i==0)
					downFace.get(i).add(new CubieSpot(xArray2[4*i+j],yArray2[4*i+j],zArray2[4*i+j],3));
				else
					downFace.get(i).add(new CubieSpot(xArray2[4*i+j],yArray2[4*i+j],zArray2[4*i+j],2));
		HashMap<CubieSpot,CubieSpot> downFaceMap = new HashMap<CubieSpot,CubieSpot>();
		for(LinkedList<CubieSpot> list: downFace)
		{
			for(int i=0;i<list.size();i++)
			{
				int index=(i-1+list.size())%list.size();
				downFaceMap.put(list.get(i),list.get(index));
			}
		}
		CubiePermutation downPerm= new CubiePermutation(downFaceMap);
		HashMap<CubieSpot,CubieSpot> rightFace = new HashMap<CubieSpot,CubieSpot>();
		int[] xArray3 = {2,2,2,2,2,2,2,2};
		int[] yArray3 = {0,2,2,0,0,1,2,1};
		int[] zArray3 = {0,0,2,2,1,0,1,2};
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
				if(i==0&&j%2==0)
					rightFace.put(new CubieSpot(xArray3[4*i+j],yArray3[4*i+j],zArray3[4*i+j],3),new CubieSpot(xArray3[4*i+(j-1+4)%4],yArray3[4*i+(j-1+4)%4],zArray3[4*i+(j-1+4)%4],1,3));
				else if(i==0&&j%2==1)
					rightFace.put(new CubieSpot(xArray3[4*i+j],yArray3[4*i+j],zArray3[4*i+j],3),new CubieSpot(xArray3[4*i+(j-1+4)%4],yArray3[4*i+(j-1+4)%4],zArray3[4*i+(j-1+4)%4],2,3));
				else
					rightFace.put(new CubieSpot(xArray3[4*i+j],yArray3[4*i+j],zArray3[4*i+j],2),new CubieSpot(xArray3[4*i+(j-1+4)%4],yArray3[4*i+(j-1+4)%4],zArray3[4*i+(j-1+4)%4],2));
		CubiePermutation rightPerm= new CubiePermutation(rightFace);
		HashMap<CubieSpot,CubieSpot> leftFace = new HashMap<CubieSpot,CubieSpot>();
		int[] xArray4 = {0,0,0,0,0,0,0,0};
		int[] yArray4 = {0,0,2,2,0,1,2,1};
		int[] zArray4 = {0,2,2,0,1,2,1,0};
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
				if(i==0&&j%2==0)
					leftFace.put(new CubieSpot(xArray4[4*i+j],yArray4[4*i+j],zArray4[4*i+j],3),new CubieSpot(xArray4[4*i+(j-1+4+4)%4],yArray4[4*i+(j-1+4)%4],zArray4[4*i+(j-1+4)%4],2,3));
				else if(i==0&&j%2==1)
					leftFace.put(new CubieSpot(xArray4[4*i+j],yArray4[4*i+j],zArray4[4*i+j],3),new CubieSpot(xArray4[4*i+(j-1+4)%4],yArray4[4*i+(j-1+4)%4],zArray4[4*i+(j-1+4)%4],1,3));
				else
					leftFace.put(new CubieSpot(xArray4[4*i+j],yArray4[4*i+j],zArray4[4*i+j],2),new CubieSpot(xArray4[4*i+(j-1+4)%4],yArray4[4*i+(j-1+4)%4],zArray4[4*i+(j-1+4)%4],2));
		CubiePermutation leftPerm= new CubiePermutation(leftFace);
		HashMap<CubieSpot,CubieSpot> frontFace = new HashMap<CubieSpot,CubieSpot>();
		int[] xArray5 = {0,2,2,0,1,2,1,0};
		int[] yArray5 = {2,2,0,0,2,1,0,1};
		int[] zArray5 = {0,0,0,0,0,0,0,0};
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
			{
				int size, twist;
				if(i==0)
				{
					size=3;
					if(j%2==0)
						twist=2;
					else
						twist=1;
				}
				else
					{size=2; twist=1;}					
				CubieSpot newSpot = new CubieSpot(xArray5[4*i+j],yArray5[4*i+j],zArray5[4*i+j],size);
				CubieSpot replaced = new CubieSpot(xArray5[4*i+(j-1+4)%4],yArray5[4*i+(j-1+4)%4],zArray5[4*i+(j-1+4)%4],twist,size);
				frontFace.put(newSpot,replaced);
			}		
		CubiePermutation frontPerm= new CubiePermutation(frontFace);
		HashMap<CubieSpot,CubieSpot> backFace = new HashMap<CubieSpot,CubieSpot>();
		int[] xArray6 = {0,2,2,0,1,2,1,0};
		int[] yArray6 = {0,0,2,2,0,1,2,1};
		int[] zArray6 = {2,2,2,2,2,2,2,2};
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
			{
				int size, twist;
				if(i==0)
				{
					size=3;
					if(j%2==0)
						twist=2;
					else
						twist=1;
				}
				else
					{size=2; twist=1;}					
				CubieSpot newSpot = new CubieSpot(xArray6[4*i+j],yArray6[4*i+j],zArray6[4*i+j],size);
				CubieSpot replaced = new CubieSpot(xArray6[4*i+(j-1+4)%4],yArray6[4*i+(j-1+4)%4],zArray6[4*i+(j-1+4)%4],twist,size);
				backFace.put(newSpot,replaced);
			}					
		CubiePermutation backPerm= new CubiePermutation(backFace);
		//Adds the Permutations in the proper order so they correspond with the old numbering system of faceTurns.
		generatorList.add(upPerm);
		generatorList.add(frontPerm);
		generatorList.add(rightPerm);
		generatorList.add(leftPerm);
		generatorList.add(backPerm);
		generatorList.add(downPerm);
		return generatorList;
	}
	public ArrayList<CubiePermutation> axisTurns()
	{
		ArrayList<CubiePermutation> returnList = new ArrayList<CubiePermutation>();
		HashMap<CubieSpot, CubieSpot> xAxis = new HashMap<CubieSpot,CubieSpot>();
		int[][] allXCoords1 = {{0,0,0,0},{0,0,0,0},{1,1,1,1},{1,1,1,1},{2,2,2,2},{2,2,2,2}};
		int[][] allYCoords1 = {{2,2,0,0},{2,1,0,1},{2,2,0,0},{2,1,0,1},{2,2,0,0},{2,1,0,1}};
		int[][] allZCoords1 = {{0,2,2,0},{1,2,1,0},{0,2,2,0},{1,2,1,0},{0,2,2,0},{1,2,1,0}};
		int[][] allTwists1 = {{2,1,2,1},{0,0,0,0},{1,1,1,1},{0,0,0,0},{1,2,1,2},{0,0,0,0}};
		int [] sizes = {3,2,2,1,3,2};
		for(int i=0;i<allXCoords1.length;i++)
		{
			for(int j=0;j<allXCoords1[i].length;j++)
			{
				int index= (j+1)%allXCoords1[i].length;
				CubieSpot cs1 = new CubieSpot(allXCoords1[i][j],allYCoords1[i][j],allZCoords1[i][j],sizes[i]);
				CubieSpot cs2 = new CubieSpot(allXCoords1[i][index],allYCoords1[i][index],allZCoords1[i][index],allTwists1[i][index],sizes[i]);
				xAxis.put(cs1,cs2);
				
			}
		}
		returnList.add(new CubiePermutation(xAxis));
		HashMap<CubieSpot, CubieSpot> yAxis = new HashMap<CubieSpot,CubieSpot>();
		int[][] allXCoords2 = {{2,2,0,0},{2,1,0,1},{2,2,0,0},{1,2,1,0},{2,2,0,0},{2,1,0,1}};
		int[][] allYCoords2 = {{0,0,0,0},{0,0,0,0},{1,1,1,1},{1,1,1,1},{2,2,2,2},{2,2,2,2}};
		int[][] allZCoords2 = {{0,2,2,0},{1,2,1,0},{0,2,2,0},{0,1,2,1},{0,2,2,0},{1,2,1,0}};
		int[][] allTwists2 = {{0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		int [] sizes2 = {3,2,2,1,3,2};
		for(int i=0;i<allXCoords2.length;i++)
		{
			for(int j=0;j<allXCoords2[i].length;j++)
			{
				int index = (j+1)%allXCoords2[i].length;
				CubieSpot cs1 = new CubieSpot(allXCoords2[i][j],allYCoords2[i][j],allZCoords2[i][j],sizes2[i]);
				CubieSpot cs2 = new CubieSpot(allXCoords2[i][index],allYCoords2[i][index],allZCoords2[i][index],allTwists2[i][index],sizes2[i]);
				yAxis.put(cs1,cs2);
			}
		}
		returnList.add(new CubiePermutation(yAxis));
		HashMap<CubieSpot, CubieSpot> zAxis = new HashMap<CubieSpot,CubieSpot>();
		int[][] allXCoords3 = {{2,2,0,0},{2,1,0,1},{2,2,0,0},{2,1,0,1},{2,2,0,0},{2,1,0,1}};
		int[][] allYCoords3 = {{0,2,2,0},{1,2,1,0},{0,2,2,0},{1,2,1,0},{0,2,2,0},{1,2,1,0}};
		int[][] allZCoords3 = {{0,0,0,0},{0,0,0,0},{1,1,1,1},{1,1,1,1},{2,2,2,2},{2,2,2,2}};
		int[][] allTwists3 = {{1,2,1,2},{1,1,1,1},{1,1,1,1},{0,0,0,0},{2,1,2,1},{1,1,1,1}};
		int [] sizes3 = {3,2,2,1,3,2};
		for(int i=0;i<allXCoords3.length;i++)
		{
			for(int j=0;j<allXCoords3[i].length;j++)
			{
				int index = (j+1)%allXCoords3[i].length;
				CubieSpot cs1 = new CubieSpot(allXCoords3[i][j],allYCoords3[i][j],allZCoords3[i][j],sizes3[i]);
				CubieSpot cs2 = new CubieSpot(allXCoords3[i][index],allYCoords3[i][index],allZCoords3[i][index],allTwists3[i][index],sizes3[i]);
				zAxis.put(cs1,cs2);
			}
		}
		returnList.add(new CubiePermutation(zAxis));
		return returnList;
	}
}
