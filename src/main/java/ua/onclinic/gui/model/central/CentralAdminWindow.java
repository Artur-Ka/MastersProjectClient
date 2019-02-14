package ua.onclinic.gui.model.central;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ua.onclinic.gui.MainFrame;
import ua.onclinic.model.table.EmployeeTable;
import ua.onclinic.model.table.PatientTable;
import ua.onclinic.model.table.UserTable;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 13 трав. 2017 р.
 */
public class CentralAdminWindow extends JTabbedPane
{
	private static final long serialVersionUID = 8933583629663815063L;
	
	public static final UserTable USER_TABLE = new UserTable();
	public static final EmployeeTable EMPLOYEE_TABLE = new EmployeeTable();
	public static final PatientTable PATIENT_TABLE = new PatientTable();
	
	public CentralAdminWindow()
	{
		addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if (MainFrame.getInstance() == null)
					return;
				
				MainFrame.getInstance().changeAdminWindowCondition();
			}
		});
		
		add("Користувачі", new JScrollPane(USER_TABLE));
		add("Працівники", new JScrollPane(EMPLOYEE_TABLE));
		add("Пацієнти", new JScrollPane(PATIENT_TABLE));
		add("Відділення", null);
		add("Історія", null);
	}
}