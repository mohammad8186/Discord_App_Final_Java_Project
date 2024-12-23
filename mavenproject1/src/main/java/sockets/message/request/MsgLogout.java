package sockets.message.request;
/*this class is used to be loggedout from account
 *after all , the user has to logout to end commands
 *the loggout step is taken finally
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgLogout extends MsgBase {
	private String username;
	private	static Pattern pattern = Pattern.compile("^/logout (?<username>\\w+)$", Pattern.CASE_INSENSITIVE);
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MsgLogout(String username) {
		super(MessageType.REQ_LOGIN);
		this.username = username;
	}

	public static MsgLogout fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgLogout(m.group("username"));
		}
		return null;
	}
	
}
