package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

public class CloseConnection extends ClientPacket
{
	@Override
	public int getId()
	{
		return CLOSE_CONNECTION;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		
	}
}