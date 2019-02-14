package ua.onclinic.gui.model;

import javax.swing.JToolBar;

/**
 * Правая панель
 * 
 * @author Карпенко А. В.
 *
 * @date 7 трав. 2017 р.
 */
public class EastBar extends JToolBar
{
	private static final long serialVersionUID = -2915534072725282732L;
	
	public EastBar()
	{
		super(JToolBar.VERTICAL);
		setFloatable(false);
	}
}