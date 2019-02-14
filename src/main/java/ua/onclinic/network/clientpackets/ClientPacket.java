package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 11 квіт. 2017 р.
 */
public abstract class ClientPacket
{
	protected static final int AUTHORIZATION_REQUEST = 0x01;
	protected static final int LOGOUT_REQUEST = 0x04;
	protected static final int CLOSE_CONNECTION = 0x06;
	
	protected static final int USERLIST_REQUEST = 0x0a;
	protected static final int USERLIST_SAVE = 0x0c;
	protected static final int DEPARTAMENT_ADD_REQUEST = 0x0d;
	protected static final int DEPARTAMENT_REMOVE_REQUEST = 0x0e;
	protected static final int DEPARTAMENTLIST_REQUEST = 0x0f;
	protected static final int DEPARTAMENTLIST_SAVE = 0x11;
	protected static final int EMPLOYEE_ADD_REQUEST = 0x12;
	protected static final int EMPLOYEE_REMOVE_REQUEST = 0x13;
	protected static final int EMPLOYEELIST_REQUEST = 0x014;
	protected static final int EMPLOYEELIST_SAVE = 0x16;
	protected static final int PATIENT_ADD_REQUEST = 0x17;
	protected static final int PATIENT_REMOVE_REQUEST = 0x18;
	protected static final int PATIENTLIST_REQUEST = 0x19;
	protected static final int PATIENTLIST_SAVE = 0x1b;
	
	public abstract int getId();
	
	public abstract void writeImpl(DataOutputStream dos) throws IOException;
}