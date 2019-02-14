package ua.onclinic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ua.onclinic.gui.MainFrame;
import ua.onclinic.network.clientpackets.ClientPacket;
import ua.onclinic.network.clientpackets.CloseConnection;
import ua.onclinic.network.serverpackets.ServerPacket;

public class ServerThread extends Thread
{
	private static Socket _socket;
	
	
	public ServerThread()
	{
		connect();
		start();
	}
	
	public void connect()
	{
		try
		{
			_socket = new Socket(Config.SERVER_IP, Config.SERVER_PORT);
		}
		catch (IOException ioe)
		{
		}
	}
	
	public void disconnect()
	{
		if (_socket != null && !_socket.isClosed())
		{
			sendPacket(new CloseConnection());
			
			try
			{
				_socket.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
	
	public static void sendPacket(ClientPacket packet)
	{
		if (_socket.isClosed())
			return;
		
		try
		{
			final DataOutputStream dos = new DataOutputStream(_socket.getOutputStream());
			dos.writeInt(packet.getId());
			packet.writeImpl(dos);
			dos.flush();
			
			// Заблокировать главное окно до получения ответа от сервера
			MainFrame.getInstance().lock();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		DataInputStream dis;
		
		while (!isInterrupted())
		{
			if (_socket == null || _socket.isClosed())
			{
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException ie)
				{}
				
				continue;
			}
			
			try
			{
				dis = new DataInputStream(_socket.getInputStream());
				
				if (dis.available() <= 0)
				{
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException ie)
					{}
					
					continue;
				}
				
				readData(dis);
			}
			catch (IOException | InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private static void readData(DataInputStream dis) throws IOException, InstantiationException, IllegalAccessException
	{
		final int packetId = dis.readInt();
		
		final ServerPacket packet = ServerPacket.getPacket(packetId);
		packet.readImpl(dis);
		packet.runImpl();
		
		// Разблокировать главное окно при получении ответа от сервера
		MainFrame.getInstance().unlock();
	}
	
	public static final ServerThread getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static final class SingletonHolder
	{
		private static final ServerThread _instance = new ServerThread();
	}
}