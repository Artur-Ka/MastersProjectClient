package ua.onclinic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import ua.onclinic.enums.Language;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 4 трав. 2017 р.
 */
public class Config
{
	public static String SERVER_IP = "localhost";
	public static int SERVER_PORT = 8070;
	public static String CLINIC_NAME = "OnClinic";
	public static Language LANGUAGE = Language.Ua;
	
	private static File CONFIGURATION_FILE;
	public static Properties SETTINGS;
	
	static
	{
		try
		{
			SETTINGS = new Properties();
			CONFIGURATION_FILE = new File("settings.ini");
			
			if (CONFIGURATION_FILE.exists())
			{
				FileInputStream fis = new FileInputStream(CONFIGURATION_FILE);
				SETTINGS.load(fis);
				fis.close();
				
				SERVER_IP = SETTINGS.getProperty("SERVER_IP");
				SERVER_PORT = Integer.parseInt(SETTINGS.getProperty("SERVER_PORT"));
				CLINIC_NAME = SETTINGS.getProperty("CLINIC_NAME");
				LANGUAGE = Language.valueOf(SETTINGS.getProperty("LANG"));
			}
			else
			{
				CONFIGURATION_FILE.createNewFile();
				
				SETTINGS.setProperty("SERVER_IP", SERVER_IP);
				SETTINGS.setProperty("SERVER_PORT", String.valueOf(SERVER_PORT));
				SETTINGS.setProperty("CLINIC_NAME", CLINIC_NAME);
				SETTINGS.setProperty("LANG", LANGUAGE.getName());
				
				FileOutputStream fos = new FileOutputStream(CONFIGURATION_FILE);
				SETTINGS.store(fos, null);
				fos.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static final void setServerIp(String serverIp)
	{
		SERVER_IP = serverIp;
		setProperty("SERVER_IP", serverIp);
	}
	
	public static final void setServerPort(int serverPort)
	{
		SERVER_PORT = serverPort;
		setProperty("SERVER_PORT", String.valueOf(serverPort));
	}
	
	public static final void setClinicName(String clinicName)
	{
		CLINIC_NAME = clinicName;
		setProperty("CLINIC_NAME", CLINIC_NAME);
	}
	
	public static final void setLanguage(Language lang)
	{
		LANGUAGE = lang;
		setProperty("LANG", lang.getName());
	}
	
	private static final void setProperty(String key, String value)
	{
		SETTINGS.setProperty(key, value);
		
		try
		{
			FileOutputStream fos = new FileOutputStream(CONFIGURATION_FILE);
			SETTINGS.store(fos, null);
			fos.close();
		}
		catch (IOException e)
		{
		}
	}
}