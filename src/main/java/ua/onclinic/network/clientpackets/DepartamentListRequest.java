package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

public class DepartamentListRequest extends ClientPacket
{
	@Override
	public int getId()
	{
		return DEPARTAMENTLIST_REQUEST;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		
	}
}