package ua.onclinic.gui.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ua.onclinic.Config;
import ua.onclinic.ServerThread;
import ua.onclinic.network.clientpackets.AuthorizationRequest;

/**
 * Панель авторизации
 * 
 * @author Карпенко А. В.
 *
 * @date 14 трав. 2017 р.
 */
public class AuthorizationPanel extends JPanel
{
	private static final long serialVersionUID = 2346862838774666935L;
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 120;
	
	public static final int MIN_LOGIN_LENGTH = 4;
	public static final int MIN_PASS_LENGTH = 4;
	
	private final JTextField _login = new JTextField();
	private final JPasswordField _password = new JPasswordField();
	
	private final JLabel _log = new JLabel();
	
	private final JButton _submit = new JButton(Config.LANGUAGE.getString("authEnter"));
	
	public AuthorizationPanel()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		
		final JPanel fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new GridLayout(2, 2));
		final Dimension fieldsSize = new Dimension(200, 20);
		
		_login.setPreferredSize(fieldsSize);
		_password.setPreferredSize(fieldsSize);
		_password.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e){}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					submit();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e){}
		});
		
		fieldsPanel.add(new JLabel(Config.LANGUAGE.getString("authLogin")));
		fieldsPanel.add(_login);
		fieldsPanel.add(new JLabel(Config.LANGUAGE.getString("authPass")));
		fieldsPanel.add(_password);
		
		_log.setForeground(Color.RED);
		
		final Dimension buttonsSize = new Dimension(80, 26);
		
		_submit.setPreferredSize(buttonsSize);
		_submit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				submit();
			}
		});
		
		add(fieldsPanel, BorderLayout.NORTH);
		add(_log, BorderLayout.CENTER);
		add(_submit, BorderLayout.SOUTH);
	}
	
	public void refresh()
	{
		_login.setText(null);
		_password.setText(null);
		_log.setText(null);
	}
	
	public void log(String text)
	{
		_log.setText(text);
	}
	
	private final void submit()
	{
		final String login = _login.getText();
		
		if (login.length() < MIN_LOGIN_LENGTH)
		{
			final String text = Config.LANGUAGE.getString("authErr1");
			_log.setText(text.replaceFirst("%n%", String.valueOf(MIN_LOGIN_LENGTH)));
			return;
		}
		
		char[] pass = _password.getPassword();
		
		if (pass.length < MIN_PASS_LENGTH)
		{
			final String text = Config.LANGUAGE.getString("authErr2");
			_log.setText(text.replaceFirst("%n%", String.valueOf(MIN_PASS_LENGTH)));
			return;
		}
		
		if (_log.getText() != null)
			_log.setText(null);
		
		// Запрос авторизации у сервера
		ServerThread.sendPacket(new AuthorizationRequest(login, pass));
		
		// Зануливаем массив с паролем, чтобы не оставался в памяти
		pass = null;
	}
}