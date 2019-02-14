package ua.onclinic.model.table;

import java.util.List;

import ua.onclinic.model.AbstractTable;
import ua.onclinic.model.instance.PatientInstance;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 12 трав. 2017 р.
 */
public class PatientTable extends AbstractTable<PatientTableModel, PatientInstance>
{
	private static final long serialVersionUID = -4826296085387145871L;

	public PatientTable()
	{
		super(new PatientTableModel());
	}
	
	@Override
	public PatientTableModel getModel()
	{
		return (PatientTableModel) super.getModel();
	}
	
	@Override
	public void setList(List<PatientInstance> patients)
	{
		getModel().setInstances(patients);
		updateUI();
	}
	
	@Override
	public void addRow()
	{
		final List<PatientInstance> patients = getModel().getInstances();
		patients.add(new PatientInstance());
		updateUI();
	}

	@Override
	public void removeRow()
	{
		getModel().getInstances().remove(getSelectedRow());
		updateUI();
	}
	
	@Override
	public void clearTable()
	{
		getModel().getInstances().clear();
		updateUI();
	}
}