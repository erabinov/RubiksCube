package src;
import java.util.*;
public class rubiksCubeGroup implements Group<Permutation<CubeSpot>>
{
	public rubiksCubeGroup()
	{
	}
	public Permutation<CubeSpot> identityElement()
	{
		return new Permutation<CubeSpot>(new HashMap<CubeSpot,CubeSpot>());
	}
	public ArrayList<Permutation<CubeSpot>> generator()
	{
		ArrayList<Permutation<CubeSpot>> generatorList = new ArrayList<Permutation<CubeSpot>>();
		//Up face permutations of stickers.
		HashMap<CubeSpot,CubeSpot> upFace = new HashMap<CubeSpot,CubeSpot>();
		upFace.put(new CubeSpot(5,5),new CubeSpot(3,5));
		upFace.put(new CubeSpot(3,5),new CubeSpot(3,3));
		upFace.put(new CubeSpot(3,3),new CubeSpot(5,3));
		upFace.put(new CubeSpot(5,3),new CubeSpot(5,5));
		
		upFace.put(new CubeSpot(5,4),new CubeSpot(4,5));
		upFace.put(new CubeSpot(4,5),new CubeSpot(3,4));
		upFace.put(new CubeSpot(3,4),new CubeSpot(4,3));
		upFace.put(new CubeSpot(4,3),new CubeSpot(5,4));
		
		upFace.put(new CubeSpot(6,1),new CubeSpot(4,6));
		upFace.put(new CubeSpot(4,6),new CubeSpot(2,1));
		upFace.put(new CubeSpot(2,1),new CubeSpot(4,2));
		upFace.put(new CubeSpot(4,2),new CubeSpot(6,1));
		
		upFace.put(new CubeSpot(6,0),new CubeSpot(5,6));
		upFace.put(new CubeSpot(5,6),new CubeSpot(2,2));
		upFace.put(new CubeSpot(2,2),new CubeSpot(3,2));
		upFace.put(new CubeSpot(3,2),new CubeSpot(6,0));
		
		upFace.put(new CubeSpot(6,2),new CubeSpot(3,6));
		upFace.put(new CubeSpot(3,6),new CubeSpot(2,0));
		upFace.put(new CubeSpot(2,0),new CubeSpot(5,2));
		upFace.put(new CubeSpot(5,2),new CubeSpot(6,2));
		generatorList.add(new Permutation<CubeSpot>(upFace));
		//Front face cycles of stickers.
		HashMap<CubeSpot,CubeSpot> frontFace = new HashMap<CubeSpot,CubeSpot>();
		frontFace.put(new CubeSpot(7,0),new CubeSpot(8,1));
		frontFace.put(new CubeSpot(8,1),new CubeSpot(7,2));
		frontFace.put(new CubeSpot(7,2),new CubeSpot(6,1));
		frontFace.put(new CubeSpot(6,1),new CubeSpot(7,0));
		
		frontFace.put(new CubeSpot(8,0),new CubeSpot(8,2));
		frontFace.put(new CubeSpot(8,2),new CubeSpot(6,2));
		frontFace.put(new CubeSpot(6,2),new CubeSpot(6,0));
		frontFace.put(new CubeSpot(6,0),new CubeSpot(8,0));
		
		frontFace.put(new CubeSpot(5,10),new CubeSpot(5,7));
		frontFace.put(new CubeSpot(5,7),new CubeSpot(5,4));
		frontFace.put(new CubeSpot(5,4),new CubeSpot(5,1));
		frontFace.put(new CubeSpot(5,1),new CubeSpot(5,10));
		
		frontFace.put(new CubeSpot(5,11),new CubeSpot(5,8));
		frontFace.put(new CubeSpot(5,8),new CubeSpot(5,5));
		frontFace.put(new CubeSpot(5,5),new CubeSpot(5,2));
		frontFace.put(new CubeSpot(5,2),new CubeSpot(5,11));
		
		frontFace.put(new CubeSpot(5,9),new CubeSpot(5,6));
		frontFace.put(new CubeSpot(5,6),new CubeSpot(5,3));
		frontFace.put(new CubeSpot(5,3),new CubeSpot(5,0));
		frontFace.put(new CubeSpot(5,0),new CubeSpot(5,9));
		generatorList.add(new Permutation<CubeSpot>(frontFace));
		//Right face cycles
		int [] rightCo = {5,7,4,8,4,8,3,7,3,7,4,6,4,6,5,7,5,6,5,8,5,8,3,8,3,8,3,6,3,6,5,6,5,5,8,2,8,2,3,9,3,9,2,2,2,2,5,5,6,2,5,9,5,9,0,2,0,2,3,5,3,5,6,2,7,2,4,9,4,9,1,2,1,2,4,5,4,5,7,2};
		HashMap<CubeSpot,CubeSpot> rightFace = new HashMap<CubeSpot,CubeSpot>();
		for(int i=0;i<rightCo.length;i+=4)
		{
			rightFace.put(new CubeSpot(rightCo[i],rightCo[i+1]),new CubeSpot(rightCo[i+2],rightCo[i+3]));
		}
		generatorList.add(new Permutation<CubeSpot>(rightFace));
		//left face cycles
		int [] leftCo = {4,0,5,1,5,1,4,2,4,2,3,1,3,1,4,0,5,0,5,2,5,2,3,2,3,2,3,0,3,0,5,0,7,0,4,3,4,3,1,0,1,0,4,11,4,11,7,0,8,0,5,3,5,3,2,0,2,0,3,11,3,11,8,0,6,0,3,3,3,3,0,0,0,0,5,11,5,11,6,0};
		HashMap<CubeSpot,CubeSpot> leftFace = new HashMap<CubeSpot,CubeSpot>();
		for(int i=0;i<leftCo.length;i+=4)
		{
			leftFace.put(new CubeSpot(leftCo[i],leftCo[i+1]),new CubeSpot(leftCo[i+2],leftCo[i+3]));
		}
		generatorList.add(new Permutation<CubeSpot>(leftFace));
		//Back face cycles
		int [] backCo = {2,0,2,2,2,2,0,2,0,2,0,0,0,0,2,0,1,0,2,1,2,1,1,2,1,2,0,1,0,1,1,0,3,2,3,5,3,5,3,8,3,8,3,11,3,11,3,2,3,0,3,3,3,3,3,6,3,6,3,9,3,9,3,0,3,1,3,4,3,4,3,7,3,7,3,10,3,10,3,1};
		HashMap<CubeSpot,CubeSpot> backFace = new HashMap<CubeSpot,CubeSpot>();
		for(int i=0;i<backCo.length;i+=4)
		{
			backFace.put(new CubeSpot(backCo[i],backCo[i+1]),new CubeSpot(backCo[i+2],backCo[i+3]));
		}
		generatorList.add(new Permutation<CubeSpot>(backFace));
		//Down face cycles
		int [] downCo = {3,9,5,9,5,9,5,11,5,11,3,11,3,11,3,9,4,9,5,10,5,10,4,11,4,11,3,10,3,10,4,9,5,8,8,0,8,0,3,0,3,0,0,2,0,2,5,8,3,8,8,2,8,2,5,0,5,0,0,0,0,0,3,8,4,8,8,1,8,1,4,0,4,0,0,1,0,1,4,8};
		HashMap<CubeSpot,CubeSpot> downFace = new HashMap<CubeSpot,CubeSpot>();
		for(int i=0;i<downCo.length;i+=4)
		{
			downFace.put(new CubeSpot(downCo[i],downCo[i+1]),new CubeSpot(downCo[i+2],downCo[i+3]));
		}
		generatorList.add(new Permutation<CubeSpot>(downFace));
		return generatorList;
	}
	public ArrayList<Permutation<CubeSpot>> axisTurns()
	{
		ArrayList<Permutation<CubeSpot>> generatorList = new ArrayList<Permutation<CubeSpot>>();
		//X-axis permutations of stickers.
		HashMap<CubeSpot,CubeSpot> xAxis = new HashMap<CubeSpot,CubeSpot>();
		int[] xAxCo = {3,3,6,0,6,0,5,11,5,11,0,0,0,0,3,3,5,3,8,0,8,0,3,11,3,
		11,2,0,2,0,5,3,6,2,5,9,5,9,0,2,0,2,3,5,3,5,6,2,8,2,3,
		9,3,9,2,2,2,2,5,5,5,5,8,2,4,3,7,0,7,0,4,11,4,11,1,0,1,0,4,3,
		7,2,4,9,4,9,1,2,1,2,4,5,4,5,7,2,
		5,4,8,1,8,1,3,10,3,10,2,1,2,1,5,4,
		3,4,6,1,6,1,5,10,5,10,0,1,0,1,3,4,
		4,4,7,1,7,1,4,10,4,10,1,1,1,1,4,4,
		3,2,5,2,5,2,5,0,5,0,3,0,3,0,3,2,
		3,1,4,2,4,2,5,1,5,1,4,0,4,0,3,1,
		5,6,5,8,5,8,3,8,3,8,3,6,3,6,5,6,
		4,6,5,7,5,7,4,8,4,8,3,7,3,7,4,6};
		for(int i=0;i<xAxCo.length;i+=4)
		{
			xAxis.put(new CubeSpot(xAxCo[i+2],xAxCo[i+3]),new CubeSpot(xAxCo[i],xAxCo[i+1]));
		}
		generatorList.add(new Permutation<CubeSpot>(xAxis));
		//Y-axis permutations of stickers.
		ArrayList<LinkedList<CubeSpot>> yAxis = new ArrayList<LinkedList<CubeSpot>>();
		HashMap<CubeSpot, CubeSpot> yAxis1 = new HashMap<CubeSpot,CubeSpot>();
		LinkedList<CubeSpot> subList21 = new LinkedList<CubeSpot>();
		subList21.add(new CubeSpot(3,6));
		subList21.add(new CubeSpot(6,2));
		subList21.add(new CubeSpot(5,2));
		subList21.add(new CubeSpot(2,0));
		LinkedList<CubeSpot> subList22 = new LinkedList<CubeSpot>();
		subList22.add(new CubeSpot(5,6));
		subList22.add(new CubeSpot(6,0));
		subList22.add(new CubeSpot(3,2));
		subList22.add(new CubeSpot(2,2));
		LinkedList<CubeSpot> subList23 = new LinkedList<CubeSpot>();
		subList23.add(new CubeSpot(3,8));
		subList23.add(new CubeSpot(8,2));
		subList23.add(new CubeSpot(5,0));
		subList23.add(new CubeSpot(0,0));
		LinkedList<CubeSpot> subList24 = new LinkedList<CubeSpot>();
		subList24.add(new CubeSpot(5,8));
		subList24.add(new CubeSpot(8,0));
		subList24.add(new CubeSpot(3,0));
		subList24.add(new CubeSpot(0,2));
		LinkedList<CubeSpot> subList25 = new LinkedList<CubeSpot>();
		subList25.add(new CubeSpot(4,6));
		subList25.add(new CubeSpot(6,1));
		subList25.add(new CubeSpot(4,2));
		subList25.add(new CubeSpot(2,1));
		LinkedList<CubeSpot> subList26 = new LinkedList<CubeSpot>();
		subList26.add(new CubeSpot(4,8));
		subList26.add(new CubeSpot(8,1));
		subList26.add(new CubeSpot(4,0));
		subList26.add(new CubeSpot(0,1));
		LinkedList<CubeSpot> subList27 = new LinkedList<CubeSpot>();
		subList27.add(new CubeSpot(5,7));
		subList27.add(new CubeSpot(7,0));
		subList27.add(new CubeSpot(3,1));
		subList27.add(new CubeSpot(1,2));
		LinkedList<CubeSpot> subList28 = new LinkedList<CubeSpot>();
		subList28.add(new CubeSpot(3,7));
		subList28.add(new CubeSpot(7,2));
		subList28.add(new CubeSpot(5,1));
		subList28.add(new CubeSpot(1,0));
		LinkedList<CubeSpot> subList29 = new LinkedList<CubeSpot>();
		subList29.add(new CubeSpot(4,1));
		subList29.add(new CubeSpot(1,1));
		subList29.add(new CubeSpot(4,7));
		subList29.add(new CubeSpot(7,1));
		LinkedList<CubeSpot> subList210 = new LinkedList<CubeSpot>();
		subList210.add(new CubeSpot(3,3));
		subList210.add(new CubeSpot(3,5));
		subList210.add(new CubeSpot(5,5));
		subList210.add(new CubeSpot(5,3));
		LinkedList<CubeSpot> subList211 = new LinkedList<CubeSpot>();
		subList211.add(new CubeSpot(4,3));
		subList211.add(new CubeSpot(3,4));
		subList211.add(new CubeSpot(4,5));
		subList211.add(new CubeSpot(5,4));
		LinkedList<CubeSpot> subList212 = new LinkedList<CubeSpot>();
		subList212.add(new CubeSpot(5,9));
		subList212.add(new CubeSpot(5,11));
		subList212.add(new CubeSpot(3,11));
		subList212.add(new CubeSpot(3,9));
		LinkedList<CubeSpot> subList213 = new LinkedList<CubeSpot>();
		subList213.add(new CubeSpot(5,10));
		subList213.add(new CubeSpot(4,11));
		subList213.add(new CubeSpot(3,10));
		subList213.add(new CubeSpot(4,9));
		yAxis.add(subList21);yAxis.add(subList22);yAxis.add(subList23);yAxis.add(subList24);yAxis.add(subList25);
		yAxis.add(subList26);yAxis.add(subList27);yAxis.add(subList28);yAxis.add(subList29);yAxis.add(subList210);
		yAxis.add(subList211);yAxis.add(subList212);yAxis.add(subList213);
		for(LinkedList<CubeSpot> list:yAxis)
		{
			for(int i=0;i<list.size();i++)
			{
				int index = (i-1+list.size())%list.size();
				yAxis1.put(list.get(i),list.get(index));
			}
		}
		generatorList.add(new Permutation<CubeSpot>(yAxis1));
		//Z-Axis Permutations of stickers.
		ArrayList<LinkedList<CubeSpot>> zAxis = new ArrayList<LinkedList<CubeSpot>>();
		HashMap<CubeSpot,CubeSpot> zAxis1 = new HashMap<CubeSpot,CubeSpot>();
		LinkedList<CubeSpot> subList31 = new LinkedList<CubeSpot>();
		subList31.add(new CubeSpot(5,3));
		subList31.add(new CubeSpot(5,6));
		subList31.add(new CubeSpot(5,9));
		subList31.add(new CubeSpot(5,0));
		LinkedList<CubeSpot> subList32 = new LinkedList<CubeSpot>();
		subList32.add(new CubeSpot(5,5));
		subList32.add(new CubeSpot(5,8));
		subList32.add(new CubeSpot(5,11));
		subList32.add(new CubeSpot(5,2));
		LinkedList<CubeSpot> subList33 = new LinkedList<CubeSpot>();
		subList33.add(new CubeSpot(3,3));
		subList33.add(new CubeSpot(3,6));
		subList33.add(new CubeSpot(3,9));
		subList33.add(new CubeSpot(3,0));
		LinkedList<CubeSpot> subList34 = new LinkedList<CubeSpot>();
		subList34.add(new CubeSpot(3,5));
		subList34.add(new CubeSpot(3,8));
		subList34.add(new CubeSpot(3,11));
		subList34.add(new CubeSpot(3,2));
		LinkedList<CubeSpot> subList35 = new LinkedList<CubeSpot>();
		subList35.add(new CubeSpot(5,4));
		subList35.add(new CubeSpot(5,7));
		subList35.add(new CubeSpot(5,10));
		subList35.add(new CubeSpot(5,1));
		LinkedList<CubeSpot> subList36 = new LinkedList<CubeSpot>();
		subList36.add(new CubeSpot(3,4));
		subList36.add(new CubeSpot(3,7));
		subList36.add(new CubeSpot(3,10));
		subList36.add(new CubeSpot(3,1));
		LinkedList<CubeSpot> subList37 = new LinkedList<CubeSpot>();
		subList37.add(new CubeSpot(4,2));
		subList37.add(new CubeSpot(4,5));
		subList37.add(new CubeSpot(4,8));
		subList37.add(new CubeSpot(4,11));
		LinkedList<CubeSpot> subList38 = new LinkedList<CubeSpot>();
		subList38.add(new CubeSpot(4,0));
		subList38.add(new CubeSpot(4,3));
		subList38.add(new CubeSpot(4,6));
		subList38.add(new CubeSpot(4,9));
		LinkedList<CubeSpot> subList39 = new LinkedList<CubeSpot>();
		subList39.add(new CubeSpot(4,1));
		subList39.add(new CubeSpot(4,4));
		subList39.add(new CubeSpot(4,7));
		subList39.add(new CubeSpot(4,10));
		LinkedList<CubeSpot> subList310 = new LinkedList<CubeSpot>();
		subList310.add(new CubeSpot(6,0));
		subList310.add(new CubeSpot(6,2));
		subList310.add(new CubeSpot(8,2));
		subList310.add(new CubeSpot(8,0));
		LinkedList<CubeSpot> subList311 = new LinkedList<CubeSpot>();
		subList311.add(new CubeSpot(6,1));
		subList311.add(new CubeSpot(7,2));
		subList311.add(new CubeSpot(8,1));
		subList311.add(new CubeSpot(7,0));
		LinkedList<CubeSpot> subList312 = new LinkedList<CubeSpot>();
		subList312.add(new CubeSpot(2,2));
		subList312.add(new CubeSpot(2,0));
		subList312.add(new CubeSpot(0,0));
		subList312.add(new CubeSpot(0,0));
		LinkedList<CubeSpot> subList313 = new LinkedList<CubeSpot>();
		subList313.add(new CubeSpot(2,1));
		subList313.add(new CubeSpot(1,0));
		subList313.add(new CubeSpot(0,1));
		subList313.add(new CubeSpot(1,2));
		zAxis.add(subList31);zAxis.add(subList32);zAxis.add(subList33);zAxis.add(subList34);zAxis.add(subList35);
		zAxis.add(subList36);zAxis.add(subList37);zAxis.add(subList38);zAxis.add(subList39);zAxis.add(subList310);
		zAxis.add(subList311);zAxis.add(subList312);zAxis.add(subList313);
		for(LinkedList<CubeSpot> list:zAxis)
		{
			for(int i=0;i<list.size();i++)
			{
				int index = (i-1+list.size())%list.size();
				zAxis1.put(list.get(i),list.get(index));
			}
		}
		generatorList.add(new Permutation<CubeSpot>(zAxis1));
		return generatorList;
	}
}
