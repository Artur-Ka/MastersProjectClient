package ua.onclinic.model.table;

import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import ua.onclinic.enums.EmployeeType;
import ua.onclinic.enums.Post;
import ua.onclinic.model.AbstractTable;
import ua.onclinic.model.instance.EmployeeInstance;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 12 трав. 2017 р.
 */
public class EmployeeTable extends AbstractTable<EmployeeTableModel, EmployeeInstance>
{
	private static final long serialVersionUID = 8393789375915052717L;
	
	public EmployeeTable()
	{
		super(new EmployeeTableModel());
		
		final JComboBox<EmployeeType> types = new JComboBox<>(EmployeeType.values());
		final JComboBox<Post> posts = new JComboBox<>(Post.values());
		
		final TableColumn typeColumn = getColumnModel().getColumn(8);
		final TableColumn postColumn = getColumnModel().getColumn(9);
		
		typeColumn.setCellEditor(new DefaultCellEditor(types));
		postColumn.setCellEditor(new DefaultCellEditor(posts));
	}
	
	@Override
	public EmployeeTableModel getModel()
	{
		return (EmployeeTableModel) super.getModel();
	}
	
	@Override
	public void setList(List<EmployeeInstance> doctors)
	{
		getModel().setInstances(doctors);
		updateUI();
	}

	@Override
	public void addRow()
	{
		final List<EmployeeInstance> doctors = getModel().getInstances();
		doctors.add(new EmployeeInstance()); // +1 потому, что порядковый номер начинается с единицы
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