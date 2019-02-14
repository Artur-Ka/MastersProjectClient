package ua.onclinic.enums;

import java.util.ResourceBundle;

import ua.onclinic.utils.DataLoader;

public enum Language
{
	Ua("Українська", DataLoader.BUNDLE_UA),
	Ru("Русский", DataLoader.BUNDLE_RU),
	En("English", DataLoader.BUNDLE_EN);
	
	private final String _name;
	private final ResourceBundle _bundle;
	
	Language(String name, ResourceBundle bundle)
	{
		_name = name;
		_bundle = bundle;
	}
	
	public String getName()
	{
		return name();
	}
	
	public String getString(String key)
	{
		String text = _bundle.getString(key);
		
		try
		{
			text = new String(text.getBytes("ISO-8859-1"), "UTF-8");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return text;
	}
	
	@Override
	public String toString()
	{
		return _name;
	}
}