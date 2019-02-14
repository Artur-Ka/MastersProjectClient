package ua.onclinic.gui.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import ua.onclinic.Config;
import ua.onclinic.ServerThread;
import ua.onclinic.gui.AboutFrame;
import ua.onclinic.gui.MainFrame;
import ua.onclinic.gui.SettingsFrame;
import ua.onclinic.network.clientpackets.LogoutRequest;
import ua.onclinic.utils.DataLoader;

/**
 * Меню настроек
 * 
 * @author Карпенко А. В.
 *
 * @date 7 трав. 2017 р.
 */
public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = 6270154333443074510L;
	
	private final JMenu _authorization = new JMenu(Config.LANGUAGE.getString("menuAuth"));
	
	private final AuthorizationPanel _authPanel = new AuthorizationPanel();
	
	/** Место отображения логина пользователя */
	private final JLabel _user = new JLabel();
	
	public MenuBar()
	{
		final JMenu file = new JMenu(Config.LANGUAGE.getString("menuFile"));
		final JMenuItem settings = new JMenuItem(Config.LANGUAGE.getString("menuSettings"));
		if (DataLoader.SETTINGS_IMAGE != null)
			settings.setIcon(DataLoader.SETTINGS_IMAGE);
		settings.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SettingsFrame.getInstannce().setLocationRelativeTo(MainFrame.getInstance());
				SettingsFrame.getInstannce().showWindow();
			}
		});
		final JMenuItem close = new JMenuItem(Config.LANGUAGE.getString("menuClose"));
		close.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				MainFrame.exit();
			}
		});
		if(DataLoader.EXIT_IMAGE != null)
			close.setIcon(DataLoader.EXIT_IMAGE);
		
		file.add(settings);
		file.addSeparator();
		file.add(close);
		
		final JMenu helpMenu = new JMenu(Config.LANGUAGE.getString("menuHelp"));
		final JMenuItem about = new JMenuItem(Config.LANGUAGE.getString("menuAbout"));
		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Point buttonLoc = new Point();
				// Узнать расположение кнопки относительно экрана (не компонента)
				SwingUtilities.convertPointFromScreen(buttonLoc, helpMenu);
				int x = Math.abs(buttonLoc.x);
				int y = Math.abs(buttonLoc.y) + helpMenu.getHeight();
				
				AboutFrame.getInstance().showWindow(x, y);
			}
		});
		if (DataLoader.INFO_IMAGE != null)
			about.setIcon(DataLoader.INFO_IMAGE);
		
		helpMenu.add(about);
		
		_user.setPreferredSize(new Dimension(256, 21));
		if (DataLoader.USER_IMAGE != null)
			_user.setIcon(DataLoader.USER_IMAGE);
		
		if (DataLoader.LOGIN_IMAGE != null)
			_authorization.setIcon(DataLoader.LOGIN_IMAGE);
		_authorization.add(_authPanel);
		
		final JButton exit = new JButton(Config.LANGUAGE.getString("menuExit"));
		exit.setBorderPainted(false);
		if (DataLoader.LOGOUT_IMAGE != null)
			exit.setIcon(DataLoader.LOGOUT_IMAGE);
		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ServerThread.sendPacket(new LogoutRequest());
			}
			
		});
		
		add(file);
		add(helpMenu);
		add(Box.createHorizontalGlue());
		add(_authorization);
		add(_user);
		add(exit);
	}
	
	/**
	 * Выводит информацию в панели авторизации.<BR><BR>
	 */
	public void setAuthInfo(String text)
	{
		_authPanel.log(text);
	}
	
	/**
	 * Скрывает и очищает поля панели авторизации.<BR><BR>
	 */
	public void hideAuthPanel()
	{
		_authorization.setSelected(false);
		_authorization.setPopupMenuVisible(false);
		_authPanel.refresh();
	}
	
	/**
	 * Отображение логина пользователя.<BR><BR>
	 * @param login
	 */
	public void login(String login)
	{
		_user.setText(login);
	}
	
	/**
	 * Убрать отображение логина пользователя.<BR><BR>
	 */
	public void logout()
	{
		_user.setText(null);
	}
}