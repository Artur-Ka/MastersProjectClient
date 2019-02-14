package ua.onclinic.utils;

public class Utils
{
	private static final int PATIENT_ID_LENGTH = 10;
	
	/**
	 * Возвращает номер карты пациента, приведенный к заданной длинне.<BR><BR>
	 * @param id
	 * @return
	 */
	public static String getFormattedId(int id)
	{
		String sid = String.valueOf(id);
		
		if (sid.length() == PATIENT_ID_LENGTH)
			return sid;
		
		while (sid.length() < PATIENT_ID_LENGTH)
		{
			sid = "0".concat(sid);
		}
		
		return sid;
	}
}