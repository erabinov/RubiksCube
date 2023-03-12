package src;
import java.util.*;
public class PermutationTest 
{
	public static void main(String [] args)
	{
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(new Integer(1),new Integer(6));
		map.put(new Integer(6),new Integer(1));
		map.put(new Integer(5),new Integer(4));
		map.put(new Integer(4),new Integer(3));
		map.put(new Integer(3),new Integer(5));
		Permutation<Integer> one = new Permutation<Integer>(map);
		HashMap<Integer,Integer> map2 = new HashMap<Integer,Integer>();
		map2.put(new Integer(1),new Integer(2));
		map2.put(new Integer(2),new Integer(3));
		map2.put(new Integer(3),new Integer(1));
		map2.put(new Integer(4),new Integer(5));
		map2.put(new Integer(5),new Integer(4));
		System.out.println(one);
		Permutation<Integer> two = new Permutation<Integer>(map2);
		System.out.println(two);
		Permutation<Integer> three = one.concatenate(two);
		System.out.println(three+"\n"+two.equals(three));
		System.out.println(one.inverse());
		System.out.println(three.inverse().concatenate(three)+"here");
	}
}
