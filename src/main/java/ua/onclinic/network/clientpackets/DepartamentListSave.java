package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import ua.onclinic.model.Departament;
import ua.onclinic.model.instance.EmployeeInstance;

/**
 * 
 * @author A. Karpenko
 * @date 15 дек. 2018 г. 18:09:21
 */
public class DepartamentListSave extends ClientPacket
{
	private final List<Departament> _departaments;
	
	public DepartamentListSave(List<Departament> departaments)
	{
		_departaments = departaments;
	}
	
	@Override
	public int getId()
	{
		return DEPARTAMENTLIST_SAVE;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		for (Departament dep : _departaments)
		{
			dos.writeInt(dep.getId());
			dos.writeUTF(dep.getName());
			
			final List<EmployeeInstance> emps = dep.getEmployees();
			dos.writeInt(emps.size());
			
			for (EmployeeInstance emp : emps)
			{
				dos.writeInt(emp.getId());
				dos.writeUTF(emp.getName());
			}
		}
	}
}