package sockets.message.response;
/*this class is used to respond to user requster that 
can watch a has its userfriends list 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.ArrayList;
import sockets.message.MessageType;
import sockets.message.MsgBase;
import sockets.message.model.User;

public class MsgUserFriends extends MsgBase {
	private String username;
	private ArrayList<User> friends;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public MsgUserFriends(String username,  ArrayList<User> friends) {
		super(MessageType.RES_USER_FRIENDS);
		this.username = username;
		this.friends = friends;

	}
	
}
