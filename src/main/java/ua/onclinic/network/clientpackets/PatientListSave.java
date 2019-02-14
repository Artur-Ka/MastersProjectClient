package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import ua.onclinic.model.instance.PatientInstance;

public class PatientListSave extends ClientPacket
{
	private final List<PatientInstance> _patients;
	
	public PatientListSave(List<PatientInstance> patients)
	{
		_patients = patients;
	}
	
	@Override
	public int getId()
	{
		return PATIENTLIST_SAVE;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		for (PatientInstance patient : _patients)
		{
			dos.writeInt(patient.getId());
			dos.writeUTF(patient.getName());
			dos.writeUTF(patient.getDateOfBirth());
			dos.writeUTF(patient.getSex());
			dos.writeUTF(patient.getPhone());
			dos.writeUTF(patient.getAddPhone());
			dos.writeUTF(patient.getMail());
			dos.writeUTF(patient.getCountry());
			dos.writeUTF(patient.getCity());
			dos.writeUTF(patient.getAddress());
			dos.writeUTF(patient.getNote());
		}
	}
}