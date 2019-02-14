package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

public class LogoutRequest extends ClientPacket
{
	@Override
	public int getId()
	{
		return LOGOUT_REQUEST;
	}

	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		
	}
}