package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;

import ua.onclinic.ServerThread;

public class CloseClient extends ServerPacket
{
	@Override
	public int getId()
	{
		return CLOSE_CLIENT;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		
	}

	@Override
	public void runImpl()
	{
		ServerThread.getInstance().disconnect();
		System.exit(0);
	}
}