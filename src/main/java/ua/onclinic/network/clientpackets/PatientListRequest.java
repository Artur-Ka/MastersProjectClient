package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

public class PatientListRequest extends ClientPacket
{
	@Override
	public int getId()
	{
		return PATIENTLIST_REQUEST;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		
	}
}