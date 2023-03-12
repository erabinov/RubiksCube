package src;
public class cubie
{
	private char [] stickers;
	private int size;
	private int twist;
	public cubie(char[] sticks)
	{
		stickers=sticks;
		size = sticks.length;
		twist=0;	
	}
	public int size()
	{
		return size;
	}
	public char[] colors()
	{
		char [] twistedStickers = new char[size];
		for(int i=0;i<size;i++)
		{
			twistedStickers[i]=stickers[(i+twist)%size];
		}
		return twistedStickers;
	}
	public void twist(int n)
	{
		twist = (twist+n)%size;
	}
	public int getTwist()
	{
		return twist;
	}
	public boolean equals(Object obj)
	{
		if(obj==null||! (obj instanceof cubie))
			return false;
		else if(((cubie)obj).size!=this.size)
			return false;
		else
		{
			int j;
			for(j=0;j<((cubie)obj).size;j++)
				if(stickers[0]==((cubie)obj).stickers[j])
					break;
			for(int i=0;i<size;i++)
				if(stickers[i]!=((cubie)obj).stickers[(i+j)%size])
					return false;
			return true;
		}
	}
	public int hashCode()
	{
		int code=0;
		for(int i=1;i<=size;i++)
			code+=stickers[i-1];
		return code;
	}
	public String toString()
	{
		String s1="";
		for(char stick:stickers)
			s1+=stick+" ";
		s1+=" "+twist;
		return s1;
	}
}