package ua.onclinic.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

import ua.onclinic.Config;
import ua.onclinic.utils.DataLoader;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 16 квіт. 2017 р.
 */
public class AboutFrame extends JDialog
{
	private static final long serialVersionUID = -1107982887140130557L;
	
	public static final int WIDTH = 360;
	public static final int HEIGHT = 240;
	
	public static final String CONTACT_MAIL = "artur.karpenko94@gmail.com";
	public static final String WEBSITE = "http://localhost:8080";
	public static final String AUTHOR_INFO = "<html><body>"
			+ "<table width=" + WIDTH + ">"
			+ "<tr>"
			+ "<td>"+Config.LANGUAGE.getString("abLicense")+"</td>"
			+ "<td>TrialWare License</td>"
			+ "</tr><tr>"
			+ "<td>"+Config.LANGUAGE.getString("abAuthor1")+"</td>"
			+ "<td>"+Config.LANGUAGE.getString("abAuthor2")+"</td>"
			+ "</tr><tr>"
			+ "<td>"+Config.LANGUAGE.getString("abService")+"</td>"
			+ "<td>" + CONTACT_MAIL + "</a></td>"
			+ "</tr><tr>"
			+ "<td>"+Config.LANGUAGE.getString("abSite")+"</td>"
			+ "<td><a href=\"\">" + WEBSITE + "</a></td>"
			+ "</tr>"
			+ "</table>"
			+ "</body></html>";
	
	private AboutFrame()
	{
		setSize(WIDTH, HEIGHT);
		setModal(true);
		setResizable(false);
		setTitle(Config.LANGUAGE.getString("menuAbout"));
		setLayout(new FlowLayout());
		setIconImage(DataLoader.ICON_IMAGE.getImage());
		
		// Панель с информацией об авторе
		//==================================================================
		JTextPane infoPane = new JTextPane();
		infoPane.setEditable(false);
		infoPane.setBackground(new Color(238, 238, 238));
		infoPane.setContentType("text/html");
		infoPane.setBorder(BorderFactory.createEtchedBorder());
		infoPane.setText(AUTHOR_INFO);
		infoPane.addHyperlinkListener(new HyperlinkListener()
		{
			@Override
			public void hyperlinkUpdate(HyperlinkEvent arg0)
			{
				if (arg0.getEventType() == EventType.ACTIVATED)
				{
					if (Desktop.isDesktopSupported())
					{
						try
						{
							Desktop.getDesktop().browse(new URI(WEBSITE));
						}
						catch (IOException | URISyntaxException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		});
		//==================================================================
		
		// Текстовая панель
		//==================================================================
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(Config.LANGUAGE.getString("abText"));
		final JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(WIDTH - 10, HEIGHT - infoPane.getHeight() - 155));
		//==================================================================
		
		add(infoPane);
		add(scrollPane);
	}
	
	public void showWindow(int x, int y)
	{
		setLocation(x, y);
		setVisible(true);
	}
	
	public static final AboutFrame getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static final class SingletonHolder
	{
		private static final AboutFrame _instance = new AboutFrame();
	}
}