package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

public class UserListRequest extends ClientPacket
{
	@Override
	public int getId()
	{
		return USERLIST_REQUEST;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		
	}
}