package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;

import ua.onclinic.ServerThread;
import ua.onclinic.enums.AccessLevel;
import ua.onclinic.gui.MainFrame;
import ua.onclinic.network.clientpackets.DepartamentListRequest;

public class AuthorizationOk extends ServerPacket
{
	private String _login;
	private AccessLevel _accessLevel;
	
	@Override
	public int getId()
	{
		return AUTHORIZATION_OK;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		_login = dis.readUTF();
		_accessLevel = AccessLevel.values()[dis.readInt()];
	}
	
	@Override
	public void runImpl()
	{
		ServerThread.sendPacket(new DepartamentListRequest());
		MainFrame.getInstance().authorize(_login, _accessLevel);
	}
}