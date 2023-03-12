package src;
import java.util.*;
import TerminalIO.KeyboardReader;
public class PermutationMap<E> implements Invertible<PermutationMap<E>>
{
	//I will use a HashMap implementation of the Permutation. This masterMap maps the pieces on the cube to the cubies that replace them.
	protected Map<E,E> masterMap;
	public PermutationMap()
	{
		masterMap= new HashMap<E,E>();
	}
	private PermutationMap(HashMap<E,E> template)
	{
		masterMap = template;
	}
	public Map<E,E> memberMap()
	{
		return masterMap;
	}
	public boolean equals(Object obj)
	{
		if(obj==null||this==null)
			return false;
		if(!(obj instanceof PermutationMap))
			return false;
		else
		{
			for(E elem:masterMap.keySet())
				if(!masterMap.get(elem).equals(((Permutation<E>)obj).get(elem)))
					return false;
						for(E elem:masterMap.keySet())
			for(E element: ((Permutation<E>)obj).masterMap.keySet())
				if(!masterMap.get(element).equals(((Permutation<E>)obj).get(element)))
					return false;
		}
		return true;			
	}
	public PermutationMap<E> inverse()
	{
		HashMap<E,E> copy = new HashMap<E,E>();
		for(E elem: masterMap.keySet())
			copy.put(masterMap.get(elem),elem);
		PermutationMap<E> newPerm = new PermutationMap<E>(copy);
		return newPerm;
	}
	//Counts how many elements are in the supports for both.
/*	public int compareTo(Permutation<E> other)
	{
		int count = 0;
		if(list.size()==0||other.list.size()==0)
			return -1;
		for(LinkedList<E> listie: list)
			for(E spottie:listie)
				for(LinkedList<E> thatList : other.list)
					if(thatList.contains(spottie))
					{
						count++;
						break;
					}
		return count;
	}*/
	public Set<E> support()
	{
		Set<E> returnList = new HashSet<E>();
		for(E elem: masterMap.keySet())
		{
			returnList.add(elem);
			returnList.add(masterMap.get(elem));
		}
		return returnList;
			
	}
	public PermutationMap<E> concatenate(PermutationMap<E> other)
	{
		/*if(compareTo(other)==0)
		{
			ArrayList<LinkedList<E>> list1 = new ArrayList<LinkedList<E>>(list);
			list1.addAll(other.list);
			return new Permutation<E>(list1);
		}*/
		HashMap<E,E> newMap = new HashMap<E,E>();
		for(E elem: other.masterMap.keySet())
		{
			E intermediate = other.masterMap.get(elem);
			if(masterMap.containsKey(intermediate))
				intermediate = masterMap.get(intermediate);
			newMap.put(elem,intermediate);
		}
		for(E elem: masterMap.keySet())
		{
			if(!other.masterMap.containsKey(elem))
				newMap.put(elem, masterMap.get(elem));
		}
		PermutationMap returnPerm = new PermutationMap(newMap);
		returnPerm.normalize();
		return returnPerm;
	}
	protected void normalize()
	{
		//removes any mappings of a piece onto itself.
		Iterator<E> it = masterMap.keySet().iterator();
		while(it.hasNext())
		{
			E elem = it.next();
			if(masterMap.get(elem).equals(elem))
				it.remove();
		}
	}
	public boolean contains(E elem)
	{
		return masterMap.keySet().contains(elem);
	}
	public String toString()
	{
		String s1="";
		for(E elem:masterMap.keySet())
		{
			s1+=elem+":"+masterMap.get(elem);
		}
		return s1;
	}
}
