package sockets.message.request;
/*this class is used to make a first_step request message to make a frienship
relation between to users 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;
import java.util.regex.Matcher;

public class MsgReplyFriendship extends MsgBase {

	
        private String username;
	private String requester;
	private String status;
	
	 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MsgReplyFriendship(String username, String requester, String status) {
		super(MessageType.REPLY_FRIENDSHIP);
		this.username = username;
		this.requester = requester;
		this.status = status;
		
	}


}

