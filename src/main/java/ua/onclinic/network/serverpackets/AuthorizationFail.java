package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;

import ua.onclinic.gui.MainFrame;

public class AuthorizationFail extends ServerPacket
{
	public enum Reason
	{
		USER_NOT_FOUND("Пользователья с таким именем не существует."),
		WRONG_PASSWORD("Неправильный пароль.");
		
		private final String _descr;
		
		Reason(String descr)
		{
			_descr = descr;
		}
		
		public String getDescr()
		{
			return _descr;
		}
	}
	
	private Reason _reason;
	
	@Override
	public int getId()
	{
		return AUTHORIZATION_FAIL;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		_reason = Reason.values()[dis.readInt()];
	}
	
	@Override
	public void runImpl()
	{
		MainFrame.getInstance().setAuthorizationInfo(_reason.getDescr());
	}
}