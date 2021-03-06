package ua.onclinic.model.instance;

import ua.onclinic.enums.AccessLevel;
import ua.onclinic.model.AbstractInstance;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 26 квіт. 2017 р.
 */
public class UserInstance extends AbstractInstance
{
	private String _login;
	private String _password;
	private AccessLevel _accessLevel;
	
	
	public String getLogin()
	{
		return _login;
	}
	
	public String getPassword()
	{
		return _password;
	}
	
	public AccessLevel getAccessLevel()
	{
		return _accessLevel;
	}
	
	public void setLogin(String login)
	{
		_login = login;
	}
	
	public void setPassword(String password)
	{
		_password = password;
	}
	
	public void setAccessLevel(AccessLevel accessLevel)
	{
		_accessLevel = accessLevel;
	}
}