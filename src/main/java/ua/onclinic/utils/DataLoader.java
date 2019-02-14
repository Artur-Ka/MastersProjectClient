package ua.onclinic.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 15 квіт. 2017 р.
 */
public class DataLoader
{
	private static final String KEY = "et34bac";
	
	public static ImageIcon ICON_IMAGE;
	public static ImageIcon SETTINGS_IMAGE;
	public static ImageIcon EXIT_IMAGE;
	public static ImageIcon INFO_IMAGE;
	public static ImageIcon USER_IMAGE;
	public static ImageIcon LOGIN_IMAGE;
	public static ImageIcon LOGOUT_IMAGE;
	public static ImageIcon REGISTRATION_IMAGE;
	
	public static ImageIcon PLUS_IMAGE;
	public static ImageIcon MINUS_IMAGE;
	public static ImageIcon TRUNC_IMAGE;
	public static ImageIcon APPLY_IMAGE;
	public static ImageIcon UPDATE_IMAGE;
	
	public static ImageIcon FRAME_IMAGE;
	public static ImageIcon DATA_IMAGE;
	
	public static ImageIcon SEARCH_IMAGE;
	
	public static ResourceBundle BUNDLE_UA;
	public static ResourceBundle BUNDLE_RU;
	public static ResourceBundle BUNDLE_EN;
	
	static
	{
		try
		{
			ICON_IMAGE = loadDecodeImage("/img/icon.img");
			SETTINGS_IMAGE = loadDecodeImage("/img/settings.img");
			EXIT_IMAGE = loadDecodeImage("/img/exit.img");
			INFO_IMAGE = loadDecodeImage("/img/info.img");
			USER_IMAGE = loadDecodeImage("/img/user.img");
			LOGIN_IMAGE = loadDecodeImage("/img/login.img");
			LOGOUT_IMAGE = loadDecodeImage("/img/logout.img");;
			REGISTRATION_IMAGE = loadDecodeImage("/img/registration.img");
			
			PLUS_IMAGE = loadDecodeImage("/img/plus.img");
			MINUS_IMAGE = loadDecodeImage("/img/minus.img");
			TRUNC_IMAGE = loadDecodeImage("/img/remove.img");
			APPLY_IMAGE = loadDecodeImage("/img/apply.img");
			UPDATE_IMAGE = loadDecodeImage("/img/update.img");
			
			FRAME_IMAGE = loadDecodeImage("/img/frame.img");
			DATA_IMAGE = loadDecodeImage("/img/data.img");
			
			SEARCH_IMAGE = loadDecodeImage("/img/search.img");
			
			BUNDLE_UA = ResourceBundle.getBundle("localization/res", new Locale("ua"));
			BUNDLE_RU = ResourceBundle.getBundle("localization/res", new Locale("ru"));
			BUNDLE_EN = ResourceBundle.getBundle("localization/res", new Locale("en"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static final ImageIcon loadDecodeImage(String path) throws IOException
	{
		// Достаем файл из jar`а
		final DataInputStream inputStream = new DataInputStream(DataLoader.class.getResourceAsStream(path));
		byte[] imageInByte = new byte[(int) inputStream.available()];
		
		// Записываем байты из файла в массив
		inputStream.readFully(imageInByte);
		inputStream.close();
		
		return new ImageIcon(decode(imageInByte));
	}
	
	/**
	 * Кодирование иконок приложения
	 */
	private static final byte[] encode(byte[] object)
	{
		byte[] key = KEY.getBytes();
		
		byte[] result = new byte[object.length];
		
		for (int i = 0; i < object.length; i++)
		{
			result[i] = (byte) (object[i] ^ key[i % key.length]);
		}
		
		return result;
	}
	
	/**
	 * Декодирование иконок приложения
	 */
	private static final byte[] decode(byte[] secret)
	{
		byte[] key = KEY.getBytes();
		
		byte[] result = new byte[secret.length];
		
		for (int i = 0; i < secret.length; i++)
		{
			result[i] = (byte) (secret[i] ^ key[i % key.length]);
		}
		
		return result;
	}
	
	/**
	 * Кодирование иконок для чтения программы.<BR>
	 */
	public static void main(String[] args)
	{
		File dec = new File("D:/Work/Eclipse workspace/BakalavrProjectClient/src/main/resources/img/data.png");
		File enc = new File("D:/Work/Eclipse workspace/BakalavrProjectClient/src/main/resources/img/data.img");
		
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		
		try
		{
			if (!enc.exists())
				enc.createNewFile();
			
			inputStream = new FileInputStream(dec);
			outputStream = new FileOutputStream(enc);
			
			byte[] origByte = new byte[(int) dec.length()];
			inputStream.read(origByte);
			
			// Записываем закодированный массив бай в новый файл
			outputStream.write(encode(origByte));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}