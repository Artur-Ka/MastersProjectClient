package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;

public class ActionFailed extends ServerPacket
{
	@Override
	public int getId()
	{
		return ACTION_FAILED;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		
	}
	
	@Override
	public void runImpl()
	{
		
	}
}