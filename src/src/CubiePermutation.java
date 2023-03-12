package src;
import java.util.*;
public class CubiePermutation extends Permutation<CubieSpot> implements Invertible<Permutation<CubieSpot>>
{
	public CubiePermutation()
	{
		masterMap = new HashMap<CubieSpot,CubieSpot>();
	}
	public CubiePermutation(HashMap<CubieSpot,CubieSpot> template)
	{
		masterMap = template;
		normalize();
	}
	//Combines two Permutations, so we see the effect of first applying other, then this.
	public CubiePermutation concatenate(Permutation<CubieSpot> other)
	{
		HashMap<CubieSpot,CubieSpot> newMap = new HashMap<CubieSpot,CubieSpot>();
		for(CubieSpot elem: masterMap.keySet())
		{
			CubieSpot intermediate = masterMap.get(elem);
			int twist= intermediate.getTwist();
			int twist2=0;
			if(other.masterMap.containsKey(intermediate))
			{
				intermediate=other.masterMap.get(intermediate);
				twist2=intermediate.getTwist();
			}

			CubieSpot finalCubie = new CubieSpot(intermediate.getX(),intermediate.getY(),intermediate.getZ(),elem.getSize());
			finalCubie.twist(twist+twist2);
			newMap.put(elem,finalCubie);
		}
		for(CubieSpot elem2: other.masterMap.keySet())
		{
			if(!newMap.containsKey(elem2))
				newMap.put(elem2, other.masterMap.get(elem2));
		}
		CubiePermutation returnPerm = new CubiePermutation(newMap);
		returnPerm.normalize();
		return returnPerm;
	}
	public CubiePermutation inverse()
	{
		HashMap<CubieSpot,CubieSpot> newMap = new HashMap<CubieSpot,CubieSpot>();
		for(CubieSpot elem:masterMap.keySet())
		{
			CubieSpot inter = masterMap.get(elem);
			CubieSpot real = new CubieSpot(inter.getX(),inter.getY(),inter.getZ(),inter.getSize());	
			CubieSpot newElem = new CubieSpot(elem.getX(),elem.getY(),elem.getZ(),elem.getSize()-inter.getTwist(),elem.getSize());
			newMap.put(real,newElem);
		}
		return new CubiePermutation(newMap);
	}
	protected void normalize()
	{
		//removes any mappings of a piece onto itself.
		Iterator<CubieSpot> it = masterMap.keySet().iterator();
		while(it.hasNext())
		{
			CubieSpot elem = it.next();
			if(masterMap.get(elem).equals(elem)&&elem.getTwist()==masterMap.get(elem).getTwist())
				it.remove();
		}
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null||this==null)
			return false;
		if(!(obj instanceof CubiePermutation))
			return false;
		else
		{
			for(CubieSpot elem:masterMap.keySet())
				if(masterMap.get(elem)==null||!masterMap.get(elem).equals(((CubiePermutation)obj).masterMap.get(elem)))
					return false;
			for(CubieSpot elem: ((CubiePermutation)obj).masterMap.keySet())
				if(masterMap.get(elem)==null||!masterMap.get(elem).equals(((CubiePermutation)obj).masterMap.get(elem)))
					return false;
		}
		return true;			
	}
	public int hashCode(){return masterMap.hashCode();}
	public String toString()
	{
		String s1 ="";
		for(CubieSpot elem:masterMap.keySet())
		{
			s1+=elem+":"+masterMap.get(elem)+"\n";
		}
		if(masterMap.size()==0)
			s1+="Identity";
		return s1;
	}
}
