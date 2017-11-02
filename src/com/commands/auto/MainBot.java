package com.commands.auto;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.logic.auto.Check;
import com.logic.auto.Perform;
import com.logic.auto.Scan;

/** This is is the main bot class that determines which actions to take. 
 * 	The actions are ran in the specified order listed in this class 
 * 	for better readability and convenience when changes to company procedures 
 * 	are implemented. **/
public class MainBot implements Runnable
{
	//TODO Should turn public variables into getters. Temporarily using public to test out new functions. 	
	private Frames frames;	
	public boolean release = false;
	public String accountArea[];
	public int currentAccountIndex = 0;
	public boolean skipNext = false;
	public String choice = "";
	public Robot robot;
	public Type typeString;

	//public MainBot(Frames frames, int nPins, int interval)
	public MainBot(Frames frames, String choice, boolean release, String accountArea)
	{
		try
		{
			robot = new Robot();
			//this.interval = interval;
			this.frames = frames;
			this.choice = choice;
			this.release = release;
			typeString = new Type();
			this.accountArea = accountArea.split("\n");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		try
		{
			frames.setProgressState(false);
			Thread.sleep(1000);
			
			
			Perform perform = new Perform(MainBot.this);
			Scan scan = new Scan(MainBot.this, perform);
			Check check = new Check(MainBot.this, perform, scan);
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			String clipboardResult = (String) clipboard.getData(DataFlavor.stringFlavor);
			//String csSection = clipboardResult.split("CS Notes")[1].split("CS Demo Date")[0];
			String csSection = "hello";
			

			//Sets out the order of steps to take from the beginning
				for (;currentAccountIndex < accountArea.length; currentAccountIndex++)
				//for(int i = 0; i < 1; i++)
				{		
						/*
						String testString = Arrays.toString(pinArea).replace(",", "\n").replace("[", "").replace("]", "").replace(" ", "");
						System.out.println(testString);
						
						*/
						System.out.println(csSection);
						perform.searchAccount();
						
						
						/* 
						perform.searchPin();
						check.searchResults();
						check.noRead();
						perform.exam(choice);
						check.goBack();
						perform.fixError();
						scan.fixedError();
						perform.goBack();
						check.home();
						perform.refreshCDS();
						updatePin();
						toLog();
						*/
						
						synchronized(this)
						{
							while (!release)
							{
								wait();
							}
						}
				}
			
			frames.setProgressState(true);
			JOptionPane.showMessageDialog(null, "Done.");
		} 
		catch (InterruptedException | UnsupportedFlavorException | IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/** Allows the other classes to use sleep functions with bot.sleep(n) easily 
	 * 	without having to throw and catch exceptions every time. **/
	public void sleep(int n)
	{
		try
		{
			Thread.sleep(n);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	/** Stops the entire program temporarily. **/
	public void stop()
	{
		System.out.println("Stopping. ");
		release = false;
	}
	
	/** Resumes the program where it left off, but not recommended for regular use because screen may be different and the program may not detect it. **/
	synchronized void resume()
	{
		System.out.println("Resuming. ");
		release = true;
		notify();		//notify wakes up the first thread that called for wait(). 
	}
	
	/** Helps stop the program even if a method is in a loop. ie. checking for pixels. **/
	public void stopped()
	{
		while (!release)
		{
			sleep(1000);
		}
	}
	
	/** Links to Type class for typing string function **/
	public void type(String str)
	{
		typeString.type(str);
	}
	
	/** Appends the current pin number to the no-read text box. **/
	public void appendAccount()
	{
		frames.pinArea2.append(accountArea[currentAccountIndex]+ "\n");
	}
	
	/** Updates that pins to be released text box by removing pins already released. **/
	public void updatePin()
	{
		String[] updatedPin = Arrays.copyOfRange(accountArea, currentAccountIndex + 1, accountArea.length); //+1 for current pins to clear whole box after everything is done. 
		frames.accountArea.setText(Arrays.toString(updatedPin).replace(",", "\n").replace("[", "").replace("]", "").replace(" ", "")); //Default display of toString from array shows square brackets with commas separating each value. This replaces it. 
	}	
	
	/** Creates/updates the log file for pins that need to be released. 
	 * 	This should be equal to the pinArea and pinArea2. **/
	public void toLog()
	{
		//TODO Change path to same as .jar path. 
		try (PrintWriter out = new PrintWriter("h:\\p\\log.txt"))
		{
			String[] rawPinText = Arrays.copyOfRange(accountArea, currentAccountIndex + 1, accountArea.length); 
			String currentAccountText = Arrays.toString(rawPinText).replace(",", "\r\n").replace("[", "").replace("]", "").replace(" ", "");
			
			//TODO check if noread pin is writing correctly onto file. 
			String[] rawNoreadText = Arrays.copyOfRange(frames.pinArea2.getText().split("\n"), 0, frames.pinArea2.getText().split("\n").length); 
			String currentNoreadText = Arrays.toString(rawNoreadText).replace(",", "\r\n").replace("[", "").replace("]", "").replace(" ", "");
			
			out.print("@@@@@ to be @@@@@. " + "\r\n" + "===================" + "\r\n" + currentAccountText
					+ "\r\n\r\n" + "@@@@@" + "\r\n" + "===================" + "\r\n" + currentNoreadText);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/** Remember to clean up code above since it was from different project. **/
	//Checks for folder and id card icon to see if there is only 1 result for account. 
	public String getCurrentAccount()
	{
		return accountArea[currentAccountIndex].split("\\$")[0].trim();
	}
	
	public String getCurrentValue()
	{
		return "$" + accountArea[currentAccountIndex].split("\\$")[1].trim();
	}
	
}