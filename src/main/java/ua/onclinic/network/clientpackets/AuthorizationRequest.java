package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 
 * @author A. Karpenko
 * @date 20 окт. 2018 г. 19:20:48
 */
public class AuthorizationRequest extends ClientPacket
{
	private String _login;
	private char[] _password;
	
	public AuthorizationRequest(String login, char[] password)
	{
		_login = login;
		_password = password;
	}
	
	@Override
	public int getId()
	{
		return AUTHORIZATION_REQUEST;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(_login);
		
		for (int i = 0; i < _password.length; i++)
		{
			dos.writeChar(_password[i]);
		}
		
		// Нулим переменные, чтобы не хранились в памяти
		_login = null;
		_password = null;
	}
}