package src;
public class testingProgram{
	public static void main(String [] args)
	{
		rubiksCube cube = new rubiksCube();
		for(int i=0; i<=100; i++)
		{
			cube.scramble();
			cube.solveMoves();
		}
	}
	
}