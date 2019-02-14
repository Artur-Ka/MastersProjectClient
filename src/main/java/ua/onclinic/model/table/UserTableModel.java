package ua.onclinic.model.table;

import ua.onclinic.enums.AccessLevel;
import ua.onclinic.model.AbstractTableModel;
import ua.onclinic.model.instance.UserInstance;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 26 квіт. 2017 р.
 */
public class UserTableModel extends AbstractTableModel<UserInstance>
{
	public enum UserColumn
	{
		no("№", Integer.class),
		id("ID", Integer.class),
		login("login", String.class),
		password("password", String.class),
		name("name", String.class),
		access("access", String.class);
		
		private final String _name;
		private final Class<?> _type;
		
		UserColumn(String name, Class<?> type)
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
		return UserColumn.values().length;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return UserColumn.values()[columnIndex].toString();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return UserColumn.values()[columnIndex]._type;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		if (_instances == null)
			return null;
		
		final UserInstance user = (UserInstance) _instances.get(rowIndex);
		final UserColumn column = UserColumn.values()[columnIndex];
		
		switch (column)
		{
			case no: return ++rowIndex;
			case id: return user.getId();
			case login: return user.getLogin();
			case password: return user.getPassword();
			case name: return user.getName();
			case access: return user.getAccessLevel() != null ? user.getAccessLevel().toString() : null;
			default: return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		if (_instances == null)
			return;
		
		final UserInstance user = (UserInstance) _instances.get(rowIndex);
		final UserColumn column = UserColumn.values()[columnIndex];
		
		switch (column)
		{
			default:
			case id:
				user.setId(Integer.parseInt(aValue.toString()));
				break;
			case login:
				user.setLogin(aValue.toString());
				break;
			case password:
				user.setPassword(aValue.toString());
				break;
			case name:
				user.setName(aValue.toString());
				break;
			case access:
				user.setAccessLevel(AccessLevel.valueOf(aValue.toString()));
				break;
		}
	}
}