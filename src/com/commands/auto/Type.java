package com.commands.auto;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/** This class helps typed entire sentences instead of just one letter at a type with robot's keypress function. **/
class Type extends Robot
{

public Type() throws AWTException
{
super();
}

/*public void pressEnter() 
{
keyPress(KeyEvent.VK_ENTER);
delay(50);
keyRelease(KeyEvent.VK_ENTER);
} */

public void pasteClipboard() 
{
keyPress(KeyEvent.VK_CONTROL);
keyPress(KeyEvent.VK_V);
delay(50);
keyRelease(KeyEvent.VK_V);
keyRelease(KeyEvent.VK_CONTROL); 
}

public void type(String text)
{ 
writeToClipboard(text);
pasteClipboard();
}

private void writeToClipboard(String s)
{
Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
Transferable transferable = new StringSelection(s);
clipboard.setContents(transferable, null);
}
}