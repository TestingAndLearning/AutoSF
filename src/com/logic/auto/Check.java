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
	//Color color = bot.robot.getPixelColor(0,  0);
	//Color color2 = bot.robot.getPixelColor(0,  0);

	
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
	
	public void test()
	{
		scan.test();
	}
	
	/** Checks for blue bar and black graphic in middle. If found, then wait a few seconds and search again if taking too long. 
	 *  Checks for grey box in middle. If found, @@@@@
	 *  Checks for white screen. If found, waits indefinitely until loaded. 
	 *  Uses loading(3), searchCDS(), whiteScreen() **/
	public void searchResults()
	{	
		//Checks for stop status
		if (!bot.release)
		{
			System.out.println("Stop detected");
			bot.stopped();
		}

		//This helps check for any changes BEFORE the white screen. 
		//Checking for ANY signs of loading/progress. 
		Color color = bot.robot.getPixelColor(610, 330);
		Color color2 = bot.robot.getPixelColor(720, 158);
		Color color3 = bot.robot.getPixelColor(7, 111);
		Color color4 = bot.robot.getPixelColor(960, 10);
		if (color.getRed() != 71 &&
			color.getGreen() != 100 &&
			color.getBlue() != 151)
		{
			if 	(color2.getRed()!= 230 &&
				color2.getGreen()!= 230 &&
				color2.getBlue() != 230)
			{
				System.out.println("No grey box detected. ");
				loading(5, true);
			}
			
			//TODO Can maybe delete below 2.  
			if (color3.getRed()== 226 &&
				color3.getGreen() == 226 &&
				color3.getBlue() == 266)
			{
				System.out.println("Grey screen detected. ");
			}
			if (color4.getRed()== 255 &&
				color4.getGreen() == 255 &&
				color4.getBlue() == 255 )
			{
				System.out.println("White loading screen detected. ");
			}
		}
		
		//TODO Maybe delete because grey screen does not happen too long or often. 
		//Check greyed-out screen and wait if greyed-out. 
		color = bot.robot.getPixelColor(7, 111);
		color2 = bot.robot.getPixelColor(960, 158);
		if (color.getRed()== 226 &&
			color.getGreen() == 226 &&
			color.getBlue() == 266)
		{
			System.out.println("Grey screen detected. Waiting for screen to load.");
			bot.sleep(1000);
			loadForever("Grey ");
		}
		
		//Check white screen and wait indefinitely if white. 
		color = bot.robot.getPixelColor(960, 10);
		color2 = bot.robot.getPixelColor(720, 158); //was at 593, 330 before changes
		if (color.getRed()== 255 &&
			color.getGreen() == 255 &&
			color.getBlue() == 255 &&
			
			color2.getRed()== 255 &&
			color2.getGreen() == 255 &&
			color2.getBlue() == 255)
		{
			System.out.println("White screen detected. Waiting for screen to load.");
			bot.sleep(1000);
			loadForever("White ");
		}
		
		//This helps detect for exam AFTER white screen finishes loading. 
		//Checking for ANY signs of loading/progress in-case white screen took a while to load. 
		color = bot.robot.getPixelColor(610, 330);	//Blue box near check box. 
		color2 = bot.robot.getPixelColor(720, 158);
		if (color.getRed() != 71 &&
			color.getGreen() != 100 &&
			color.getBlue() != 151)
		{
			if 	(color2.getRed()!= 230 &&
				color2.getGreen()!= 230 &&
				color2.getBlue() != 230)
			{
				System.out.println("No grey or blue detected. ");
				loading(5, false);
			}
		}
		
		//Checking if @@@@@ from @@@@@ or not
		color = bot.robot.getPixelColor(720, 158);
        color2 = bot.robot.getPixelColor(720, 260);
        if (color.getRed() == 230 &&
        	color.getGreen() == 230 &&		//Gray box in middle
        	color.getBlue() == 230 &&
        	
        	color2.getRed()== 255 &&
			color2.getGreen() == 255 &&		//Box area not equal to grey
			color2.getBlue() == 255)
    	{
	        //Presses @@@@@ to avoid detecting screen again. 
	        bot.robot.mouseMove(1710, 20);
	        bot.sleep(delay+100);
	        bot.robot.mousePress(InputEvent.BUTTON1_MASK);
	        bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        bot.sleep(delay+100);
	        
	        postHome();
	        
        	System.out.println("Not in @@@@@ detected. @@@@@ from @@@@@.");
        	perform.searchCDS();
        	perform.searchPin();
        	searchResults();
		}
        
		//Checking for @@@@@. 
		color = bot.robot.getPixelColor(720, 158);
        color2 = bot.robot.getPixelColor(720, 260);
        if (color.getRed() == 230 &&
        	color.getGreen() == 230 &&		//Gray box in middle
        	color.getBlue() == 230 &&
        	
        	color2.getRed()!= 255 &&
			color2.getGreen() != 255 &&		//Box area not equal to blue
			color2.getBlue() != 255)
    	{
			//Clicks close. 
			System.out.println("@@@@@ detected. ");
			bot.robot.mouseMove(3565, 600);
			bot.sleep(delay+100);
	        bot.robot.mousePress(InputEvent.BUTTON1_MASK);
	        bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        bot.sleep(delay+100);
	        
	        perform.noread = true;
	        
	        //Presses @@@@@ to avoid detecting screen again. 
	        bot.robot.mouseMove(1710, 20);
	        bot.sleep(delay+100);
	        bot.robot.mousePress(InputEvent.BUTTON1_MASK);
	        bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        bot.sleep(delay+100);
	        
	        postHome();
	        
	        //Increments currentPin to skip this one and appends it to the no-read text box. 
	        bot.appendAccount();
	        bot.currentAccountIndex++;
	        bot.updatePin();
	        bot.sleep(delay+100);
		}
	}
	
	/** Waits for the loading for 2000ms c number of times. Re-do perform.searchPin() and check.searchResults if still loading. **/
	public void loading(int c, boolean searchAgain)
	{
		//Checks for stop status
		if (!bot.release)
		{
			System.out.println("Stop detected");
			bot.stopped();
		}
		
		for (int i = 0; i < c; i++)
		{
			//Checks for stop status
			if (!bot.release)
			{
				System.out.println("Stop detected");
				bot.stopped();
			}
			
			Color color = bot.robot.getPixelColor(610, 330);
			Color color2 = bot.robot.getPixelColor(1188,433); //For black box
			Color color3 = bot.robot.getPixelColor(960, 158);
			if (color.getRed()!= 71 &&
				color.getGreen() != 100 &&		//Blue box
				color.getBlue() != 151)
			{
				if (color2.getRed()== 3 &&
					color2.getGreen() == 3 &&
					color2.getBlue() == 3)
				{
					System.out.println("Black graphic detected. ");
					System.out.println("Waiting. " + (i+1) + "/" + c + ".");
					bot.sleep(2500);
				}
				
				if (color3.getRed()== 230 &&
					color3.getGreen() == 230 &&
					color3.getBlue() == 230)
				{
					System.out.println("Grey box detected. ");
					i = c;	//Stops the waiting loop. 
				}
				
				if (color2.getRed()!= 3 &&
					color2.getGreen() != 3 &&
					color2.getBlue() != 3 &&
					
					color3.getRed()!= 230 &&
					color3.getGreen() != 230 &&
					color3.getBlue() != 230)
				{
					System.out.println("No @@@@@ detected. ");
					System.out.println("Waiting. " + (i+1) + "/" + c + ".");
					bot.sleep(2500);
				}
				
			}
		}
		
		if (searchAgain)
		{
			Color color = bot.robot.getPixelColor(610, 330);
			Color color2 = bot.robot.getPixelColor(720, 158);
			
			if (color.getRed()!= 71 &&
				color.getGreen() != 100 &&
				color.getBlue() != 151)
			{
				if (color2.getRed() != 230 &&
					color2.getGreen() != 230 &&
					color2.getBlue() != 230)
				{
					System.out.println("Redoing search");
					perform.searchPin();
					searchResults();
				}
			}
		}
		
		if (!searchAgain)
		{
			bot.sleep(500);
			System.out.println("Re-checking search results. ");
			searchResults();	//This should not lead to exams and other procedures being stacked up performed again.  
		}
	}
	
	/** Detects white screen. If found, then waits indefinitely until not white screen. **/
	public void loadForever(String str)
	{
		//Checks for stop status
		if (!bot.release)
		{
			System.out.println("Stop detected");
			bot.stopped();
		}
		
		//Check white screen and wait if white
		Color color = bot.robot.getPixelColor(960,158);
		Color color2 = bot.robot.getPixelColor(593,330);
		while (color.getRed()== 255 &&
			color.getGreen() == 255 &&
			color.getBlue() == 255 &&
			
			color2.getRed()== 255 &&
			color2.getGreen() == 255 &&
			color2.getBlue() == 255)
		{
			System.out.println(str + "screen detected. Waiting for screen to load.");
			bot.sleep(2000);
			color = bot.robot.getPixelColor(960,158);
			color2 = bot.robot.getPixelColor(593,330);
		}
	}
	
	/** Checks for pink error bar. Proceed to fix in fixError(). **/
	public void goBack()
	{
		//Checks for stop status
		if (!bot.release)
		{
			System.out.println("Stop detected");
			bot.stopped();
		}
		scan.Pink(977, 500);
	}
	
	/** Function exists for better readability in MainBot. Just 1 line calling scanPix. **/
	public void home()
	{
		//Checks for stop status
		if (!bot.release)
		{
			System.out.println("Stop detected");
			bot.stopped();
		}
		
		System.out.println("@@@@@ found, looking for @@@@@ button. ");
		scan.Letter(1047, 376);
	}
	
	/** **/
	public void noRead()
	{
		if (perform.noread)
		{
	        postHome();
			
			perform.noread = false;
			perform.searchPin();
			searchResults();
		}
	}
	
	/** Checks for @@@@@ screen after pressing home button. **/
	public void postHome()
	{
		System.out.println("Checking for @@@@@ screen. ");
		//Checks for stop status
		if (!bot.release)
		{
			System.out.println("Stop detected");
			bot.stopped();
		}
		
		Color color = bot.robot.getPixelColor(989,  525);
		if (color.getRed() == 255 &&
			color.getGreen() == 255 &&
			color.getBlue() == 255)
		{
			bot.sleep(1000);
		}
	}
	
	/** Remember to clean up code above since it was from different project. **/
	public void searchResultsAreLoaded()
	{
		int folderX = 255;
		int folderY = 420;
		int blankspaceX = 245;
		int blankspaceY = 479;
		//color = bot.robot.getPixelColor(folderX,  folderY);
		//color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
		System.out.println("Checking searchResultsAreLoaded(). ");
		bot.sleep(1000);
		//while (!(color.getRed() < 200 && color2.getGreen() == 255))	//Checks if folder part is orange and there is a blank space between the two sections to prove that there is only one result. 
		//{
		//	System.out.println("searchResultsAreLoaded() not passed yet. ");
		//	bot.sleep(2000);
		//}
	}
	
	/** Remember to clean up code above since it was from different project. **/
	public void accountIsLoaded()
	{
		int folderX = 258;
		int folderY = 262;
		int blankspaceX = 247;
		int blankspaceY = 357;
		//color = bot.robot.getPixelColor(folderX,  folderY);
		//color2 = bot.robot.getPixelColor(blankspaceX,  blankspaceY);
		//System.out.println("Checking accountIsLoaded(). ");
		//bot.sleep(1000);
		//while (!(color.getRed() < 200 && color2.getGreen() == 255))	//Checks if folder part is orange and there is a blank space between the two sections to prove that there is only one result. 
		//{
			System.out.println("accountIsLoaded() not passed yet. ");
			bot.sleep(2000);
		//}
	}
	
	//Checks for folder and id card icon to see if there is only 1 result for account. 
	public void onlyHasOneResult()
	{
		
	}
}