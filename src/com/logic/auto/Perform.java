package com.logic.auto;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.commands.auto.MainBot;

/** This class contains actions that the computer should take to progress the program. 
 * 	It should not perform any functions from the check class. **/
public class Perform
{
	private MainBot bot;
	private int delay = 200;
	public boolean noread = false;
	
	public Perform(MainBot bot)
	{
		try
		{
			this.bot = bot;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void test()
	{
		bot.appendAccount();
		bot.sleep(1000);
		bot.updatePin();
		bot.updatePin();
	}

	
	/** Highlights existing @@@@@ then pastes and searches **/
	public void searchPin()
	{
		if (!bot.release)
		{
			System.out.println("Stop detected");
			bot.stopped();
		}
		
		System.out.println("Searching Pin");
		
		Color color = bot.robot.getPixelColor(614, 393);
		Color color2 = bot.robot.getPixelColor(720, 10);
		
		if (color.getRed()!= 71 &&
				color.getGreen() != 100 &&
				color.getBlue() != 151 &&
				
				color2.getRed() != 255 &&
				color2.getGreen() != 255 &&
				color2.getBlue() != 255)
		{
			bot.robot.mouseMove(1767, 84);
			bot.sleep(delay);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseMove(1657, 84);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay);
			bot.type(bot.pinArea[bot.currentPin]);
			bot.sleep(delay);
		}
		
		color = bot.robot.getPixelColor(614, 393);
		color2 = bot.robot.getPixelColor(720, 10);
		
		if (color.getRed()!= 71 &&
			color.getGreen() != 100 &&
			color.getBlue() != 151 &&
			
			color2.getRed() != 255 &&
			color2.getGreen() != 255 &&
			color2.getBlue() != 255)
		{
			bot.robot.mouseMove(1852, 84);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(2000);
		}
	}
	
	
	/** Performs the @@@@@ and hits @@@@@. **/
	public void exam(String choice)
	{
		System.out.println("Loaded exam screen. Waiting to perform exam. ");
		
		//Sometimes there will be fragments of the screen loaded, but will not actually be loaded. 
		//This will wait until it is certain that the exam will be usable before @@@@@
		Color color= bot.robot.getPixelColor(614, 393);	//
		
		while (	color.getRed()!= 71 &&
				color.getGreen() != 100 &&
				color.getBlue() != 151)
		{
			System.out.println("Waiting for @@@@@ to load. ");
			bot.sleep(1000);
			color = bot.robot.getPixelColor(614, 393);
		}
		
			System.out.println("@@@@@");
			
			//Clicks @@@@@
			if (choice == "Opened")
			{
				bot.robot.mouseMove(494, 361);
				bot.sleep(delay);
				bot.robot.mousePress(InputEvent.BUTTON1_MASK);
				bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
				bot.sleep(delay);
			}
			
			//Clicks @@@@@
			if (choice == "Triaged")
			{
				bot.robot.mouseMove(553, 361);
				bot.sleep(delay);
				bot.robot.mousePress(InputEvent.BUTTON1_MASK);
				bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
				bot.sleep(delay);
			}
			
			//Clicks @@@@@
			bot.robot.mouseMove(553, 422);
			bot.sleep(delay);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay);
			
			//Clicks @@@@@
			if (choice == "Triaged")
			{
				bot.robot.mouseMove(689, 510);
				bot.sleep(delay);
				bot.robot.mousePress(InputEvent.BUTTON1_MASK);
				bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
				bot.sleep(delay);
			}
			
			//Clicks @@@@@
			bot.robot.mouseMove(472, 586);
			bot.sleep(delay);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay);
			
			//Highlights @@@@@
			bot.robot.mouseMove(863, 806);
			bot.sleep(200);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.sleep(200);
			bot.robot.mouseMove(455, 756);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay);
			
			
			//Types text into @@@@@
			if (choice == "Opened")
			{
				bot.robot.keyPress(KeyEvent.VK_SHIFT);
				bot.robot.keyPress(KeyEvent.VK_O);
				bot.robot.keyRelease(KeyEvent.VK_O);
				bot.robot.keyRelease(KeyEvent.VK_SHIFT);
				bot.type("pened and examined. ");
				bot.robot.keyPress(KeyEvent.VK_SHIFT);
				bot.robot.keyPress(KeyEvent.VK_R);
				bot.robot.keyRelease(KeyEvent.VK_R);
				bot.robot.keyRelease(KeyEvent.VK_SHIFT);
				bot.type("@@@@@ @@@@@ @@@@@. ");
				bot.sleep(delay);
				
			}
			
			//Types @@@@@
			if (choice == "Triaged")
			{
				bot.robot.keyPress(KeyEvent.VK_SHIFT);
				bot.robot.keyPress(KeyEvent.VK_X);
				bot.robot.keyRelease(KeyEvent.VK_X);
				bot.robot.keyRelease(KeyEvent.VK_SHIFT);
				bot.type("@@@@@ @@@@@ @@@@@. ");
				bot.robot.keyPress(KeyEvent.VK_SHIFT);
				bot.robot.keyPress(KeyEvent.VK_R);
				bot.robot.keyRelease(KeyEvent.VK_R);
				bot.robot.keyRelease(KeyEvent.VK_SHIFT);
				bot.type("@@@@@ @@@@@ @@@@@");
				bot.sleep(delay);
			}
			
			//Moves to save. Sleep time at end can be reduced, but this part is also slow/unpredictable
			//in the system so it was best to give it a bit more time before it moves on, just to be safe. 
			bot.robot.mouseMove(1820, 898);
			bot.sleep(delay);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			System.out.println("@@@@@ completed.");
			bot.sleep(300);
	}
	
	/** Fixes @@@@@ error and then calls itself back if still not fixed. **/
	public void fixError()
	{
		//If top bar is red, then @@@@@ again after closing @@@@@ notification. 
		Color color = bot.robot.getPixelColor(1035, 306);
		if (color.getRed() == 242 &&
			color.getGreen() == 222 &&
			color.getBlue() == 222)
		{
			System.out.println("Error detected. Redoing @@@@@ (red). ");
			bot.robot.mouseMove(1860, 308);
			bot.sleep(200);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(300);
			exam(bot.choice);
			bot.sleep(250);		//Extra 300ms to wait time after fixing because this part usually takes a bit longer to load
		}
	}
	
	/** Clicks the @@@@@ button and end the loop */
	public void goBack()
	{
		//Moves mouse to @@@@@
		System.out.println("Going back");
		bot.robot.mouseMove(854, 550);
		bot.sleep(delay);
		bot.robot.mousePress(InputEvent.BUTTON1_MASK);
		bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
		bot.sleep(500);
	}
	
	/** Pastes existing PIN into @@@@@ and presses @@@@@. 
	 *	Checks for loading bar then clicks @@@@@. 
	 *	Checks for loading bar then clicks @@@@@. 
	 *	Clicks @@@@@ then @@@@@. **/
	public void searchCDS()
	{
		//MAKE SURE THE @@@@@ DOES NOT HAVE ANY @@@@@ OR OTHER @@@@@. 
		//TODO attempt to fix this in initialize method. 
		System.out.println("Searching CDS.");
		
        //Pastes PIN
        bot.robot.mouseMove(3360, 233);
        bot.sleep(delay+100);
        bot.robot.mousePress(InputEvent.BUTTON1_MASK);
        bot.sleep(delay+100);
        bot.robot.mouseMove(3200, 233);
        bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
        bot.sleep(delay+100);
        bot.type(bot.pinArea[bot.currentPin]);
        
        bot.sleep(delay+100);

        //Presses enter to search pin. 
        bot.robot.keyPress(KeyEvent.VK_ENTER);
        bot.robot.keyRelease(KeyEvent.VK_ENTER);
        bot.sleep(2000);

        //Detecting @@@@@ (2+ @@@@@ version). 
        Color color = bot.robot.getPixelColor(3330, 385);
        if (color.getRed() == 255 &&
        	color.getGreen() == 255 &&
        	color.getBlue() == 255)
        {
    		System.out.println("@@@@@ detected. Refreshing and appending pin. ");
    		bot.robot.mouseMove(3140, 187);
    		bot.sleep(delay+200);
    		bot.robot.mousePress(InputEvent.BUTTON1_MASK);
    		bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
    		bot.sleep(delay);
    		
	        noread = true;
        }
        
        //Detecting @@@@@ prompt. 
		color = bot.robot.getPixelColor(3463, 623);
		if (color.getRed() == 242 &&
				color.getGreen() == 245 &&
				color.getBlue() == 247)
		{
			//@@@@@close. 
			System.out.println("@@@@@ detected. @@@@@. ");
			bot.robot.mouseMove(3549, 623);
			bot.sleep(delay+200);
	        bot.robot.mousePress(InputEvent.BUTTON1_MASK);
	        bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        bot.sleep(delay+200);
	        
	        noread = true;
		}
		
		if (!noread)
		{
			//Clicks @@@@@ then @@@@@ to @@@@@
			bot.robot.mouseMove(3012, 539);
			bot.sleep(delay+300);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay+300);
			bot.robot.mouseMove(3012, 560);
			bot.sleep(delay+300);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(2000);
			
			//TODO add wait for load to complete. Although probably unnecessary because 
			//load times in this program seem to be static and brief. Using sleep seems faster. 
			
			//Clicks @@@@@ for @@@@@ then @@@@@. 
			bot.robot.mouseMove(3368, 545);
			bot.sleep(delay+100);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay+100);
			bot.robot.mouseMove(3368, 709);
			bot.sleep(delay+100);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay+100);
			//Clicks refer
			bot.robot.mouseMove(3722, 530);
			bot.sleep(delay+100);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(3000);
			
			//TODO add wait for refer complete. However, also static and brief so may not be needed. 
			
			//Clicks @@@@@
			bot.robot.mouseMove(2947, 229);
			bot.sleep(delay+100);
			bot.robot.mousePress(InputEvent.BUTTON1_MASK);
			bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.sleep(delay+100);
		}
		
		//Moved down to bottom so the method finishes all blocks first before moving on. This way it does not 
		//stack up tasks. 
		if (noread)
		{
			System.out.println("Incrementing PIN to skip no-read. ");
	        //Increments currentPin to skip this one and appends it to the no-read text box. 
	        bot.appendAccount();
	        if (bot.currentPin < bot.pinArea.length) //This avoids error when incrementing pin above the limit. 
	        {
	        	bot.currentPin++;
	        }
	        bot.updatePin();
	        bot.sleep(2000);  
		}
	}
	
	//Clicks @@@@@ @@@@@ button to @@@@@. 
	public void refreshCDS()
	{
		System.out.println("@@@@@ @@@@@");
		bot.robot.mouseMove(3140, 187);
		bot.sleep(delay+200);
		bot.robot.mousePress(InputEvent.BUTTON1_MASK);
		bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
		bot.sleep(delay);
	}
}
