//Supports group and Permutation approach.
package src;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import TerminalIO.KeyboardReader;
public class rubiksCube 
{
	private char[][] cube;
	private cubie[][][] cubieCube;
	private rubiksCubeGroup cubeGroup;
	private rubiksCubieGroup cubieGroup;
	private boolean justScrambled;
	private boolean userSolve;
	private CubiePermutation cubiePerm;
	public Permutation<CubeSpot> permutation;
	private HashMap<cubie,CubieSpot> cubieToSpotMap;
	boolean failedConstruction;
	private ArrayList<CubiePermutation> generator; 
	public rubiksCube()
	{
		cubieToSpotMap = new HashMap<cubie,CubieSpot>();
		justScrambled = false;
		userSolve = false;
		cubeGroup = new rubiksCubeGroup();
		cubieGroup = new rubiksCubieGroup();
		cubiePerm = cubieGroup.identityElement();
		permutation = cubeGroup.identityElement();
		generator = cubieGroup.generator();
		cube=new char[9][];
		int i;
		for(i=0;i<3;i++)
		{
			cube[i]=new char[3];
		}
		for(i=i;i<6;i++)
		{
			cube[i]=new char[12];
		}
		for(i=i;i<9;i++)
		{
			cube[i]=new char[3];
		}
		//Populates the cells with the proper colors.
		for(int j=0;j<cube.length;j++)
			for(int k=0;k<cube[j].length;k++)
				{
				if(0<=j&&j<3&&0<=k&&k<3)
					cube[j][k]='R';
				if(3<=j&&j<6&&0<=k&&k<3)
					cube[j][k]='B';
				if(3<=j&&j<6&&3<=k&&k<6)
					cube[j][k]='W';
				if(3<=j&&j<6&&6<=k&&k<9)
					cube[j][k]='G';
				if(3<=j&&j<6&&9<=k&&k<12)
					cube[j][k]='Y';
				if(6<=j&&j<9&&0<=k&&k<3)
					cube[j][k]='O';									
				}
		cubieCube = new cubie[3][3][3];
		char[][] CornerColors = {{'Y','B','O'},{'Y','R','B'},{'W','O','B'},{'W','B','R'},{'Y','O','G'},{'Y','G','R'},{'W','G','O'},{'W','R','G'}};
		char[][] EdgeColors = {{'Y','B'},{'O','B'},{'R','B'},{'W','B'},{'Y','O'},{'Y','R'},{'W','O'},{'W','R'},{'Y','G'},{'O','G'},{'R','G'},{'W','G'}};
		char[][] CenterColors = {{'B'},{'Y'},{'O'},{'R'},{'W'},{'G'}};
		int cornerCount=0;
		int edgeCount=0;
		int centerCount=0;
		for(int l=0;l<cubieCube.length;l++)
			for(int j=0;j<cubieCube[l].length;j++)
				for(int k=0;k<cubieCube[j].length;k++)
				{	
					char[] defaultA = {'K'};
					cubie addCubie=new cubie(defaultA);
					int size=0;
					switch(oneCount(l,j,k))
					{
						case 1:
						addCubie=new cubie(EdgeColors[edgeCount]);
						edgeCount++;
						size=2;
						break;
						case 2:
						addCubie = new cubie(CenterColors[centerCount]);
						centerCount++;
						size=1;
						break;
						case 0:
						addCubie = new cubie(CornerColors[cornerCount]);
						cornerCount++;
						size=3;
						break;
					}
					cubieCube[l][j][k] = addCubie;
					cubieToSpotMap.put(addCubie,new CubieSpot(l,j,k,size));
				}		
	}	
	public rubiksCube clone()
	{
		rubiksCube returnCube = new rubiksCube();
		returnCube.cube = this.cube;
		returnCube.cubieCube = this.cubieCube;
		returnCube.justScrambled =this.justScrambled;
		returnCube.userSolve = this.userSolve;
		returnCube.cubiePerm = this.cubiePerm;
		returnCube.permutation = this.permutation;
		returnCube.cubieToSpotMap = cubieToSpotMap;
		return returnCube;
		
	}
	//Creates a rubik's cube with the given template. Also figures out the exact Permutation of stickers.
	public rubiksCube(char[][] template)
	{
		//Initializes all the variables that would be the same otherwise and makes sure the arrays are the right size.
		this();
		if(!(template.length==9&&template[0].length==3&&template[3].length==12&&template[6].length==3))
			return;
		else
		{
			//Populates the cells with the proper colors according to template.
			for(int j=0;j<cube.length;j++)
				for(int k=0;k<cube[j].length;k++)
				{
					cube[j][k]=template[j][k];
				}
			//Takes the sticker colors from the two-dimensional array and maps this to a three-D array of cubies.
			HashMap<CubieSpot,ArrayList<CubeSpot>> stickMap = CubieSpot.spotMap();
			//A blank cube for purposes of comparison.
			rubiksCube clean = new rubiksCube();
			for(int j=0;j<cubieCube.length;j++)
				{
				for(int k=0;k<cubieCube[j].length;k++)
					{
					for(int l=0;l<cubieCube[j][k].length;l++)
					{
						int size;
						switch(oneCount(j,k,l))
						{
							case 0:
							size=3; break;
							case 1:
							size=2;
							break;
							case 2:
							size=1; break;
							default: size=0; break;
						}
						if(size!=0)
						{
							ArrayList<CubeSpot> locations = stickMap.get(new CubieSpot(j,k,l,size));
							char[] cubieCols = new char[size];					
							for(int m=0;m<size;m++)
								{
									cubieCols[m]=cube[locations.get(m).getRow()][locations.get(m).getCol()];
								}
							Set<cubie> cubies = clean.cubieToSpotMap.keySet();
							ArrayList<cubie> cubiesArr = new ArrayList<cubie>(cubies);
							//Gets the pure cubie with the same colors that's on a clean cube and compares them
							char[] cleanCols;
							try{
							cleanCols = cubiesArr.get(cubiesArr.indexOf(new cubie(cubieCols))).colors();
							}
							catch(ArrayIndexOutOfBoundsException e)
							{
								JOptionPane.showMessageDialog(new JFrame(),"You have entered an invalid cube orientation. Please re-enter.","ERROR!",JOptionPane.ERROR_MESSAGE);
								failedConstruction = true;
								return;
							}
							int twist;
							for(twist=0;twist<cleanCols.length;twist++)
							{
								if(cubieCols[twist]==cleanCols[0])
									break;
							}
							cubie newCubie = new cubie(cleanCols);
							newCubie.twist(twist);
							cubieCube[j][k][l]=newCubie;					
						}
					}
				}
			}
			failedConstruction=false;
			HashMap<CubieSpot,CubieSpot> permMap = new HashMap<CubieSpot,CubieSpot>();
			HashMap<CubeSpot,CubeSpot> stickPermMap = new HashMap<CubeSpot,CubeSpot>();
			//Finds which cubie replaced each cubie of the clean cube.
			for(int j=0;j<clean.cubieCube.length;j++)
				for(int k=0;k<clean.cubieCube[j].length;k++)
					for(int l=0;l<clean.cubieCube[j][k].length;l++)
					{
						if(!(j==1&&k==1&&l==1))
						{
							CubieSpot cs = clean.cubieToSpotMap.get(cubieCube[j][k][l]);
							int twist = cubieCube[j][k][l].getTwist();
							//if(oneCount(j,k,l)==1)
								//permMap.put(new CubieSpot(j,k,l,cubieCube[j][k][l].size()), new CubieSpot(cs.getX(),cs.getY(),cs.getZ(),twist,cs.getSize()));

							//else
								permMap.put(new CubieSpot(j,k,l,cubieCube[j][k][l].size()), new CubieSpot(cs.getX(),cs.getY(),cs.getZ(),twist,cs.getSize()));
						}
					}
			cubiePerm = new CubiePermutation(permMap);
		}
	}
	//This is a two-dimensional array that is used to look up the effect of face turns on the cubiecoordinate of the cube.
	//The first array index is the starting int edgeOrient, while the second index is an int corresponding to the faceturn being done.
	//Each array location stores the edgeOrient of the new Permutation created by performing the corresponding move.
	private int[][] moveEffects()
	{
		int[][] returnMap = new int[2048][12];
		int[] xArr = {0,0,0,0,1,1,1,1,2,2,2,2};
		int[] yArr = {0,1,2,1,0,2,2,0,0,1,2,1};
		int[] zArr = {1,0,1,2,0,0,2,2,1,0,1,2};
		for(int a=0;a<2;a++)
			for(int b=0;b<2;b++)
				for(int c=0;c<2;c++)
					for(int d=0;d<2;d++)
						for(int e=0;e<2;e++)
							for(int f=0;f<2;f++)
								for(int g=0;g<2;g++)
									for(int h=0;h<2;h++)
										for(int i=0;i<2;i++)
											for(int j=0;j<2;j++)
												for(int k=0;k<2;k++)
													{
														//This represents the Permutation created by the twists given by each of the edge cubies of the cube.
														HashMap<CubieSpot, CubieSpot> permMap = new HashMap<CubieSpot, CubieSpot>();
														int[] twists = {a,b,c,d,e,f,g,h,i,j,k};
														int twistSum = 0;
														for(int cubie=0;cubie<11;cubie++)
														{
															twistSum+=twists[cubie];
															permMap.put(new CubieSpot(xArr[cubie],yArr[cubie],zArr[cubie],2),new CubieSpot(xArr[cubie],yArr[cubie],zArr[cubie],twists[cubie],2));
														}
														//The twelfth cubie has to be fixed so the that sum of all the twists modulo 2 is zero.
														int twist = (twistSum%2);
														permMap.put(new CubieSpot(xArr[11],yArr[11],zArr[11],2),new CubieSpot(xArr[11],yArr[11],zArr[11],twist,2));
														CubiePermutation testPerm = new CubiePermutation(permMap);
														//This will be the given Value for cubieCo.
														CubieCoordinate cubieCo = new CubieCoordinate(testPerm);
														//Performs every faceTurn on cubieCo and puts all the new coordinates in the new map.
														for(int move=0;move<12;move++)
														{
															CubiePermutation newPerm;
															if(move/6==0)
																newPerm = cubieGroup.generator().get(move);
															else
																newPerm = cubieGroup.generator().get(move-6).inverse();
															returnMap[cubieCo.getEdgeOrient()][move] = new CubieCoordinate(newPerm.concatenate(testPerm)).getEdgeOrient();
														}
													}
								return returnMap;
		
	}
	//A very similar two-dimensional array, but for the corner twists. 
	private int[][] moveEffects2()
	{
		int[][] returnMap = new int[2187][10];
		int[] xArr = {0,0,0,0,2,2,2,2};
		int[] yArr = {0,0,2,2,0,0,2,2};
		int[] zArr = {2,0,0,2,2,0,0,2};
		ArrayList<CubiePermutation> generator = cubieGroup.generator();
		for(int a=0;a<3;a++)
			for(int b=0;b<3;b++)
				for(int c=0;c<3;c++)
					for(int d=0;d<3;d++)
						for(int e=0;e<3;e++)
							for(int f=0;f<3;f++)
								for(int g=0;g<3;g++)
								{
									HashMap<CubieSpot, CubieSpot> permMap = new HashMap<CubieSpot,CubieSpot>();
									int[] twists = {a,b,c,d,e,f,g};
									int twistSum=0;
									for(int cubie=0;cubie<7;cubie++)
									{
										twistSum+=twists[cubie];
										permMap.put(new CubieSpot(xArr[cubie],yArr[cubie],zArr[cubie],3),new CubieSpot(xArr[cubie],yArr[cubie],zArr[cubie],twists[cubie],3));
									}
									int twist = 3-twistSum%3;
									permMap.put(new CubieSpot(xArr[7],yArr[7],zArr[7],2),new CubieSpot(xArr[7],yArr[7],zArr[7],twist,3));
									CubiePermutation testPerm = new CubiePermutation(permMap);
									CubieCoordinate cubieCo = new CubieCoordinate(testPerm);
									for(int move=0;move<10;move++)
									{
										CubiePermutation newPerm = new CubiePermutation();
										switch(move)
										{
											case 0:
											newPerm = generator.get(0);
											break;
											case 1:
											newPerm = generator.get(1).concatenate(generator.get(1));
											break;
											case 2:
											newPerm = generator.get(2);
											break;
											case 3:
											newPerm = generator.get(3);
											break;
											case 4:
											newPerm = generator.get(4).concatenate(generator.get(4));
											break;
											case 5:
											newPerm = generator.get(5);
											break;
											case 6:
											newPerm = generator.get(0).inverse();
											break;
											case 7:
											newPerm = generator.get(2).inverse();
											break;
											case 8:
											newPerm = generator.get(3).inverse();
											break;
											case 9:
											newPerm = generator.get(5).inverse();
											break;
											
										}
										CubiePermutation perm3 = newPerm.concatenate(testPerm);
										returnMap[cubieCo.getCornerOrient()][move]= new CubieCoordinate(perm3).getCornerOrient();
									}
								}
							return returnMap;
	}
	//List of moves necessary to make the edges twisted right.
	public Move[] firstStage()
	{
		int[][] map = moveEffects();
		ArrayList<Move[]> possibles = new ArrayList<Move[]>(7000);
		int depth=7;
		while(possibles.isEmpty())
		{
			firstStageHelper(possibles, new Move[depth], new CubieCoordinate(cubiePerm).getEdgeOrient(), map,0);
			depth++;
		}
		return minMoves(possibles);
	}
	//Recursive algorithm that uses a look-up table to look up the effect of each move on the current edge orientation.
	//The look-up table makes things a lot quicker, because instead of calling methods numerous times, it uses an extremely quick array look-up.
	//Exits the loop if the size of the moves is greater than 7.
	//Returns a working move, but modifies an ArrayList of moves so that 
	private void firstStageHelper(ArrayList<Move[]> listOfPossibles, Move[] intermediate, int cubieCo, int[][] effects, int depth)
	{
		if(cubieCo==0)
		{
			Move[] copy;
			if(depth==0)
			{
				copy = new Move[1];
				copy[0]=Move.EMPTY;
				listOfPossibles.add(copy);
				return;
			}
			else
			{
				copy = new Move[depth];
				System.arraycopy(intermediate, 0, copy, 0, depth);
				listOfPossibles.add(copy);
				return;

			}
		}
		if(depth>=intermediate.length)
			return;
		for(int i=0;i<12;i++)
		{
				intermediate[depth]=Move.values()[i];
				int newCoord = effects[cubieCo][i];
				firstStageHelper(listOfPossibles, intermediate, newCoord,effects, depth+1);
		}
	}
	//Similar, but for corners.	
	public Move[] secondStage()
	{
		ArrayList<Move[]> possibles = new ArrayList<Move[]>();
		int depth = 7;
		while(possibles.isEmpty())
		{
			secondStageHelper2(possibles, new Move[depth], new CubieCoordinate(cubiePerm).getCornerOrient(), moveEffects2(),0);
			depth++;
		}
		return minMoves(possibles);
	}
	public void secondStageHelper2(ArrayList<Move[]> listOfPossibles, Move[] intermediate, int cubieCo, int[][] effects, int depth)
	{
		if(cubieCo==0)
		{
			Move[] copy;
			if(depth==0)
			{
				copy = new Move[1];
				copy[0]=Move.EMPTY;
				listOfPossibles.add(copy);
				return;
			}
			else
			{
				copy = new Move[depth];
				System.arraycopy(intermediate, 0, copy, 0, depth);
				listOfPossibles.add(copy);
				return;
			}
		}
		if(depth>=intermediate.length)
			return;
		for(int i=0;i<10;i++)
		{
			switch(i)
			{
				case 0:
					intermediate[depth]=Move.U;
				break;
				case 1:
					intermediate[depth]=Move.F2;
				break;
				case 2:
					intermediate[depth]=Move.R;
				break;
				case 3:
					intermediate[depth]=Move.L;
				break;
				case 4:
					intermediate[depth]=Move.B2;
				break;
				case 5:
					intermediate[depth]=Move.D;
				break;
				case 6:
					intermediate[depth]=Move.UI;
				break;
				case 7:
					intermediate[depth]=Move.RI;
				break;
				case 8:
					intermediate[depth]=Move.LI;
				break;
				case 9:
					intermediate[depth]=Move.DI;
				break;
			}
			int newCoord = effects[cubieCo][i];
			secondStageHelper2(listOfPossibles,intermediate,newCoord,effects, depth+1);
		}
	}
	//Returns the set of moves that least affects the cube.
	public Move[] minMoves(ArrayList<Move[]> possibles)
	{
		Move[] minList = null;
		double minPerm=26.;
		for(Move[] elem : possibles)
		{
			rubiksCube testCube = clone();
			testCube.doMoves(elem);
			double newNumber = 0.0;
			for(CubieSpot elem1: testCube.cubiePerm.memberMap().keySet())
			{
				if(elem1.getSize()==2)
					newNumber+=1.25;
				else if (elem1.getSize()==3)
					newNumber++;
			}
			if(newNumber<minPerm)
			{
				minList=elem;
				minPerm=newNumber;
			}
			else if(newNumber==minPerm&&(minList==null||elem.length<minList.length))
			{
				minList=elem;
			}
		}
		return minList;
	}
	//Determines how many of the oneCnt type pieces are out of their spots.
	public int outOfOrder(int oneCnt, CubiePermutation perm)
	{
		int count=0;
		for(CubieSpot cs : perm.memberMap().keySet())
		{
			if(oneCount(cs.getX(),cs.getY(),cs.getZ())==oneCnt)
				count++;
		}
		return count;
	}
	//Solves the corners completely by using one 3-cycle algorithm and conjugating it by a move returned by SolveHelper.
	public ArrayList<Move> cornerSolve()
	{
		ArrayList<CubiePermutation> generator = cubieGroup.generator();
		ArrayList<Move> returnList = new ArrayList<Move>();
		Move[][] movesList = {{Move.U,Move.F2,Move.UI,Move.B2,Move.U,Move.F2,Move.UI,Move.B2},{Move.U,Move.R2,Move.UI,Move.L2,Move.U,Move.R2,Move.UI,Move.L2},{Move.
				U,Move.L2,Move.UI,Move.R2,Move.U,Move.L2,Move.UI,Move.R2}, {Move.U,Move.B2,Move.UI,Move.F2,Move.U,Move.B2,Move.UI,Move.F2},{Move.UI,Move.F2,Move.U,Move.B2,Move.UI,Move.F2,Move.U,Move.B2},
				{Move.UI,Move.R2,Move.U,Move.L2,Move.UI,Move.R2,Move.U,Move.L2},{Move.UI,Move.L2,Move.U,Move.R2,Move.UI,Move.L2,Move.U,Move.R2}, {Move.UI,Move.B2,Move.U,Move.F2,Move.UI,Move.B2,Move.U,Move.F2}};
		ArrayList<Move[]> allMoves = new ArrayList<Move[]>();
		for(int i=0;i<movesList.length;i++)
		{
			Move[] intermediate = new Move[movesList[i].length];
			for(int j=0;j<movesList[i].length;j++)
			{
				intermediate[j]=movesList[i][j];
			}
			allMoves.add(intermediate);
		}
		ArrayList<Duo<CubiePermutation,Move>> generator2 = new ArrayList<Duo<CubiePermutation,Move>>();
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(0),Move.U));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(0).inverse(),Move.UI));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(5),Move.D));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(5).inverse(),Move.DI));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(1).concatenate(generator.get(1)),Move.F2));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(2).concatenate(generator.get(2)),Move.R2));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(3).concatenate(generator.get(3)),Move.L2));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(4).concatenate(generator.get(4)),Move.B2));
		rubiksCube temp = new rubiksCube();
		while(new CubieCoordinate(cubiePerm).getCornerPerm()!=0&&outOfOrder(0,cubiePerm)>2)
		{
			ArrayList<Move[]> possibles = new ArrayList<Move[]>();
			int depth = 4;
			while(possibles.isEmpty())
			{
				for(int i=0; i<allMoves.size();i++)
				{
					temp.doMoves(allMoves.get(i));
					SolveHelper(possibles,temp.cubiePerm, allMoves.get(i), generator2, new Move[depth], new CubiePermutation(), 0);
					temp.solve();
				}
				depth++;
			}
			Move[] minMoves = minMoves(possibles);
			System.out.print("[");
			for(int i=0;i<minMoves.length-1;i++)
				System.out.print(minMoves[i]+",");
			System.out.print(minMoves[minMoves.length-1]);
			System.out.println("]");
			doMoves(minMoves);
			for(int i=0;i<minMoves.length;i++)
				returnList.add(minMoves[i]);
		}
		return returnList;
	}
	//Similar method.
	public ArrayList<Move> edgeSolve()
	{
		Move[] cycle = {Move.B, Move.R, Move.UI,Move.D,Move.BI,Move.U2,Move.B,Move.U,Move.DI,Move.RI,Move.U2,Move.BI};
		rubiksCube temp = new rubiksCube();
		Move[] cycle2 = new Move[cycle.length];
		System.arraycopy(cycle, 0, cycle2, 0, cycle.length);
		cycle2[2]=Move.U; cycle2[3]=Move. DI; cycle2[7]=Move.UI; cycle2[8]=Move.D;
		Move[] cycle3 = new Move[cycle.length];
		System.arraycopy(cycle, 0, cycle3, 0, cycle.length);
		cycle3[0]=Move.BI; cycle3[cycle.length-1]=Move.B;
		Move[] cycle4 = rotateMove(cycle,'X');
		Move[] cycle5 = rotateMove(cycle4,'X');
		Move[] cycle6 = rotateMove(cycle5,'X');
		Move[] cycle7 = rotateMove(cycle, 'Y');
		Move[] cycle8 = rotateMove(rotateMove(cycle7,'Y'),'Y');
		ArrayList<Move[]>allMoves=new ArrayList<Move[]>();
		allMoves.add(cycle); allMoves.add(cycle2); allMoves.add(cycle3); allMoves.add(cycle4); allMoves.add(cycle5);
		allMoves.add(cycle6); allMoves.add(cycle7); allMoves.add(cycle8);
		ArrayList<CubiePermutation> generator = cubieGroup.generator();
		ArrayList<Duo<CubiePermutation,Move>> generator2 = new ArrayList<Duo<CubiePermutation,Move>>();
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(0),Move.U));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(0).inverse(),Move.UI));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(5),Move.D));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(5).inverse(),Move.DI));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(1).concatenate(generator.get(1)),Move.F2));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(2),Move.R));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(2).inverse(),Move.RI));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(3).inverse(),Move.LI));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(3),Move.L));
		generator2.add(new Duo<CubiePermutation,Move>(generator.get(4).concatenate(generator.get(4)),Move.B2));
		ArrayList<Move> returnList = new ArrayList<Move>();
		rubiksCube temp3 = new rubiksCube();
		while(new CubieCoordinate(cubiePerm).getEdgePerm()!=0&&outOfOrder(1,cubiePerm)>2)
		{
			ArrayList<Move[]> possibles = new ArrayList<Move[]>();
			int depth = 4;
			while(possibles.isEmpty())
			{
				for(int i=0;i<allMoves.size();i++)
				{
					temp3.doMoves(allMoves.get(i));
					SolveHelper(possibles, temp3.cubiePerm, allMoves.get(i), generator2, new Move[depth], new CubiePermutation(), 0);
					temp3.solve();
				}
				depth++;
			}
			Move[] list1 = minMoves(possibles);
			System.out.print("[");
			for(int i=0;i<list1.length-1;i++)
				{System.out.print(list1[i]+","); returnList.add(list1[i]);}
			System.out.print(list1[list1.length-1]); returnList.add(list1[list1.length-1]);
			System.out.println("]");
			doMoves(list1);
		}
		return returnList;
	}
	private ArrayList<Move> resolveParity()
	{
		if(cubiePerm.memberMap().size()!=4)
		{
			return new ArrayList<Move>();
		}
		else
		{
			ArrayList<CubiePermutation> gen = cubieGroup.generator();
			ArrayList<Duo<CubiePermutation,Move>> generator = new ArrayList<Duo<CubiePermutation,Move>>();
			generator.add(new Duo<CubiePermutation,Move>(gen.get(0),Move.U));
			generator.add(new Duo<CubiePermutation,Move>(gen.get(0).inverse(),Move.UI));
			generator.add(new Duo<CubiePermutation,Move>(gen.get(5),Move.D));
			generator.add(new Duo<CubiePermutation,Move>(gen.get(5).inverse(),Move.DI));
			generator.add(new Duo<CubiePermutation,Move>(gen.get(1).concatenate(gen.get(1)),Move.F2));
			generator.add(new Duo<CubiePermutation,Move>(gen.get(2).concatenate(gen.get(2)),Move.R2));
			generator.add(new Duo<CubiePermutation,Move>(gen.get(3).concatenate(gen.get(3)),Move.L2));
			generator.add(new Duo<CubiePermutation,Move>(gen.get(4).concatenate(gen.get(4)),Move.B2));
			Move[][] strArr = {{Move.LI,Move.U,Move.R,Move.UI,Move.L,Move.U,Move.LI,Move.U,Move.RI,Move.UI,Move.L,Move.U2,Move.R,Move.U2,Move.RI},
								{Move.L2,Move.FI,Move.L,Move.D2,Move.RI,Move.B,Move.R,Move.D2,Move.L, Move.B,Move.L, Move.F,Move.LI,Move.BI},
								{Move.L2,Move.D,Move.F2,Move.DI, Move.L2,Move.B2,Move.DI, Move.R2, Move.D,Move.B2},
								{Move.BI,Move.U2,Move.B,Move.UI,Move.RI,Move.F,Move.R,Move.BI, Move.RI,Move.FI,Move.R,Move.UI,Move.B,Move.UI},
								{Move.B2,Move.L,Move.U,Move.LI,Move.B2,Move.R,Move.DI,Move.R,Move.D,Move.R2},
								{Move.R,Move.B,Move.UI,Move.BI,Move.R,Move.D,Move.BI, Move.LI,Move.BI,Move.L,Move.B2,Move.DI,Move.R2}};
			ArrayList<Move[]> allMoves = new ArrayList<Move[]>();
			for(int i=0;i<strArr.length;i++)
			{
				allMoves.add(strArr[i]);
//				Move[] X1 = rotateMove(strArr[i],'X');
//				Move[] X2 = rotateMove(X1,'X');
//				Move[] X3 = rotateMove(X2,'X');
//				Move[] Y1 = rotateMove(strArr[i],'Y');
//				Move[] Y3 = rotateMove(rotateMove(Y1,'Y'),'Y');
//				allMoves.add(X1);
//				allMoves.add(X2);
//				allMoves.add(X3);
//				allMoves.add(Y1);
//				allMoves.add(Y3);
			}
			ArrayList<Move[]> possibles = new ArrayList<Move[]>();
			int depth = 4;
			while(possibles.isEmpty())
			{
				rubiksCube temp = new rubiksCube();
				for(int i=0;i<strArr.length;i++)
					{
						temp.doMoves(strArr[i]);
						SolveHelper(possibles, temp.cubiePerm, strArr[i], generator, new Move[depth], new CubiePermutation(), 0);
						temp.solve();
					}
				depth++;
			}
			Move[] returnList = minMoves(possibles);
			ArrayList<Move> movesList = new ArrayList<Move>();
			System.out.print("[");
			for(int i=0;i<returnList.length-1;i++)
				{System.out.print(returnList[i]+","); movesList.add(returnList[i]);}
			System.out.print(returnList[returnList.length-1]); movesList.add(returnList[returnList.length-1]);
			System.out.println("]");
			doMoves(returnList);
			return movesList;
			
		}
	}
	//Finds the moves necessary to conjugate move so that it reduces the support of cubiePerm.
	private void SolveHelper(ArrayList<Move[]> allPossibles, CubiePermutation move, Move[] moveList, ArrayList<Duo<CubiePermutation,Move>> generator,Move[] conjMoves,CubiePermutation conjPerm, int depth)
	{
		CubiePermutation intermediate = conjPerm.concatenate(cubiePerm);
		intermediate = move.concatenate(intermediate);
		intermediate = (conjPerm.inverse()).concatenate(intermediate);
		Iterator<CubieSpot> it =move.memberMap().keySet().iterator();
		int size = it.next().getSize();
		int num1, num2;
		boolean parity;
		if(it.next().getSize()==size&&it.next().getSize()==size)
		{
			parity=false;
			switch(size)
			{
				case 2:
				size=1;
				break;
				case 3:
				size=0;
				break;
			}
			num1 = outOfOrder(size,intermediate);
			num2 = outOfOrder(size,cubiePerm);
		}
		else
		{
			parity = true;
			num1 = outOfOrder(0,intermediate)+outOfOrder(1,intermediate);
			num2 = outOfOrder(0,cubiePerm)+outOfOrder(1,cubiePerm);
		}	
		//If the intermediate array of Moves works so that conjMove*move*conjMove' reduces the number of pieces out of order, we return the permutation given by the conjugator and its moves.
		if(num1<num2)
		{
			Move[] copy;
			if(depth==0)
			{
				copy = new Move[1];
				copy[0]=Move.EMPTY;
				return;
			}
			else
			{
				Move[] addMoves = new Move[depth*2+moveList.length];
				for(int i=0;i<depth;i++)
					addMoves[i]=conjMoves[i];
				for(int i=depth; i<moveList.length+depth;i++)
					addMoves[i]=moveList[i-depth];
				Move[] trimmedConjMoves = new Move[depth];
				for(int i=0;i<depth;i++)
					trimmedConjMoves[i]=conjMoves[i];
				Move[] inverseConj = findInverseMove(trimmedConjMoves);
				for(int i=depth+moveList.length;i<depth*2+moveList.length;i++)
					addMoves[i]=inverseConj[i-depth-moveList.length];
				copy = addMoves;
			}
			allPossibles.add(copy);
			return;
		}
		if(depth>=conjMoves.length)
			return;
		for(int i=0;i<generator.size();i++)
		{
			CubiePermutation newPerm = generator.get(i).getK();
			conjMoves[depth] = generator.get(i).getV();
			SolveHelper(allPossibles, move, moveList, generator, conjMoves, newPerm.concatenate(conjPerm),depth+1);
		}

	}
	//Finds the moves necessary to solve the cube.
	//First, recursively finds the minimal sequence of moves to twist the edges properly.
	//Then, repeats a very similar process for the corners.
	//Then, using three algorithms that don't affect the twists of any of the cubies, we find successively find rearrangements of these algorithms the bring the cube closer to being solved.
	public ArrayList<Move> solveMoves()
	{
		Move[] returnList1 = firstStage();
		System.out.print("[");
		for(int i=0;i<returnList1.length-1;i++)
			System.out.print(returnList1[i]+",");
		System.out.print(returnList1[returnList1.length-1]);
		System.out.println("]");
		doMoves(returnList1);
		Move[] returnList2 = secondStage();
		System.out.print("[");
		for(int i=0;i<returnList2.length-1;i++)
			System.out.print(returnList2[i]+",");
		System.out.print(returnList2[returnList2.length-1]);
		System.out.println("]");
		doMoves(returnList2);
		ArrayList<Move> wholeMove = new ArrayList<Move>();
		for(int i=0;i<returnList1.length;i++)
		{
			Move turn = returnList1[i];
			if(turn!=Move.EMPTY)
				wholeMove.add(returnList1[i]);
		}
		for(int i=0;i<returnList2.length;i++)
		{
			Move turn = returnList2[i];
			if(turn!=Move.EMPTY)
				wholeMove.add(returnList2[i]);
		}
		ArrayList<Move> cornerSolveMoves = cornerSolve();
//		doMoves((Move[])(cornerSolveMoves.toArray()));
		wholeMove.addAll(cornerSolveMoves);
		ArrayList<Move> edgeSolveMoves = edgeSolve();
//		doMoves((Move[])(edgeSolveMoves.toArray()));
		wholeMove.addAll(edgeSolveMoves);
		ArrayList<Move> resolveParityMoves = resolveParity();
//		doMoves((Move[])(resolveParityMoves.toArray()));
		wholeMove.addAll(resolveParityMoves);
		ArrayList<Move> cornerSolveMoves2 = cornerSolve();
//		doMoves((Move[])(cornerSolveMoves2.toArray()));
		wholeMove.addAll(cornerSolveMoves2);
		ArrayList<Move> edgeSolveMoves2 = edgeSolve();
//		doMoves((Move[])(edgeSolveMoves2.toArray()));
		wholeMove.addAll(edgeSolveMoves2);
		System.out.println(wholeMove.size());
		return wholeMove;
	}
	private Move[] findInverseMove(Move[] move)
	{
		Move[] returnList = new Move[move.length];
		for(int i=move.length-1;i>=0;i--)
		{
			Move turn = move[i];
			returnList[move.length-1-i]=Move.findInverse(turn);
		}
		return returnList;
	}
	private Move[] rotateMove(Move[] move, char axis)
	{
		ArrayList<Move> Order = new ArrayList<Move>();
		switch(axis)
		{
			case 'X':
			Order.add(Move.B);
			Order.add(Move.D);
			Order.add(Move.F);
			Order.add(Move.U);
			break;
			case 'Y':
			Order.add(Move.U);
			Order.add(Move.L);
			Order.add(Move.D);
			Order.add(Move.R);
			break;
			case 'Z':
			Order.add(Move.R);
			Order.add(Move.B);
			Order.add(Move.L);
			Order.add(Move.F);
			break;
			
		}
		Move[] newMove = new Move[move.length];
		for(int i=0;i<newMove.length;i++)
		{
			Move elem = move[i];
			ArrayList<Move> moveValues = new ArrayList<Move>();
			for(Move element: Move.values())
				moveValues.add(element);
			int index1 = moveValues.indexOf(elem);
			int strippedDown = index1%6;
			int inverseOrDouble = index1/6;
			int index = Order.indexOf(moveValues.get(strippedDown));
			if(index>-1)
			{
				Move newTurn = Order.get((index+1)%Order.size());
				int index2 = moveValues.indexOf(newTurn);
				newMove[i]=moveValues.get(6*inverseOrDouble+index2);
			}
			else
				newMove[i]=elem;
		}
		return newMove;
		
	}
	private void doMoves(Move[] strList)
	{
		for(Move elem : strList)
		{
			switch(elem)
			{
				
				case U2:
					faceTurn(1);
				case U:
					faceTurn(1);
					break;
				case UI:
					faceTurn(-1);
					break;
				case F2:
					faceTurn(2);
				case F:
					faceTurn(2);
					break;
				case FI:
					faceTurn(-2);
					break;
				case R2:
					faceTurn(3);
				case R:
					faceTurn(3);
					break;
				case RI:
					faceTurn(-3);
					break;
				case L2:
					faceTurn(4);
				case L:
					faceTurn(4);
					break;
				case LI:
					faceTurn(-4);
					break;
				case B2:
					faceTurn(5);
				case B:
					faceTurn(5);
					break;
				case BI:
					faceTurn(-5);
					break;
				case D2:
					faceTurn(6);
				case D:
					faceTurn(6);
					break;
				case DI:
					faceTurn(-6);
					break;
				case EMPTY:
					return;
			}
		}
	}
	private int oneCount(int i,int j, int k)
	{
		int count=0;
		if(i==1)
			count++;
		if(j==1)
			count++;
		if(k==1)
			count++;
		return count;
	}
	public String toString()
	{
		String s1="Sticker representation: \n";
		for(int j=0;j<cube.length;j++)
		{
			if (cube[j].length==3)
			s1+="   ";
			for(int k=0;k<cube[j].length;k++)
					s1+=cube[j][k];
			s1+="\n";
		}
		s1+="\n\n\nRepresentation of cube based on three dimensional coordinates of each cubie:\n";
		for(int i=0;i<cubieCube.length;i++)
		{
			for(int j=0;j<cubieCube[i].length;j++)
			{
				for(int k=0;k<cubieCube[j].length;k++)
					s1+=cubieCube[i][j][k]+"\t";
				s1+="\n";
			}
			s1+="\n\n";
		}
		return s1;
	}
	public char[][] getCube()
	{
		return cube;
	}	
	public cubie[][][] getCubieCube()
	{
		return cubieCube;
	}
	public void faceTurn(int choice)
	{
		userSolve=false;
		if(choice<0)
			{
				apply(cubeGroup.generator().get(-choice-1).inverse());
				applyCubie(cubieGroup.generator().get(-choice-1).inverse());
			}
		else
			{
				apply(cubeGroup.generator().get(choice-1));
				applyCubie(cubieGroup.generator().get(choice-1));
			}
		for(int i=0;i<cube.length;i++)
			for(int j=0;j<cube[i].length;j++)
				if(cube[i][j]!=solvedState()[i][j]||justScrambled==false)
					return;
			userSolve= true;
			justScrambled=false;
	}
	public boolean userSolve(){return userSolve;}
	public void cubeTurn(int choice)
	{
		userSolve=false;
		if(choice<0)
		{
			apply(cubeGroup.axisTurns().get(-choice-1).inverse());
			applyCubie(cubieGroup.axisTurns().get(-choice-1).inverse());
		}
		else
		{
			apply(cubeGroup.axisTurns().get(choice-1));
			applyCubie(cubieGroup.axisTurns().get(choice-1));
		}
		for(int i=0;i<cube.length;i++)
			for(int j=0;j<cube[i].length;j++)
				if(cube[i][j]!=solvedState()[i][j]||justScrambled==false)
					return;
			userSolve= true;
			justScrambled=false;
	}
	private void applyCubie(CubiePermutation perm)
	{
		cubie[][][] copy = new cubie[3][3][3];
		for(int i=0;i<cubieCube.length;i++)
			for(int j=0;j<cubieCube.length;j++)
				for(int k=0;k<cubieCube.length;k++)
					copy[i][j][k]=cubieCube[i][j][k];
		for(CubieSpot cs:perm.memberMap().keySet())
		{
			CubieSpot replacedBy = perm.get(cs);
			cubieCube[cs.getX()][cs.getY()][cs.getZ()]=copy[cs.getX()][cs.getY()][cs.getZ()];
		}
		cubiePerm = perm.concatenate(cubiePerm);
	}
	private void apply(Permutation<CubeSpot> perm)
	{
		char[][] copy = new char[9][];
		for(int i=0;i<cube.length;i++)
		{
			copy[i] = new char[cube[i].length];
			for(int j=0;j<cube[i].length;j++)
			{
				copy[i][j]=cube[i][j];
			}
		}
		for(CubeSpot cs:perm.memberMap().keySet())
		{
			CubeSpot replacedBy = perm.get(cs);
			cube[cs.getRow()][cs.getCol()]=copy[replacedBy.getRow()][replacedBy.getCol()];
		}
		permutation = perm.concatenate(permutation);				
	}
	public void solve()
	{
		for(int j=0;j<cube.length;j++)
			for(int k=0;k<cube[j].length;k++)
				{
				if(0<=j&&j<3&&0<=k&&k<3)
					cube[j][k]='R';
				if(3<=j&&j<6&&0<=k&&k<3)
					cube[j][k]='B';
				if(3<=j&&j<6&&3<=k&&k<6)
					cube[j][k]='W';
				if(3<=j&&j<6&&6<=k&&k<9)
					cube[j][k]='G';
				if(3<=j&&j<6&&9<=k&&k<12)
					cube[j][k]='Y';
				if(6<=j&&j<9&&0<=k&&k<3)
					cube[j][k]='O';
									
				}
				justScrambled=false;
				cubiePerm = new CubiePermutation();
				permutation = new Permutation<CubeSpot>();
	}
	public char getColor(int j, int k)
	{
		return cube[j][k];
	}
	public void scramble()
	{
		for(int i=0;i<40;i++)
		{
			int rand = (int)(Math.random()*6)+1;
			if(Math.random()>=.5)
				rand=-rand;
			faceTurn(rand);
		}
		justScrambled=true;
	}
	public static char[][] solvedState()
	{
		char cube[][]= new char[9][];
		int i;
		for(i=0;i<3;i++)
		{
			cube[i]=new char[3];
		}
		for(i=i;i<6;i++)
		{
			cube[i]=new char[12];
		}
		for(i=i;i<9;i++)
		{
			cube[i]=new char[3];
		}		
		for(int j=0;j<cube.length;j++)
			for(int k=0;k<cube[j].length;k++)
				{
				if(0<=j&&j<3&&0<=k&&k<3)
					cube[j][k]='R';
				if(3<=j&&j<6&&0<=k&&k<3)
					cube[j][k]='B';
				if(3<=j&&j<6&&3<=k&&k<6)
					cube[j][k]='W';
				if(3<=j&&j<6&&6<=k&&k<9)
					cube[j][k]='G';
				if(3<=j&&j<6&&9<=k&&k<12)
					cube[j][k]='Y';
				if(6<=j&&j<9&&0<=k&&k<3)
					cube[j][k]='O';									
				}
		return cube;
	}
	public boolean failedConstruction()
	{return failedConstruction;}
	private class Duo<K,V>
	{
		private K key;
		private V value;
		public Duo(K k, V v)
		{
			key = k;
			value = v;
		}
		public K getK(){return key;}
		public V getV(){return value;}
	}
}
