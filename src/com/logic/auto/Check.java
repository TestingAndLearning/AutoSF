package com.logic.auto;

import java.awt.Color;
import java.awt.event.InputEvent;

import com.commands.auto.MainBot;

/** This class performs checks and occasionally calls perform functions 
 *  to perform fixes, depending on the checks. **/
public class Check
{
	private MainBot bot;
	private int delay = 200;
	private Perform perform;
	private Scan scan;
	private boolean redone = false;
	
	public Check(MainBot bot, Perform perform, Scan scan)
	{
		try
		{
			this.bot = bot;
			this.perform = perform;
			this.scan = scan;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/** Remember to clean up code above since it was from different project. **/
	//Checks to see if search results are loaded by seeing the folder and blank space between sections to show only one search item in result. 
	public void searchResultsAreLoaded()
	{
		System.out.println("Checking if search results are loaded. ");
		int folderX = 305;
		int folderY = 420;
		int blankspaceX = 245;
		int blankspaceY = 497;
		Color color = bot.robot.getPixelColor(folderX,  folderY);
		Color color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
		System.out.println("Checking searchResultsAreLoaded(). ");
		bot.sleep(1000);
		while (!(color.getRed() > 200 && color2.getGreen() == 255))	//Checks if folder part is orange and there is a blank space between the two sections to prove that there is only one result. 
		{
			System.out.println("searchResultsAreLoaded() not passed yet. ");
			bot.sleep(2000);
			color = bot.robot.getPixelColor(folderX,  folderY);
			color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
			
			System.out.println("R: "+ color.getRed() +" G: " + color2.getGreen());
			
		}
		bot.sleep(1000);
		System.out.println("Checking if search results are loaded. ");
	}
	
	//Checks to see if account page is loaded by looking at the orange folder in the top and an accompanying blank space. 
	public void accountIsLoaded()
	{
		System.out.println("Checking if account is loaded. ");
		int folderX = 258;
		int folderY = 262;
		int blankspaceX = 247;
		int blankspaceY = 357;
		Color color = bot.robot.getPixelColor(folderX,  folderY);
		Color color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
		System.out.println("Checking accountIsLoaded(). ");
		bot.sleep(1000);
		while (!(color.getRed() > 200 && color2.getGreen() == 255))	//Checks if folder part is orange and there is a blank space between the two sections to prove that there is only one result. 
		{
			System.out.println("accountIsLoaded() not passed yet. ");
			bot.sleep(2000);
			color = bot.robot.getPixelColor(folderX,  folderY);
			color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
		}
			bot.sleep(1000);
		System.out.println("Finished checking if account is loaded. ");
	}
	
	//Checks to see if folder is gone to indicate it is back to default screen. 
	public void isBackHome()
	{
		System.out.println("Checking if account is loaded. ");
		int folderX = 258;
		int folderY = 262;
		int blankspaceX = 247;
		int blankspaceY = 357;
		Color color = bot.robot.getPixelColor(folderX,  folderY);
		Color color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
		System.out.println("Checking accountIsLoaded(). ");
		bot.sleep(1000);
		while ((color.getRed() > 200 && color2.getGreen() == 255))	//Checks if folder part is orange and there is a blank space between the two sections to prove that there is only one result. 
		{
			System.out.println("accountIsLoaded() not passed yet. ");
			bot.sleep(2000);
			color = bot.robot.getPixelColor(folderX,  folderY);
			color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
		}
			bot.sleep(1000);
		System.out.println("Finished checking if account is loaded. ");
	}
}