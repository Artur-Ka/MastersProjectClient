package ua.onclinic.gui.model.cards;

import javax.swing.JPanel;

public class DefaultCard extends JPanel
{
	private static final long serialVersionUID = -574744894077136950L;
	
	private String _name;
	private String _dateOfBirth;
	private String _phone;
	private String _addPhone;
	private String _mail;
	private String _address;
	
	protected DefaultCard(){}
	
	@Override
	public String getName()
	{
		return _name;
	}
	
	public String getDateOfBirth()
	{
		return _dateOfBirth;
	}
	
	public String getPhone()
	{
		return _phone;
	}
	
	public String getAddPhone()
	{
		return _addPhone;
	}
	
	public String getMail()
	{
		return _mail;
	}
	
	public String getAddress()
	{
		return _address;
	}
	
	public void setName(String name)
	{
		_name = name;
	}
	
	public void setDateOfBirth(String dateOfBirth)
	{
		_dateOfBirth = dateOfBirth;
	}
	
	public void setPhone(String phone)
	{
		_phone = phone;
	}
	
	public void setAddPhone(String addPhone)
	{
		_addPhone = addPhone;
	}
	
	public void setMail(String mail)
	{
		_mail = mail;
	}
	
	public void setAddress(String address)
	{
		_address = address;
	}
}