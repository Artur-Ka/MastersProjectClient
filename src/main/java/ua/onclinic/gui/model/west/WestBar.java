package ua.onclinic.gui.model.west;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.onclinic.utils.DataLoader;

/**
* @author Карпенко А. В.
 *
 * @date 7 трав. 2017 р.
 */
public class WestBar extends JPanel
{
	private static final long serialVersionUID = 6678792975702680572L;
	
	private final JTextField _searchField = new JTextField();
	private final JButton _searchButton = new JButton();
	
	
	public WestBar()
	{
		_searchField.setPreferredSize(new Dimension(120, 22));
		_searchButton.setPreferredSize(new Dimension(20, 20));
		_searchButton.setIcon(DataLoader.SEARCH_IMAGE);
		_searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		add(_searchField);
		add(_searchButton);
	}
	
	
	@Override
	public void setEnabled(boolean enabled)
	{
		_searchField.setEnabled(enabled);
		_searchButton.setEnabled(enabled);
	}
}