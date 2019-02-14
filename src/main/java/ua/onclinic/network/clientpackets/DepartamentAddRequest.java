package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 
 * @author A. Karpenko
 * @date 16 дек. 2018 г. 13:32:56
 */
public class DepartamentAddRequest extends ClientPacket
{
	private final String _name;
	
	public DepartamentAddRequest(String name)
	{
		_name = name;
	}
	
	@Override
	public int getId()
	{
		return DEPARTAMENT_ADD_REQUEST;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(_name);
	}
}
