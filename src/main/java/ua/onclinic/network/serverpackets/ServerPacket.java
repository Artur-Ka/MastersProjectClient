package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 11 квіт. 2017 р.
 */
public abstract class ServerPacket
{
	private static final Map<Integer, Class<? extends ServerPacket>> _packets = new HashMap<>();
	
	protected static final int AUTHORIZATION_OK = 0x02;
	protected static final int AUTHORIZATION_FAIL = 0x03;
	protected static final int LOGOUT_ANSWER = 0x05;
	protected static final int ACTION_FAILED = 0x07;
	protected static final int CLOSE_CLIENT = 0x08;
	
	protected static final int USER_LIST = 0x0b;
	protected static final int DEPARTAMENT_LIST = 0x10;
	protected static final int DOCTOR_LIST = 0x15;
	protected static final int PATIENT_LIST = 0x1a;
	
	static
	{
		_packets.put(AUTHORIZATION_OK, AuthorizationOk.class);
		_packets.put(AUTHORIZATION_FAIL, AuthorizationFail.class);
		_packets.put(LOGOUT_ANSWER, LogoutAnswer.class);
		_packets.put(ACTION_FAILED, ActionFailed.class);
		_packets.put(CLOSE_CLIENT, CloseClient.class);
		_packets.put(USER_LIST, UserList.class);
		_packets.put(DEPARTAMENT_LIST, DepartamentList.class);
		_packets.put(DOCTOR_LIST, EmployeeList.class);
		_packets.put(PATIENT_LIST, PatientList.class);
	}
	
	public static ServerPacket getPacket(int id) throws InstantiationException, IllegalAccessException
	{
		return _packets.get(id).newInstance();
	}
	
	public abstract int getId();
	
	public abstract void readImpl(DataInputStream dis) throws IOException;
	
	public abstract void runImpl();
}