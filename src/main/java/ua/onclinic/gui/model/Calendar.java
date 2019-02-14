package ua.onclinic.gui.model;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 13 трав. 2017 р.
 */
public class Calendar extends JPanel
{
	private static final long serialVersionUID = -3900880719341301705L;
	
	public Calendar()
	{
		setPreferredSize(new Dimension(200, 400));
		setLayout(new FlowLayout());
		JPanel mounth = new JPanel();
		mounth.add(new JButton("<"));
		mounth.add(new JLabel("May"));
		mounth.add(new JButton(">"));
		
		JPanel days = new JPanel();
		
		for (int i = 0; i < 31; i++)
		{
			days.add(new JButton(String.valueOf(i)));
		}
		
		add(mounth);
		add(days);
	}
}