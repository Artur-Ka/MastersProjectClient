package ua.onclinic.model;

import java.util.ArrayList;
import java.util.List;

import ua.onclinic.gui.model.central.CentralAdminWindow;
import ua.onclinic.gui.model.west.WestPanel;
import ua.onclinic.model.instance.EmployeeInstance;
import ua.onclinic.model.instance.PatientInstance;
import ua.onclinic.model.instance.UserInstance;

/**
 * 
 * @author A. Karpenko
 * @date 10 дек. 2018 г. 19:20:25
 */
public class InstancesManager
{
	private final List<UserInstance> _users = new ArrayList<>();
	private final List<Departament> _departaments = new ArrayList<>();
	private final List<EmployeeInstance> _employees = new ArrayList<>();
	private final List<PatientInstance> _patients = new ArrayList<>();
	
	
	public List<UserInstance> getUserList()
	{
		return _users;
	}
	
	public List<Departament> getDepartamentList()
	{
		return _departaments;
	}
	
	public List<EmployeeInstance> getEmployeeList()
	{
		return _employees;
	}
	
	public List<PatientInstance> getPatientList()
	{
		return _patients;
	}
	
	public synchronized void setUserList(List<UserInstance> users)
	{
		_users.clear();
		_users.addAll(users);
		CentralAdminWindow.USER_TABLE.setList(_users);
	}
	
	public synchronized void setDepartamentList(List<Departament> departaments)
	{
		_departaments.clear();
		_departaments.addAll(departaments);
		WestPanel.TREE.setDepartaments(departaments);
	}
	
	public synchronized void setEmployeeList(List<EmployeeInstance> employees)
	{
		_employees.clear();
		_employees.addAll(employees);
		CentralAdminWindow.EMPLOYEE_TABLE.setList(_employees);
	}
	
	public synchronized void setPatientList(List<PatientInstance> patients)
	{
		_patients.clear();
		_patients.addAll(patients);
		CentralAdminWindow.PATIENT_TABLE.setList(_patients);
	}
	
	public static final InstancesManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static final class SingletonHolder
	{
		private static final InstancesManager _instance = new InstancesManager();
	}
}