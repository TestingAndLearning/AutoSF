package com.logic.auto;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.commands.auto.MainBot;

/** This class contains actions that the computer should take to progress the program. 
 * 	It should not perform any functions from the check class. **/
public class Perform
{
	private MainBot bot;
	private int delayTime = 200;
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
	
	/** Remember to clean up code above since it was from different project. **/
	public void wait(int time)
	{
		bot.sleep(time);
	}
	
	public void backToHome()
	{
		moveAndClick(50,300);
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_CONTROL);
		bot.robot.keyPress(KeyEvent.VK_A);
		bot.robot.keyRelease(KeyEvent.VK_A);
		bot.robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_DELETE);
		bot.robot.keyRelease(KeyEvent.VK_DELETE);
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_ENTER);
		bot.robot.keyRelease(KeyEvent.VK_ENTER);
		wait(delayTime);
	}
	
	//Combines move and click.
	public void moveAndClick(int x,int y)
	{
		bot.sleep(delayTime);
		bot.robot.mouseMove(x, y);
		bot.sleep(400);
		bot.robot.mousePress(InputEvent.BUTTON1_MASK);
		bot.robot.mouseRelease(InputEvent.BUTTON1_MASK);
		bot.sleep(delayTime);
	}
	
	//Moves mouse to search bar on left, pastes the first account from list and presses enter to search. 
	public void searchAccount()
	{
		System.out.println("Searching account. ");
		wait(delayTime);
		moveAndClick(50,300);
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_CONTROL);
		bot.robot.keyPress(KeyEvent.VK_A);
		bot.robot.keyRelease(KeyEvent.VK_A);
		bot.robot.keyRelease(KeyEvent.VK_CONTROL);
		bot.type(bot.getCurrentAccount());
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_ENTER);
		bot.robot.keyRelease(KeyEvent.VK_ENTER);
		wait(delayTime);
		System.out.println("Finished searching account. ");
	}
	
	public void clickAccount()
	{
		System.out.println("Clicknig account");
		moveAndClick(305,475);
		System.out.println("Finished Clicking account. ");
	}
	
	//Copies the text of the page and parses it. 
	public void copyText()
	{
		System.out.println("Copying text. ");
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_CONTROL);
		bot.robot.keyPress(KeyEvent.VK_A);
		bot.robot.keyRelease(KeyEvent.VK_A);
		bot.robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_CONTROL);
		bot.robot.keyPress(KeyEvent.VK_C);
		bot.robot.keyRelease(KeyEvent.VK_C);
		bot.robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(400);
		System.out.println("Finished copying text. ");
	}
	
	//Changes windows to text editor
	public void changeTabs()
	{
		System.out.println("Changing tabs. ");
		wait(delayTime);
		bot.robot.keyPress(KeyEvent.VK_ALT);
		bot.robot.keyPress(KeyEvent.VK_TAB);
		bot.robot.keyRelease(KeyEvent.VK_TAB);
		bot.robot.keyRelease(KeyEvent.VK_ALT);
		wait(delayTime);
		System.out.println("Finished changing tabs. ");
	}
	
	//Moves to click the texteditor. 
	public void clickTextEditor()
	{
		moveAndClick(1600,500);
	}
	
	//Trims clipboard and types CS section in HTML tables. 
	public void typeCSInfo() throws UnsupportedFlavorException, IOException
	{
		System.out.println("Typing CS Info");
		wait(delayTime);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String clipboardResult = (String) clipboard.getData(DataFlavor.stringFlavor);
		String csSection = clipboardResult.split("CS Notes")[1].split("CS Demo Date")[0];
		
		bot.robot.keyPress(KeyEvent.VK_CONTROL);
		bot.robot.keyPress(KeyEvent.VK_END);
		bot.robot.keyRelease(KeyEvent.VK_END);
		bot.robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(delayTime);
		
		bot.type("\n" + "<tr class='trs' id='" + bot.getCurrentAccount().replaceAll("\\s+","-").toLowerCase() + "'>" + "\n" +
				"<td>"+bot.getCurrentAccount()+"</td>"+"\n" + 
				"<td>"+bot.getCurrentValue()+"</td>"+"\n" +
				"<td>"+ csSection + "</td>" + "\n" +
				"</tr>" + "\n");
		System.out.println("Finished typing CS Info");
		wait(1000);
	}
	
}
