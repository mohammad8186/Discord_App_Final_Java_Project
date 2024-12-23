package sockets.message.request;
/*this class is used to make a second_step respond message to make a frienship
relation between to users 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;
import java.util.regex.Matcher;

public class MsgRequestFriendship extends MsgBase {

	
  private  String username;
          private  String friendname;
	
	 
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFriendname() {
		return friendname;
	}


	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}


	public  MsgRequestFriendship( String username , String friendname ) {
		super(MessageType.REQUEST_FRIENDSHIP);
		this.username = username;
		this.friendname = friendname;
		
	}



}

