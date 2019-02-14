package ua.onclinic.gui.model.west;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import ua.onclinic.model.Departament;

/**
 * 
 * @author A. Karpenko
 * @date 10 дек. 2018 г. 19:20:08
 */
public class WestTree extends JTree
{
	private static final long serialVersionUID = -6972737619623228198L;
	
	private final CopyOnWriteArrayList<Departament> _departaments = new CopyOnWriteArrayList<>();
	
	private final DepartamentPopupMenu _depMenu;
	private final EmployeePopupMenu _emplMenu;
	
	
	public WestTree()
	{
		super(new WestTreeModel());
		
		_depMenu = new DepartamentPopupMenu(this);
		_emplMenu = new EmployeePopupMenu(this);
		
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				checkPopup(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				checkPopup(e);
			}
		});
	}
	
	@Override
	public WestTreeModel getModel()
	{
		return (WestTreeModel) super.getModel();
	}
	
	public void setDepartaments(List<Departament> departaments)
	{
		getModel().setDepartaments(departaments);
		updateUI();
	}
	
	public CopyOnWriteArrayList<Departament> getDepartaments()
	{
		return _departaments;
	}
	
	public Departament getDepartament(int index)
	{
		return _departaments.get(index);
	}
	
	public void addDepartament(String name)
	{
		//TODO: запрос на сервер. Генерация ID
		updateUI();
	}
	
	public void removeDepartament()
	{
		_departaments.remove(getSelectionRows()[0]);
		updateUI();
	}
	
	private void checkPopup(MouseEvent e)
	{
		if (!isEnabled() || e.getButton() != MouseEvent.BUTTON3)
			return;
		
		final TreePath treePath = getClosestPathForLocation(e.getX(), e.getY());
		
		if (e.isPopupTrigger())
		{
			if (!isPathSelected(treePath))
				setSelectionPath(treePath);
			
			if (treePath.getLastPathComponent() == getModel().getRoot())
				_depMenu.show(e.getComponent(), e.getX(), e.getY());
			else
				_emplMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}
}