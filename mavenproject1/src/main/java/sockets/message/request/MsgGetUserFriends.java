package sockets.message.request;
/*this class gets friendusers from list of user friendlist to be showed
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;

import java.util.regex.Matcher;

public class MsgGetUserFriends extends MsgBase {

  private String username;
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	private static Pattern pattern = Pattern.compile("^/get_user_friends (?<username>\\w+)?$", Pattern.CASE_INSENSITIVE);
	public MsgGetUserFriends(String username) {
		super(MessageType.REQ_GET_USER_FRIENDS);
    this.username = username;
		
	}


	public static MsgGetUserFriends fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgGetUserFriends(m.group("username"));
			
		}
		return null;
	}

	
}

