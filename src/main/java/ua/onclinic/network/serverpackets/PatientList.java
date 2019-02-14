package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.onclinic.model.InstancesManager;
import ua.onclinic.model.instance.PatientInstance;

public class PatientList extends ServerPacket
{
	private List<PatientInstance> _patients;
	
	@Override
	public int getId()
	{
		return PATIENT_LIST;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		_patients = new ArrayList<>();
		PatientInstance patient;
		
		while (dis.available() > 0)
		{
			patient = new PatientInstance();
			
			patient.setId(dis.readInt());
			patient.setName(dis.readUTF());
			patient.setDateOfBirth(dis.readUTF());
			patient.setSex(dis.readUTF());
			patient.setPhone(dis.readUTF());
			patient.setAddPhone(dis.readUTF());
			patient.setMail(dis.readUTF());
			patient.setCountry(dis.readUTF());
			patient.setCity(dis.readUTF());
			patient.setAddress(dis.readUTF());
			patient.setNote(dis.readUTF());
			
			_patients.add(patient);
		}
	}
	
	@Override
	public void runImpl()
	{
		InstancesManager.getInstance().setPatientList(_patients);
	}
}