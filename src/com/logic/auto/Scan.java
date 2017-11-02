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
}
