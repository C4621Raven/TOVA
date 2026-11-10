import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class UIDriver implements Runnable, KeyListener
{
	private MyFrame frame;
	private ArrayList<Boolean> stimuliAry;
	
	private ArrayList<ReactionInstance> quarterData;
	private ArrayList<ReactionInstance> blockData;
	private ArrayList<ReactionInstance> totalData;
	
	private long showTimeLong;
	private int spotInAry, block, subSection;
	
	private int targetsHit;
	private int commisions; //flase positive, user response when its a nontarget
	private int ommssions; //when the user dosnt respond to a target
	
	private int numStimuli;
	private int numTargets;
	
	private Thread thread;
	
	private boolean readyToRead;
	
	public UIDriver(MyFrame frame)
	{
		this.frame = frame;
		this.frame.addKeyListener(this);
		stimuliAry = null;
		showTimeLong = 0;
		spotInAry = -1;
		block = 0;
		subSection = 0;
		quarterData = new ArrayList<>();
		blockData = new ArrayList<>();
		totalData = new ArrayList<>();
		readyToRead = false;
	}
	
	public void setUpStimuli(int stimuli, int targets)
	{
		//////////////////////////////////////////////////////////////////////////////
		numStimuli = stimuli;
		numTargets = targets;
		//////////////////////////////////////////////////////////////////////////////
		
		boolean tempAry[] = new boolean[numStimuli];
		for(int i = 0; i < numTargets; i++)
		{
			tempAry[i] = true;
		}
		
		//swap array around
		Random r = new Random();
	    for (int i = tempAry.length - 1; i > 0; i--)
	    {
	    	int index = r.nextInt(i);
	    	// swap 
	    	boolean tmp = tempAry[index];
	    	tempAry[index] = tempAry[i];
	    	tempAry[i] = tmp;
	    }
	    
	    stimuliAry = new ArrayList<>();
	    
	    for(boolean x : tempAry)
	    {
	    	stimuliAry.add(x);
	    }
	}
	
	protected void sleep(int time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void beginTest()
	{	
		if(thread != null)
		{
			throw new IllegalStateException("Thread already running");
		}
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run()
	{
		System.out.println("thread began");
		sleep(3000);
		
		//quarter 1
		subSection = 1;
		block = 1;
		
		quarterData = new ArrayList<>();
		blockData = new ArrayList<>();
		totalData = new ArrayList<>();
		
		setUpStimuli(162, 36);
		
		for(int i = 0; i < stimuliAry.size(); i++)
		{
			spotInAry = i;
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			    	showTimeLong = new Date().getTime();
			    	
			    	readyToRead = true;
			        
			    	frame.runTarget(stimuliAry.get(spotInAry));
			        System.out.println(new Date().getTime());
			    }
			});
			
			sleep(100);
			
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			        frame.clearTarget();
			    }
			});
			
			sleep(1900);
			
			if(readyToRead == true)
			{
				quarterData.add(null);
				blockData.add(null);
				totalData.add(null);
			}
		}
		
		readyToRead = false;
		caculateData(quarterData, false, "Quarter: " + subSection);
		
		//quarter 2
		subSection++;
		//block++;
		
		quarterData = new ArrayList<>();
		//blockData = new ArrayList<>();
		//totalData = new ArrayList<>();
			
		setUpStimuli(162, 36);
				
		for(int i = 0; i < stimuliAry.size(); i++)
		{
			spotInAry = i;
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			    	showTimeLong = new Date().getTime();
					    	
			    	readyToRead = true;
			        
			    	frame.runTarget(stimuliAry.get(spotInAry));
			        System.out.println(new Date().getTime());
			    }
			});
					
			sleep(100);
					
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			        frame.clearTarget();
			    }
			});
				
			sleep(1900);
			
			if(readyToRead == true)
			{
				quarterData.add(null);
				blockData.add(null);
				totalData.add(null);
			}
		}
				
		readyToRead = false;
		caculateData(quarterData, false, "Quarter: " + subSection);
		caculateData(blockData, false, "Block: " + block);
		
		//quarter 3 and block 2
		subSection++;
		block++;
				
		quarterData = new ArrayList<>();
		blockData = new ArrayList<>();
		//totalData = new ArrayList<>();
					
		setUpStimuli(162, 126);
						
		for(int i = 0; i < stimuliAry.size(); i++)
		{
			spotInAry = i;
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			    	showTimeLong = new Date().getTime();
							    	
			    	readyToRead = true;
					        
			    	frame.runTarget(stimuliAry.get(spotInAry));
			        System.out.println(new Date().getTime());
			    }
			});
							
			sleep(100);
							
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			        frame.clearTarget();
			    }
			});
						
			sleep(1900);
					
			if(readyToRead == true)
			{
				quarterData.add(null);
				blockData.add(null);
				totalData.add(null);
			}
		}
						
		readyToRead = false;
		caculateData(quarterData, false, "Quarter: " + subSection);
		//caculateData(blockData, false, "Block: " + block);
		
		//quarter 4
		subSection++;
		//block++;
				
		quarterData = new ArrayList<>();
		//blockData = new ArrayList<>();
		//totalData = new ArrayList<>();
					
		setUpStimuli(162, 126);
						
		for(int i = 0; i < stimuliAry.size(); i++)
		{
			spotInAry = i;
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			    	showTimeLong = new Date().getTime();
							    	
			    	readyToRead = true;
					        
			    	frame.runTarget(stimuliAry.get(spotInAry));
			        System.out.println(new Date().getTime());
			    }
			});
							
			sleep(100);
							
			EventQueue.invokeLater(new Runnable()
			{
			    public void run()
			    {
			        frame.clearTarget();
			    }
			});
						
			sleep(1900);
					
			if(readyToRead == true)
			{
				quarterData.add(null);
				blockData.add(null);
				totalData.add(null);
			}
		}
						
		readyToRead = false;
		caculateData(quarterData, false, "Quarter: " + subSection);
		caculateData(blockData, false, "Block: " + block);
		caculateData(totalData, true, "Total");
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		System.out.println(new Date().getTime() - showTimeLong);
		if(readyToRead)
		{
			
			
			ReactionInstance reactionData = new ReactionInstance(block, subSection, stimuliAry.get(spotInAry), new Date().getTime() - showTimeLong);
			
			quarterData.add(reactionData);
			blockData.add(reactionData);
			totalData.add(reactionData);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	public void caculateData(ArrayList<ReactionInstance> data, boolean raw, String title)
	{
		targetsHit = 0;
		commisions = 0;
		ommssions = 0;
		
		for(ReactionInstance dataPoint : data)
		{
			if(dataPoint == null)
			{
			}
			else if(dataPoint.target)
			{
				targetsHit++;
			}
			else if(!dataPoint.target)
			{
				commisions++;
			}
			
			ommssions = numTargets - targetsHit;	
		}
		
		//RT
		double sumReactTime = 0;
		for(ReactionInstance dataPoint : data)
		{
			if((dataPoint != null) && (dataPoint.target))
			{
				sumReactTime += dataPoint.reaction;
			}
		}
		
		double RT = sumReactTime / targetsHit;//<---------------------
		
		//RTV
		double numerator = 0;
		for(ReactionInstance dataPoint : data)
		{
			if((dataPoint != null) && (dataPoint.target))
			{
				numerator += (dataPoint.reaction - RT) * (dataPoint.reaction - RT);
			}
		}
		
		double RTV = Math.sqrt(numerator / targetsHit);//<---------------------
		
		//COM
		double COM = (commisions / (Double.valueOf(numStimuli) - numTargets)) * 100;//<---------------------
		
		//OM
		double OM = (ommssions / Double.valueOf(numTargets)) * 100;//<---------------------
		
		//d'
		
		//step 2
		double hitRate = 1 - (OM/100);
		double falseAlarmRate = 1 - (COM/100);
				
		if(hitRate == 0)
			hitRate = 0.00001;
		if(hitRate == 1)
			hitRate = 0.99999;
		if(falseAlarmRate == 0)
			falseAlarmRate = 0.00001;
		if(falseAlarmRate == 1)
			falseAlarmRate = 0.99999;
				
		//step 3
		double pHitRate = 1 - hitRate;
		double pFalseAlarmRate = 1 - falseAlarmRate;
		
		if(pHitRate > 0.5)
			pHitRate = 1 - pHitRate;
		if(pFalseAlarmRate > 0.5)
			pFalseAlarmRate = 1 - pFalseAlarmRate;
		
		//step 4
		double THitRate = Math.sqrt(
				Math.log(
						1 / (pHitRate * pHitRate)));
		
		double zHitRate = THitRate - (
				(
					2.515517 + 
					(0.802853 * pHitRate) + 
					(0.010328 * pHitRate * pHitRate)
				) 
				/ 
				(
					1 +
					(1.432788 * pHitRate) +
					(0.189269 * pHitRate * pHitRate) +
					(0.001308 * pHitRate * pHitRate * pHitRate)
				)
			);
		
		double TFalseAlarmRate = Math.sqrt(
				Math.log(
						1 / (pFalseAlarmRate * pFalseAlarmRate)));
		
		double zFalseAlarmRate = TFalseAlarmRate - (
				(
					2.515517 + 
					(0.802853 * pFalseAlarmRate) + 
					(0.010328 * pFalseAlarmRate * pFalseAlarmRate)
				) 
				/ 
				(
					1 +
					(1.432788 * pFalseAlarmRate) +
					(0.189269 * pFalseAlarmRate * pFalseAlarmRate) +
					(0.001308 * pFalseAlarmRate * pFalseAlarmRate * pFalseAlarmRate)
				)
			);
		
		if(pHitRate <= 0.5)
			zHitRate *= -1;
		if(pFalseAlarmRate <= 0.5)
			zFalseAlarmRate *= -1;
		
		double dPrime = zFalseAlarmRate - zHitRate;
		
		try
		{
			String preFile = Files.readString(Paths.get(frame.getFilePath()));

			File f = new File(frame.getFilePath());
			FileWriter writing = new FileWriter(f);
						
			writing.write(preFile + title + "\n" + 
					      "RTV: " + RTV + "\n" + 
					      "RT: " + RT + "\n" + 
					      "COM: " + COM + "%\n" + 
					      "OM: " + OM + "%\n" + 
					      "d': " + dPrime + "\n\n");
			
			if(raw)
			{
				writing.write("Raw Data:\n");
				for(ReactionInstance dataPoint : data)
				{
					if(dataPoint == null)
					{
						writing.write("User did not hit\n");
					}
					else
					{
						writing.write(dataPoint.twoString() + "\n");
					}
				}
			}
			writing.close();
			
		}
		catch(Exception e)
		{
			System.err.println("Error with file writing in UIDriver");
			e.printStackTrace();
		}

		System.out.println("RTV: " + RTV + ", RT: " + RT + ", COM: " + COM + ", OM: " + OM + ", d': " + dPrime);
	}
}