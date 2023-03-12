package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.rubiksCube;
import src.Dialog;
import src.cubePainting;
public class GUIFrame extends JFrame
{

	private rubiksCube cube;
	private MenuItem input = new MenuItem("User Input");
	private MenuItem upButton = new MenuItem("Up",new MenuShortcut(KeyEvent.VK_J));
	private MenuItem upInverseButton = new MenuItem("Up Inverse",new MenuShortcut(KeyEvent.VK_F));
	private MenuItem downButton = new MenuItem("Down",new MenuShortcut(KeyEvent.VK_L));
	private MenuItem downInverseButton = new MenuItem("Down Inverse",new MenuShortcut(KeyEvent.VK_S));
	private MenuItem rightButton = new MenuItem("Right",new MenuShortcut(KeyEvent.VK_I));
	private MenuItem rightInverseButton = new MenuItem("Right Inverse",new MenuShortcut(KeyEvent.VK_K));
	private MenuItem leftButton = new MenuItem("Left",new MenuShortcut(KeyEvent.VK_D));
	private MenuItem leftInverseButton = new MenuItem("Left Inverse",new MenuShortcut(KeyEvent.VK_E));
	private MenuItem frontButton = new MenuItem("Front",new MenuShortcut(KeyEvent.VK_H));
	private MenuItem frontInverseButton = new MenuItem("Front Inverse",new MenuShortcut(KeyEvent.VK_G));
	private MenuItem backButton = new MenuItem("Back",new MenuShortcut(KeyEvent.VK_W));
	private MenuItem backInverseButton = new MenuItem("Back Inverse",new MenuShortcut(KeyEvent.VK_O));
	private MenuItem solveButton = new MenuItem("Solve");
	private MenuItem scrambleButton = new MenuItem("Scramble");
	private MenuItem xButton = new MenuItem("X-axis turn",new MenuShortcut(KeyEvent.VK_Y));
	private MenuItem xInverseButton = new MenuItem("X-axis Inverse turn",new MenuShortcut(KeyEvent.VK_N));
	private MenuItem yButton = new MenuItem("Y-axis turn",new MenuShortcut(KeyEvent.VK_SEMICOLON));
	private MenuItem yInverseButton = new MenuItem("Y-axis Inverse turn",new MenuShortcut(KeyEvent.VK_A));
	private MenuItem zButton = new MenuItem("Z-axis turn",new MenuShortcut(KeyEvent.VK_P));
	private MenuItem zInverseButton = new MenuItem("Z-axis Inverse turn",new MenuShortcut(KeyEvent.VK_Q));
	public GUIFrame(rubiksCube rc)
	{
		this.addKeyListener(new WindowKey());
		MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu();
        Menu menuRotations = new Menu("Rotations");
        MenuItem menuFileExit = new MenuItem();
        Menu realFile = new Menu("File");
        menuFile.setLabel("Moves");
        menuFileExit.setLabel("Exit");
        
        // Add action listener.for the menu button
        menuFileExit.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GUIFrame.this.windowClosed();
                }
            }
        ); 
        realFile.add(input);
        realFile.add(menuFileExit);
        menuBar.add(realFile);
        menuBar.add(menuFile);
        menuBar.add(menuRotations);
        setTitle("A");
        setMenuBar(menuBar);
        setSize(new Dimension(400, 400));
        
        // Add window listener.
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    GUIFrame.this.windowClosed();
                }
            }
        );  
		cube=rc;
		input.addActionListener(new inputListener());
		menuRotations.add(xButton);
		menuRotations.add(xInverseButton);
		menuRotations.add(yButton);
		menuRotations.add(yInverseButton);
		menuRotations.add(zButton);
		menuRotations.add(zInverseButton);
		menuFile.add(upButton);
		menuFile.add(upInverseButton);
		menuFile.add(downButton);
		menuFile.add(downInverseButton);
		menuFile.add(rightButton);
		menuFile.add(rightInverseButton);
		menuFile.add(leftButton);
		menuFile.add(leftInverseButton);
		menuFile.add(frontButton);
		menuFile.add(frontInverseButton);
		menuFile.add(backButton);
		menuFile.add(backInverseButton);
		menuFile.add(solveButton);
		menuFile.add(scrambleButton);
		cubePainting panel = new cubePainting(Color.lightGray, cube);
		Container container = getContentPane();
		container.add(panel);
		upButton.addActionListener(new upListener());
		upInverseButton.addActionListener(new upInverseListener());
		downButton.addActionListener(new downListener());
		downInverseButton.addActionListener(new downInverseListener());
		rightButton.addActionListener(new rightListener());
		rightInverseButton.addActionListener(new rightInverseListener());
		leftButton.addActionListener(new leftListener());
		leftInverseButton.addActionListener(new leftInverseListener());
		frontButton.addActionListener(new frontListener());
		frontInverseButton.addActionListener(new frontInverseListener());
		backButton.addActionListener(new backListener());
		backInverseButton.addActionListener(new backInverseListener());
		solveButton.addActionListener(new solveListener());
		scrambleButton.addActionListener(new scrambleListener());
		xButton.addActionListener(new xListener());
		xInverseButton.addActionListener(new xInverseListener());
		yButton.addActionListener(new yListener());
		yInverseButton.addActionListener(new yInverseListener());
		zButton.addActionListener(new zListener());
		zInverseButton.addActionListener(new zInverseListener());
	}
	private class WindowKey implements KeyListener
	{
		public void keyPressed(KeyEvent k){
			switch(k.getKeyCode())
			{
				case(KeyEvent.VK_H):
				cube.faceTurn(2);
				break;
				case(KeyEvent.VK_G):
				cube.faceTurn(-2);
				break;
				case(KeyEvent.VK_J):
				cube.faceTurn(1);
				break;
				case(KeyEvent.VK_F):
				cube.faceTurn(-1);
				break;
				case(KeyEvent.VK_I):
				cube.faceTurn(3);
				break;
				case(KeyEvent.VK_K):
				cube.faceTurn(-3);
				break;
				case(KeyEvent.VK_D):
				cube.faceTurn(4);
				break;
				case(KeyEvent.VK_E):
				cube.faceTurn(-4);
				break;
				case(KeyEvent.VK_W):
				cube.faceTurn(5);
				break;
				case(KeyEvent.VK_O):
				cube.faceTurn(-5);
				break;
				case(KeyEvent.VK_S):
				cube.faceTurn(6);
				break;
				case(KeyEvent.VK_L):
				cube.faceTurn(-6);
				break;
				case(KeyEvent.VK_Y):
				cube.cubeTurn(1);
				break;
				case(KeyEvent.VK_N):
				cube.cubeTurn(-1);
				break;
				case(KeyEvent.VK_SEMICOLON):
				cube.cubeTurn(2);
				break;
				case(KeyEvent.VK_A):
				cube.cubeTurn(-2);
				break;
				case(KeyEvent.VK_P):
				cube.cubeTurn(3);
				break;
				case(KeyEvent.VK_Q):
				cube.cubeTurn(-3);
				break;
				default: return;
			}
			repaint();
			if(cube.userSolve())
			{
				Component frame = new JFrame();
				Object message = new String("Congratulations! You solved it!");
				JOptionPane.showMessageDialog(frame,message);
			}	
		}
		public void keyReleased(KeyEvent k){}
		public void keyTyped(KeyEvent k)
		{
		}
		
	}
	private class xListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
				cube.cubeTurn(-1);
				repaint();
			}
	}
	private class xInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
				cube.cubeTurn(1);
				repaint();
			}
	}
		private class yListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
				cube.cubeTurn(2);
				repaint();
			}
	}
	private class yInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
				cube.cubeTurn(-2);
				repaint();
			}
	}
	private class zListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
				cube.cubeTurn(3);
				repaint();
			}
	}
	private class zInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
				cube.cubeTurn(-3);
				repaint();
			}
	}
	private class upListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(1);
			repaint();
			if(cube.userSolve())
			{
				Component frame = new JFrame();
				Object message = new String("Congratulations! You solved it!");
				JOptionPane.showMessageDialog(frame,message);
			}			
		}
	}
	private class upInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(-1);
			repaint();	
				if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}		
		}
	}
	private class downListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(6);
			repaint();					
			if(cube.userSolve())
			{
				Component frame = new JFrame();
				Object message = new String("Congratulations! You solved it!");
				JOptionPane.showMessageDialog(frame,message);
			}		
		}
	}
	private class downInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(-6);
			repaint();				
			if(cube.userSolve())
			{
				Component frame = new JFrame();
				Object message = new String("Congratulations! You solved it!");
				JOptionPane.showMessageDialog(frame,message);
			}			
		}
	}
	private class rightListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(3);
			repaint();					if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}		
		}
	}
	private class rightInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(-3);
			repaint();					if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}		
		}
	}
	private class leftInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(-4);
			repaint();					if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}		
		}
	}
	private class leftListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(4);
			repaint();					if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}		
		}
	}
	private class frontListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(2);
			repaint();						if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}	
		}
	}
	private class frontInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(-2);
			repaint();						if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}	
		}
	}
	private class backListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(5);
			repaint();					if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}		
		}
	}
	private class backInverseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.faceTurn(-5);
			repaint();					if(cube.userSolve())
				{
					Component frame = new JFrame();
					Object message = new String("Congratulations! You solved it!");
					JOptionPane.showMessageDialog(frame,message);
				}		
		}
	}
	private class solveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.solve();
			repaint();			
		}
	}
	private class scrambleListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cube.scramble();
			repaint();			
		}
	}
	protected void windowClosed() {
    	
    	// TODO: Check if it is safe to close the application
    	
        // Exit application.
        System.exit(0);
    }
    private class inputListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		Dialog d = new Dialog(cube,GUIFrame.this);
    		d.show();
    	}
    }
    public void repaint()
    {
    	super.repaint();
    		if(cube.userSolve())
			{
				Component frame = new JFrame();
				Object message = new String("Congratulations! You solved it!");
				JOptionPane.showMessageDialog(frame,message);
			}	
    }
}
