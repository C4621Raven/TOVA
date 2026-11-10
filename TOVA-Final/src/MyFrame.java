import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ComponentListener, ActionListener
{
	JLabel whiteS, targetLabel, buttonLabel;
	JButton button;
	JTextField inIdentify;
	
	private UIDriver driver;
	
	private String filePath;
	
	MyFrame(String x)
	{
		filePath = x;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.addComponentListener(this);
		
		button = new JButton("Begin Test");
		button.addActionListener(this);
		
		inIdentify = new JTextField();
		inIdentify.setPreferredSize(new Dimension(250, 40));
		inIdentify.setText("ID_GroupX_Age_Gender_TestX");
		
		buttonLabel = new JLabel();
		buttonLabel.setForeground(Color.RED);
		buttonLabel.setText("");
		buttonLabel.setVisible(true);
		
		this.add(button);
		this.add(inIdentify);
		this.add(buttonLabel);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void runTarget(Boolean targetUp) //target is the up state
	{
		if(targetUp)
		{
			System.out.println("up");
			targetLabel.setLocation(targetLabel.getX(), targetLabel.getY()-60);
		}
		else
		{
			System.out.println("down");
			targetLabel.setLocation(targetLabel.getX(), targetLabel.getY()+60);
		}
		
		flash(true);
	}
	
	public void clearTarget()
	{
		flash(false);
		
		targetLabel.setLocation((whiteS.getWidth()-targetLabel.getWidth())/2,
				(whiteS.getHeight()-targetLabel.getHeight())/2);
		
		System.out.println("cleared");
	}
	
	public void flash(boolean state)
	{
		whiteS.setLocation((this.getWidth()-whiteS.getWidth())/2, (this.getHeight()-whiteS.getHeight())/2);

		//whiteS.setOpaque(!state);
		
		whiteS.setVisible(state);
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public void setDriver(UIDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{		
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
	}

	@Override
	public void componentHidden(ComponentEvent e)
	{		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//create the datafolder and store the files in the Documents
		
		if(e.getSource() == button)
		{
			boolean result = false;
			
			String temp = filePath;
			
			try
			{
				filePath += File.separator + inIdentify.getText()+ ".txt";
				
				System.out.println(filePath);
				
				File f = new File(filePath);
				result = f.createNewFile();
				
				System.out.println(result);
				
			
				if(result) //test if successfully created a new file  
				{  
					System.out.println("file created "+f.getCanonicalPath()); //returns the path string  
					
					FileWriter writing = new FileWriter(f);
					writing.write(f.getName() + "\n\n");
					writing.close();
					
					//test writing to file
					/*FileWriter writing = new FileWriter(f);
					writing.write("blah blah blah");
					writing.close();*/
				}  
				else  
				{  
					
					filePath = temp;
					
					System.out.println("File already exist at location: "+f.getCanonicalPath());
					buttonLabel.setText("File already exist at location: "+f.getCanonicalPath());
					this.setSize(new Dimension(380 + buttonLabel.getText().length() * 3, 120));
					
	
				}
			}
			catch(Exception e1)
			{
				
				System.err.println("Unknown error with file creation");
				e1.printStackTrace();
			}
			/*
			if(result)
			{	
				this.remove(buttonLabel);
				this.remove(button);
				this.remove(inIdentify);
				this.setLayout(null);
				
				setExtendedState(MyFrame.MAXIMIZED_BOTH);
				whiteS = new JLabel();
				whiteS.setBounds(0, 0, 300, 300);
				whiteS.setBackground(Color.white);
				whiteS.setOpaque(true);
				whiteS.setVisible(false);
				
				whiteS.setLocation((this.getWidth()-whiteS.getWidth())/2,
						(this.getHeight()-whiteS.getHeight())/2);
				
				targetLabel = new JLabel();
				targetLabel.setBounds(0,0,65,65);
				targetLabel.setBackground(Color.black);
				targetLabel.setOpaque(true);
						
				this.getContentPane().setBackground(Color.black);
				this.add(whiteS);
				whiteS.add(targetLabel);
				
				targetLabel.setLocation((whiteS.getWidth()-targetLabel.getWidth())/2,
						(whiteS.getHeight()-targetLabel.getHeight())/2);
				
				this.requestFocus();
				
				driver.beginTest();
			}*/
		}
	}
}