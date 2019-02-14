package ua.onclinic.network.clientpackets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import ua.onclinic.model.instance.UserInstance;

public class UserListSave extends ClientPacket
{
	private final List<UserInstance> _users;
	
	public UserListSave(List<UserInstance> users)
	{
		_users = users;
	}
	
	@Override
	public int getId()
	{
		return USERLIST_SAVE;
	}
	
	@Override
	public void writeImpl(DataOutputStream dos) throws IOException
	{
		for (UserInstance user : _users)
		{
			dos.writeInt(user.getId());
			dos.writeUTF(user.getLogin());
			dos.writeUTF(user.getPassword());
			dos.writeUTF(user.getName());
			dos.writeInt(user.getAccessLevel().ordinal());
		}
	}
}