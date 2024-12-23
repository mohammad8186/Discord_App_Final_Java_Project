package sockets.message.response;
/*this class is used to respond to user requster that 
can  has a watch a list of a channel or server
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.ArrayList;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgUsers extends MsgBase {
	private String servername;
	private String channelname;
	private ArrayList<String> users;
	
	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public ArrayList<String> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	public MsgUsers(String servername, String channelname, ArrayList<String> userList) {
		super(MessageType.RES_GET_USERS);
		this.servername = servername;
		this.channelname = channelname;
		this.users = userList;	

	}
	
}
