package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.onclinic.enums.EmployeeType;
import ua.onclinic.enums.Post;
import ua.onclinic.model.InstancesManager;
import ua.onclinic.model.instance.EmployeeInstance;

public class EmployeeList extends ServerPacket
{
	private List<EmployeeInstance> _employees;
	
	@Override
	public int getId()
	{
		return DOCTOR_LIST;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		_employees = new ArrayList<>();
		EmployeeInstance empl;
		
		while (dis.available() > 0)
		{
			empl = new EmployeeInstance();
			
			empl.setId(dis.readInt());
			empl.setName(dis.readUTF());
			empl.setDateOfBirth(dis.readUTF());
			empl.setPhone(dis.readUTF());
			empl.setAddPhone(dis.readUTF());
			empl.setMail(dis.readUTF());
			empl.setAddress(dis.readUTF());
			empl.setEmployeeType(EmployeeType.values()[dis.readInt()]);
			empl.setPost(Post.values()[dis.readInt()]);
			empl.setSchedule(dis.readUTF());
			empl.setNote(dis.readUTF());
			
			_employees.add(empl);
		}
	}
	
	@Override
	public void runImpl()
	{
		InstancesManager.getInstance().setEmployeeList(_employees);
	}
}