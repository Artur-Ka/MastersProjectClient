package ua.onclinic.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import ua.onclinic.enums.AccessLevel;
import ua.onclinic.gui.MainFrame;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 17 черв. 2017 р.
 */
public abstract class AbstractTableModel<T extends AbstractInstance> implements TableModel
{
	private final Set<TableModelListener> _listeners = new HashSet<>();
	protected List<T> _instances;
	
	public List<T> getInstances()
	{
		return _instances;
	}
	
	public void setInstances(List<T> instances)
	{
		_instances = instances;
	}
	
	@Override
	public int getRowCount()
	{
		return _instances != null ? _instances.size() : 0;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		if (columnIndex < 2)
			return false;
		
		if (MainFrame.getInstance().getAccessLevel() != AccessLevel.ADMIN)
			return false;
		
		return true;
	}
	
	@Override
	public void addTableModelListener(TableModelListener l)
	{
		_listeners.add(l);
	}
	
	@Override
	public void removeTableModelListener(TableModelListener l)
	{
		_listeners.remove(l);
	}
}