package src;

public enum Move
{
	U, F, R, L, B, D, UI, FI, RI, LI, BI, DI, U2, F2, R2, L2, B2, D2, EMPTY;
	public static Move findInverse(Move m)
	{
		switch(m)
		{
			case U2:
			case F2:
			case B2:
			case R2:
			case L2:
			case D2:
				return m;
			case U:
				return UI;
			case F:
				return FI;
			case R:
				return RI;
			case L:
				return LI;
			case B:
				return BI;
			case D:
				return DI;
			case UI:
				return U;
			case FI:
				return F;
			case RI:
				return R;
			case LI:
				return L;
			case BI:
				return B;
			case DI:
				return D;
			case EMPTY:
				return EMPTY;
		}
		return null;
	}
}