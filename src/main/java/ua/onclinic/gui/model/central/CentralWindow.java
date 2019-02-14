package ua.onclinic.gui.model.central;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Центральное главное окно
 * 
 * @author Карпенко А. В.
 *
 * @date 13 трав. 2017 р.
 */
public class CentralWindow extends JPanel
{
	private static final long serialVersionUID = 8933583629663815063L;
	
	public static final int USER_WINDOW = 0x01;
	public static final int ADMIN_WINDOW = 0x02;
	
	private final CentralAdminWindow _adminWindow = new CentralAdminWindow();
	private final CentralUserWindow _userWindow = new CentralUserWindow();
	
	private int _activeWindow = USER_WINDOW;
	
	public CentralWindow()
	{
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new BorderLayout());
	}
	
	public CentralAdminWindow getAdminWindow()
	{
		return _adminWindow;
	}
	
	public CentralUserWindow getUserWindow()
	{
		return _userWindow;
	}
	
	public int getActiveWindow()
	{
		return _activeWindow;
	}
	
	public void showAdminWindow()
	{
		_activeWindow = ADMIN_WINDOW;
		remove(_userWindow);
		add(_adminWindow);
		revalidate();
		repaint();
	}
	
	public void showUserWindow()
	{
		_activeWindow = USER_WINDOW;
		remove(_adminWindow);
		add(_userWindow);
		revalidate();
		repaint();
	}
	
	public void hideAdminWindow()
	{
		remove(_adminWindow);
		revalidate();
		repaint();
	}
	
	public void hideUserWindow()
	{
		remove(_userWindow);
		revalidate();
		repaint();
	}
}