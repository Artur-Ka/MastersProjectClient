package ua.onclinic.gui.model.west;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EmployeePopupMenu extends JPopupMenu
{
	private static final long serialVersionUID = 8043674915676134579L;
	
	private final JMenuItem _add = new JMenuItem("Додати працівника");
	private final JMenuItem _add1 = new JMenuItem("Додати пацієнта");
	private final JMenuItem _remove = new JMenuItem("Видалити працівника");
	
	public EmployeePopupMenu(WestTree tree)
	{
		_add.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		_remove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		add(_add);
		add(_add1);
		add(_remove);
	}
}