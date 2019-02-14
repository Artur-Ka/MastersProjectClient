package ua.onclinic.gui.model;

import java.awt.Component;

import javax.swing.JOptionPane;

import ua.onclinic.Config;

public class OptionPane extends JOptionPane
{
	private static final long serialVersionUID = 3893651060837132856L;
	
	private static final String EXIT_TEXT_1 = Config.LANGUAGE.getString("exitText1");
	private static final String EXIT_TEXT_2 = Config.LANGUAGE.getString("exitText2");
	private static final String[] OPTIONS = {Config.LANGUAGE.getString("ok"), Config.LANGUAGE.getString("cancel")};
	
	public static String showInputDialog(Component parentComponent)
	{
		final Object dialog = showInputDialog(parentComponent);
		
		if (dialog == null || !(dialog instanceof String))
			return null;
		
		return (String) dialog;
	}
	
	public static int showOptionDialog(Component parentComponent)
	{
		return showOptionDialog(parentComponent, EXIT_TEXT_1, EXIT_TEXT_2, YES_NO_OPTION, QUESTION_MESSAGE, null, OPTIONS, OPTIONS[0]);
	}
}