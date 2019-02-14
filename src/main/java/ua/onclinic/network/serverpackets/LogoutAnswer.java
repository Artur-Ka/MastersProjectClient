package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;

import ua.onclinic.gui.MainFrame;

public class LogoutAnswer extends ServerPacket
{
	@Override
	public int getId()
	{
		return LOGOUT_ANSWER;
	}

	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		
	}

	@Override
	public void runImpl()
	{
		MainFrame.getInstance().logout();
	}
}