package ua.onclinic.model.table;

import ua.onclinic.Config;
import ua.onclinic.enums.EmployeeType;
import ua.onclinic.enums.Post;
import ua.onclinic.model.AbstractTableModel;
import ua.onclinic.model.instance.EmployeeInstance;
import ua.onclinic.utils.Utils;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 26 квіт. 2017 р.
 */
public class EmployeeTableModel extends AbstractTableModel<EmployeeInstance>
{
	private enum EmployeeColumn
	{
		no("№", Integer.class),
		id("ID", Integer.class),
		name(Config.LANGUAGE.getString("name"), String.class),
		dob(Config.LANGUAGE.getString("dob"), String.class),
		phone(Config.LANGUAGE.getString("phone"), String.class),
		addPhone(Config.LANGUAGE.getString("addPhone"), String.class),
		mail(Config.LANGUAGE.getString("mail"), String.class),
		address(Config.LANGUAGE.getString("address"), String.class),
		employee_type(Config.LANGUAGE.getString("employeeType"), String.class),
		post(Config.LANGUAGE.getString("post"), String.class),
		schedule(Config.LANGUAGE.getString("schedule"), String.class),
		note(Config.LANGUAGE.getString("note"), String.class);
		
		private final String _name;
		private final Class<?> _type;
		
		EmployeeColumn(String name, Class<?> type)
		{
			_name = name;
			_type = type;
		}
		
		@Override
		public String toString()
		{
			return _name;
		}
	}
	
	@Override
	public int getColumnCount()
	{
		return EmployeeColumn.values().length;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return EmployeeColumn.values()[columnIndex].toString();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return EmployeeColumn.values()[columnIndex]._type;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		if (_instances == null)
			return null;
		
		final EmployeeInstance empl = (EmployeeInstance) _instances.get(rowIndex);
		final EmployeeColumn column = EmployeeColumn.values()[columnIndex];
		
		switch (column)
		{
			case no: return ++rowIndex;
			case id: return Utils.getFormattedId(empl.getId());
			case name: return empl.getName();
			case dob: return empl.getDateOfBirth();
			case phone: return empl.getPhone();
			case addPhone: return empl.getAddPhone();
			case mail: return empl.getMail();
			case address: return empl.getAddress();
			case employee_type: return empl.getEmployeeType() != null ? empl.getEmployeeType().toString() : null;
			case post: return empl.getPost() != null ? empl.getPost().toString() : null;
			case schedule: return empl.getSchedule();
			case note: return empl.getNote();
			default: return null;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		if (_instances == null)
			return;
		
		final EmployeeInstance empl = (EmployeeInstance) _instances.get(rowIndex);
		final EmployeeColumn column = EmployeeColumn.values()[columnIndex];
		
		switch (column)
		{
			default:
			case id:
				empl.setId(Integer.parseInt(aValue.toString()));
				break;
			case name:
				empl.setName(aValue.toString());
				break;
			case dob:
				empl.setDateOfBirth(aValue.toString());
				break;
			case phone:
				empl.setPhone(aValue.toString());
				break;
			case addPhone:
				empl.setAddPhone(aValue.toString());
				break;
			case mail:
				empl.setMail(aValue.toString());
				break;
			case address:
				empl.setAddress(aValue.toString());
				break;
			case employee_type:
				empl.setEmployeeType(EmployeeType.valueOf(aValue.toString()));
				break;
			case post:
				empl.setPost(Post.valueOf(aValue.toString()));
				break;
			case schedule:
				empl.setSchedule(aValue.toString());
				break;
			case note:
				empl.setNote(aValue.toString());
				break;
		}
	}
}