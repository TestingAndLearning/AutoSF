package com.logic.auto;

import java.awt.Color;
import java.awt.event.InputEvent;

import com.commands.auto.MainBot;

public class Scan
{
	private MainBot bot;
	private Perform perform;
	private boolean redone = false;
	
	public Scan(MainBot bot, Perform perform)
	{
		try
		{
			this.bot = bot;
			this.perform = perform;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void test()
	{
		System.out.println("Scan test.");
	}
	
	/** Scans for saved prompt and looks for pink bar. **/
	public void Pink(int x, int y)
	{		
		boolean scan = true;
		int counter = 0;
		Color color = bot.robot.getPixelColor(x, y);
		Color color2 = bot.robot.getPixelColor(1035, 306);
		while (scan)
		{
			//Checks for stop status
			if (!bot.release)
			{
				System.out.println("Stop detected");
				bot.stopped();
			}
			
			System.out.println("Scanning for matching pixels. Looking for @@@@@ or @@@@@. ");
			for (; y < 550; y++)
			{
				color = bot.robot.getPixelColor(x, y);
				if  (color.getRed() == 217 &&
					color.getBlue() == 217 &&
					color.getGreen() == 217)
				{
					scan = false;
				}
			}
			System.out.println("@@@@@. ");
			
			for (; y > 480; y--)
			{
				color = bot.robot.getPixelColor(x, y);
				if  (color.getRed() == 217 &&
					color.getBlue() == 217 &&
					color.getGreen() == 217)
				{
					scan = false;
				}
			}
			System.out.println("@@@@@. ");
			
			//Finding @@@@@. 
			if (scan)
			{
				System.out.println("Finding @@@@@. ");
				color2 = bot.robot.getPixelColor(1035, 306);
				
				if (color2.getRed() == 242 &&
				color2.getGreen() == 222 &&
				color2.getBlue() == 222)
				{
					redone = true;
					scan = false;
				}
				
				//Pressed @@@@@ again if none of the pixels are found because sometimes page will not even respond until clicked again. 
				if (counter == 5)
				{
					//Scan has to go over ~5 times before it clicks. 
					System.out.println("Pressing @@@@@ again. ");
					counter = 0;
					bot.robot.mouseMove(1844, 901);	//This was adjusted to avoid hitting the @@@@@ button if on @@@@@. 
					bot.sleep(200);
					bot.robot.mousePress(InputEvent.BUTTON1_MASK);
					bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
					bot.sleep(200);
				}
			}
			counter++;
		}
	}
	
	/** **/
	
	/** Scans for @@@@@ screen @@@@@ button (the screen after clicking @@@@@). 
	 * 	Also looks for @@@@@ @@@@@ @@@@@ @@@@@. **/
	public void Letter(int x, int y)
	{		
		boolean scan = true;
		int counter = 0;
		Color color = bot.robot.getPixelColor(x, y);
		Color color2 = bot.robot.getPixelColor(1035, 874);
		while (scan)
		{
			//Checks for stop status
			if (!bot.release)
			{
				System.out.println("Stop detected");
				bot.stopped();
			}
			
			System.out.println("Scanning for matching pixels. Looking for @@@@@ or @@@@@ @@@@@. ");
			for (; y < 400; y++)
			{
				color = bot.robot.getPixelColor(x, y);
				if  (color.getRed() == 215 &&
					color.getBlue() == 215 &&
					color.getGreen() == 215)
				{
					scan = false;
				}
			}
			System.out.println("@@@@@ for prompt. ");
			
			for (; y > 340; y--)
			{
				color = bot.robot.getPixelColor(x, y);
				if  (color.getRed() == 215 &&
					color.getBlue() == 215 &&
					color.getGreen() == 215)
				{
					scan = false;
				}
			}
			System.out.println("@@@@@ for prompt. ");		
			
			//Checks if @@@@@ @@@@@ was clicked. If clicked, then proceed to @@@@@ again and start next loop. 
			if (scan)
			{
				//If it has been too long since response, then click @@@@@ @@@@@. 
				if (counter == 4)
				{
					//First clicks @@@@@ just in-case. Added as experiment, can remove if not working well. 
					bot.robot.mouseMove(1820, 897);
					bot.sleep(200);
					bot.robot.mousePress(InputEvent.BUTTON1_MASK);
					bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
					bot.sleep(200);
					
					//Clicks @@@@@ @@@@@ button. 
					bot.robot.mouseMove(1059, 550);
					bot.sleep(300);
					bot.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					bot.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					bot.sleep(500);
					counter = 0;
				}
				
				//Scans for @@@@@ @@@@@ button or other @@@@@ button in @@@@@ @@@@@ screen. 
				for (int i = 950; i > 874; i--)
				{
					color2 = bot.robot.getPixelColor(1823, i);
					if  (color2.getRed() == 216 &&
						color2.getBlue() == 216 &&
						color2.getGreen() == 216)
					{
						scan = false;
					}
				}
				
				
				for (int i = 874; i < 950; i++)
				{
					color2 = bot.robot.getPixelColor(1823, i);
					if  (color2.getRed() == 216 &&
						color2.getBlue() == 216 &&
						color2.getGreen() == 216)
					{
						scan = false;
					}
				}
			}
			
			counter++;
		}
	}
	
	/** **/
	
	/** Checks if @@@@@ @@@@@ error has been attended to, then @@@@@ the @@@@@ after @@@@@ (with error). **/
	public void fixedError()
	{
		if (redone)
		{
			redone = false;
			perform.fixError();
			Pink(977, 500);
			fixedError();
		}
	}
}
