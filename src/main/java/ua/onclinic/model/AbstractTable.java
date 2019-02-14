package ua.onclinic.model;

import java.util.List;

import javax.swing.JTable;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 17 черв. 2017 р.
 */
public abstract class AbstractTable<T extends AbstractTableModel<? extends AbstractInstance>, E extends AbstractInstance> extends JTable
{
	private static final long serialVersionUID = -861579187469017119L;
	
	public AbstractTable(T model)
	{
		super(model);
		getColumnModel().getColumn(0).setMaxWidth(20); // уменьшаем поле No
		getColumnModel().getColumn(1).setMaxWidth(80);
	}
	
	public abstract void setList(List<E> instances);
	public abstract void addRow();
	public abstract void removeRow();
	public abstract void clearTable();
}