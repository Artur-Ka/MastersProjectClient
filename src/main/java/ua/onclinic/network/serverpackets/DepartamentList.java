package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.onclinic.model.Departament;
import ua.onclinic.model.InstancesManager;
import ua.onclinic.model.instance.EmployeeInstance;

/**
 * 
 * @author A. Karpenko
 * @date 15 дек. 2018 г. 18:09:31
 */
public class DepartamentList extends ServerPacket
{
	private List<Departament> _departaments;
	
	@Override
	public int getId()
	{
		return DEPARTAMENT_LIST;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		_departaments = new ArrayList<>();
		Departament dep;
		
		while (dis.available() > 0)
		{
			dep = new Departament();
			
			dep.setId(dis.readInt());
			dep.setName(dis.readUTF());
			
			final int count = dis.readInt();
			EmployeeInstance empl;
			for (int i = 0; i < count; i++)
			{
				empl = new EmployeeInstance();
				empl.setId(dis.readInt());
				empl.setName(dis.readUTF());
				dep.addEmployee(empl);
			}
			
			_departaments.add(dep);
		}
	}
	
	@Override
	public void runImpl()
	{
		InstancesManager.getInstance().setDepartamentList(_departaments);
	}
}