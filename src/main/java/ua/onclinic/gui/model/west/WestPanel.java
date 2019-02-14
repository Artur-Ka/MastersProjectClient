package ua.onclinic.gui.model.west;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WestPanel extends JPanel
{
	private static final long serialVersionUID = -7550577503225309233L;
	
	public static final WestTree TREE = new WestTree();
	public static final WestBar BAR = new WestBar();
	
	public WestPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Не активно до авторизации
		setEnabled(false);
		
		add(new JScrollPane(TREE));
		add(BAR);
	}
	
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		TREE.setEnabled(enabled);
		BAR.setEnabled(enabled);
	}
}