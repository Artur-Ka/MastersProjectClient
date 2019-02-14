package ua.onclinic.model.table;

import ua.onclinic.Config;
import ua.onclinic.model.AbstractTableModel;
import ua.onclinic.model.instance.PatientInstance;
import ua.onclinic.utils.Utils;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 26 квіт. 2017 р.
 */
public class PatientTableModel extends AbstractTableModel<PatientInstance>
{
	public enum PatientColumn
	{
		no("№", Integer.class),
		id("ID", Integer.class),
		name(Config.LANGUAGE.getString("name"), String.class),
		dob(Config.LANGUAGE.getString("dob"), String.class),
		sex(Config.LANGUAGE.getString("sex"), String.class),
		phone(Config.LANGUAGE.getString("phone"), String.class),
		addPhone(Config.LANGUAGE.getString("addPhone"), String.class),
		mail(Config.LANGUAGE.getString("mail"), String.class),
		country(Config.LANGUAGE.getString("country"), String.class),
		city(Config.LANGUAGE.getString("city"), String.class),
		address(Config.LANGUAGE.getString("address"), String.class),
		doc("Спеціаліст", String.class),
		time("Дата", String.class),
		note(Config.LANGUAGE.getString("note"), String.class);
		
		private final String _name;
		private final Class<?> _type;
		
		PatientColumn(String name, Class<?> type)
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
		return PatientColumn.values().length;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		return PatientColumn.values()[columnIndex].toString();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return PatientColumn.values()[columnIndex]._type;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		if (_instances == null)
			return null;
		
		final PatientInstance patient = (PatientInstance) _instances.get(rowIndex);
		final PatientColumn column = PatientColumn.values()[columnIndex];
		
		switch (column)
		{
			case no: return ++rowIndex;
			case id: return Utils.getFormattedId(patient.getId());
			case name: return patient.getName();
			case dob: return patient.getDateOfBirth();
			case sex: return patient.getSex();
			case phone: return patient.getPhone();
			case addPhone: return patient.getAddPhone();
			case mail: return patient.getMail();
			case country: return patient.getCountry();
			case city: return patient.getCity();
			case address: return patient.getAddress();
			case doc: return "14";
			case time: return "14.12.18 - 12:00";
			case note: return patient.getNote();
			default: return null;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		if (_instances == null)
			return;
		
		final PatientInstance patient = (PatientInstance) _instances.get(rowIndex);
		final PatientColumn column = PatientColumn.values()[columnIndex];
		
		switch (column)
		{
			default:
			case id:
				patient.setId(Integer.parseInt(aValue.toString()));
				break;
			case name:
				patient.setName(aValue.toString());
				break;
			case dob:
				patient.setDateOfBirth(aValue.toString());
				break;
			case sex:
				patient.setSex(aValue.toString());
				break;
			case phone:
				patient.setPhone(aValue.toString());
				break;
			case addPhone:
				patient.setAddPhone(aValue.toString());
				break;
			case mail:
				patient.setMail(aValue.toString());
				break;
			case country:
				patient.setCountry(aValue.toString());
				break;
			case city:
				patient.setCity(aValue.toString());
				break;
			case address:
				patient.setAddress(aValue.toString());
				break;
			case doc:
				break;
			case time:
				break;
			case note:
				patient.setNote(aValue.toString());
				break;
		}
	}
}