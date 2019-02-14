package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import ua.onclinic.model.instance.EmployeeInstance;

public class EmployeeListSave extends ClientPacket
{
	private final List<EmployeeInstance> _employees;
	
	public EmployeeListSave(List<EmployeeInstance> employees)
	{
		_employees = employees;
	}
	
	@Override
	public int getId()
	{
		return EMPLOYEELIST_SAVE;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		for (EmployeeInstance empl : _employees)
		{
			dos.writeInt(empl.getId());
			dos.writeUTF(empl.getName());
			dos.writeUTF(empl.getDateOfBirth());
			dos.writeUTF(empl.getPhone());
			dos.writeUTF(empl.getAddPhone());
			dos.writeUTF(empl.getMail());
			dos.writeUTF(empl.getAddress());
			dos.writeInt(empl.getEmployeeType().ordinal());
			dos.writeInt(empl.getPost().ordinal());
			dos.writeUTF(empl.getSchedule());
			dos.writeUTF(empl.getNote());
		}
	}
}