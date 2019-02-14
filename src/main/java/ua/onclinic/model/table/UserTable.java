package ua.onclinic.model.table;

import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import ua.onclinic.enums.AccessLevel;
import ua.onclinic.model.AbstractTable;
import ua.onclinic.model.instance.UserInstance;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 12 трав. 2017 р.
 */
public class UserTable extends AbstractTable<UserTableModel, UserInstance>
{
	private static final long serialVersionUID = -4826296085387145871L;

	public UserTable()
	{
		super(new UserTableModel());
		
		final JComboBox<AccessLevel> levels = new JComboBox<>(AccessLevel.values());
		final TableColumn accessColumn = getColumnModel().getColumn(5);
		
		accessColumn.setCellEditor(new DefaultCellEditor(levels));
	}
	
	@Override
	public UserTableModel getModel()
	{
		return (UserTableModel) super.getModel();
	}
	
	@Override
	public void setList(List<UserInstance> users)
	{
		getModel().setInstances(users);
		updateUI();
	}
	
	@Override
	public void addRow()
	{
		final List<UserInstance> patients = getModel().getInstances();
		patients.add(new UserInstance());
		updateUI();
	}

	@Override
	public void removeRow()
	{
		getModel().getInstances().remove(getSelectedRow());
		updateUI();
	}
	
	@Override
	public void clearTable()
	{
		getModel().getInstances().clear();
		updateUI();
	}
}