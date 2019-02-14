package ua.onclinic.gui.model.central;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 13 трав. 2017 р.
 */
public class CentralUserWindow extends JPanel
{
	private static final long serialVersionUID = 8933583629663815063L;
	
	public CentralUserWindow()
	{
		// Отключено до авторизации
		setEnabled(false);
		
		setBackground(Color.pink);
	}
}