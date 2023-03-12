package src;
import java.util.*;
public interface Group<E extends Invertible<E>> 
{
	public E identityElement();
	public ArrayList<E> generator();
}
