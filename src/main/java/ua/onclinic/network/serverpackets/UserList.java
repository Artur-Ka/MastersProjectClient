package ua.onclinic.network.serverpackets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ua.onclinic.enums.AccessLevel;
import ua.onclinic.model.InstancesManager;
import ua.onclinic.model.instance.UserInstance;

public class UserList extends ServerPacket
{
	private static final Logger _log = Logger.getLogger(UserList.class.getSimpleName());
	
	private List<UserInstance> _users;
	
	@Override
	public int getId()
	{
		return USER_LIST;
	}
	
	@Override
	public void readImpl(DataInputStream dis) throws IOException
	{
		_users = new ArrayList<>();
		UserInstance user;
		
		while (dis.available() > 0)
		{
			user = new UserInstance();
			
			user.setId(dis.readInt());
			user.setLogin(dis.readUTF());
			user.setPassword(dis.readUTF());
			user.setName(dis.readUTF());
			
			int access = dis.readInt();
			if (access >= AccessLevel.values().length)
			{
				access = 0;
				_log.warning("Invalid access level for user " + user.getId() + ". Set access level 0.");
			}
			
			user.setAccessLevel(AccessLevel.values()[access]);
			
			_users.add(user);
		}
	}
	
	@Override
	public void runImpl()
	{
		InstancesManager.getInstance().setUserList(_users);
	}
}