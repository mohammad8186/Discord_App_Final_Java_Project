package sockets.message.request;
/*this class is used to change status of users to ohters
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgSetUserStatus extends MsgBase {
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String username;
	private String status;
	private static Pattern pattern = Pattern.compile("^/status (?<username>\\w+) (?<status>(idle|online|dnd|invis))$", Pattern.CASE_INSENSITIVE);// check later
	
	public MsgSetUserStatus(String username, String status) {
		super(MessageType.REQ_SET_USER_STATUS);
		this.username = username;
		this.status = status;
	}

	public static MsgSetUserStatus fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgSetUserStatus(m.group("username"), m.group("status"));
		}
		return null;
	}
	
}
