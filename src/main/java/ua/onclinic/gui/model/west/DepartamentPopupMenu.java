package ua.onclinic.gui.model.west;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ua.onclinic.ServerThread;
import ua.onclinic.gui.model.OptionPane;
import ua.onclinic.network.clientpackets.DepartamentAddRequest;

public class DepartamentPopupMenu extends JPopupMenu
{
	private static final long serialVersionUID = -3279363526748423995L;
	
	private final JMenuItem _add = new JMenuItem("Додати відділення");
	private final JMenuItem _remove = new JMenuItem("Видалити відділення");
	
	private final WestTree _tree;
	
	public DepartamentPopupMenu(WestTree tree)
	{
		_tree = tree;
		
		_add.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final Object name = OptionPane.showInputDialog(this);

				if (name == null)
					return;
				
				ServerThread.sendPacket(new DepartamentAddRequest((String) name));
			}
		});
		
		_remove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_tree.removeDepartament();
			}
		});
		
		add(_add);
		add(_remove);
	}
}