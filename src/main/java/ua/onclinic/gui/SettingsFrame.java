package ua.onclinic.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.onclinic.Config;
import ua.onclinic.ServerThread;
import ua.onclinic.enums.Language;
import ua.onclinic.utils.RestartApplication;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 16 квіт. 2017 р.
 */
public class SettingsFrame extends JDialog
{
	private static final long serialVersionUID = -1626122562353351555L;
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 180;
	
	private final JTextField _ip = new JTextField();
	private final JTextField _port = new JTextField();
	private final JTextField _name = new JTextField();
	private final JComboBox<Language> _language = new JComboBox<>(Language.values());
	
	private final JLabel _log = new JLabel();
	
	private final JButton _ok = new JButton(Config.LANGUAGE.getString("ok"));
	private final JButton _cancel = new JButton(Config.LANGUAGE.getString("cancel"));
	
	private SettingsFrame()
	{
		setTitle(Config.LANGUAGE.getString("menuSettings"));
		setSize(WIDTH, HEIGHT);
		setModal(true);
		setResizable(false);
		
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new GridLayout(4, 2));
		Dimension fieldsSize = new Dimension(200, 20);
		
		_ip.setPreferredSize(fieldsSize);
		_port.setPreferredSize(fieldsSize);
		_name.setPreferredSize(fieldsSize);
		_language.setPreferredSize(fieldsSize);
		
		_language.setSelectedItem(Config.LANGUAGE);
		
		fieldsPanel.add(new JLabel(Config.LANGUAGE.getString("menuHost")));
		fieldsPanel.add(_ip);
		fieldsPanel.add(new JLabel(Config.LANGUAGE.getString("menuPort")));
		fieldsPanel.add(_port);
		fieldsPanel.add(new JLabel(Config.LANGUAGE.getString("menuName")));
		fieldsPanel.add(_name);
		fieldsPanel.add(new JLabel(Config.LANGUAGE.getString("menuLang")));
		fieldsPanel.add(_language);
		
		_log.setForeground(Color.RED);
		
		final JPanel buttonsPanel = new JPanel();
		final Dimension buttonsSize = new Dimension(80, 26);
		
		_ok.setPreferredSize(buttonsSize);
		_ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final String serverIp = _ip.getText().trim();
				if (!checkIp(serverIp))
				{
					_log.setText(Config.LANGUAGE.getString("menuErr1"));
					return;
				}
				
				final int serverPort = Integer.parseInt(_port.getText().trim());
				if (!checkPort(serverPort))
				{
					_log.setText(Config.LANGUAGE.getString("menuErr2"));
					return;
				}
				
				setVisible(false);
				
				Config.setServerIp(serverIp);
				Config.setServerPort(serverPort);
				Config.setClinicName(_name.getText().trim());
				
				final Language curLang = Config.LANGUAGE;
				final Language selLang = (Language) _language.getSelectedItem();
				
				if (selLang != curLang)
				{
					Config.setLanguage((Language) _language.getSelectedItem());
					
					final Object[] options = {Config.LANGUAGE.getString("ok"), Config.LANGUAGE.getString("cancel")};
					final int n = JOptionPane.showOptionDialog(MainFrame.getInstance(), Config.LANGUAGE.getString("setText1"),
							Config.LANGUAGE.getString("setText2"), JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					
					if (n == 0)
					{
						ServerThread.getInstance().disconnect();
						
						try
						{
							RestartApplication.restart(new Runnable()
							{
								@Override
								public void run(){}
							});
						}
						catch (IOException ioe)
						{
							ioe.printStackTrace();
						}
					}
				}
			}
		});
		
		_cancel.setPreferredSize(buttonsSize);
		_cancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				_log.setText(null);
			}
		});
		
		buttonsPanel.add(_ok);
		buttonsPanel.add(_cancel);
		
		add(fieldsPanel, BorderLayout.NORTH);
		add(_log, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void showWindow()
	{
		_ip.setText(Config.SERVER_IP);
		_port.setText(String.valueOf(Config.SERVER_PORT));
		_name.setText(Config.CLINIC_NAME);
		setVisible(true);
	}
	
	private static boolean checkIp(String ip)
	{
		if (ip.equals("localhost"))
			return true;
		
		final String[] subs = ip.split("\\.");
		
		if (subs.length != 4)
			return false;
		
		for (String s : subs)
		{
			int val = Integer.parseInt(s);
			
			// Значения в IP не могут быть отрицательными и превышать 1 байт
			if (val < 0 || val > 255)
				return false;
		}
		
		return true;
	}
	
	private static boolean checkPort(int port)
	{
		return port > 0 && port < 10000;
	}
	
	public static final SettingsFrame getInstannce()
	{
		return SingletonHolder._instance;
	}
	
	private static final class SingletonHolder
	{
		private static final SettingsFrame _instance = new SettingsFrame();
	}
}