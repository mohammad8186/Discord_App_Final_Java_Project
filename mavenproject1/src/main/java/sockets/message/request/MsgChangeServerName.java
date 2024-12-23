package sockets.message.request;
/*this class changes user's servername 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChangeServerName extends MsgBase {

	private static Pattern pattern = Pattern.compile("^/change_servername (?<username>\\w+) (?<servernameold>\\w+) (?<servernamenew>\\w+)$",  Pattern.CASE_INSENSITIVE); 
	private String username;
	private String servername_old;
	private String servername_new;
	
	 
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getServername_old() {
		return servername_old;
	}


	public void setServername_old(String servername_old) {
		this.servername_old = servername_old;
	}


	public String getServername_new() {
		return servername_new;
	}


	public void setServername_new(String servername_new) {
		this.servername_new = servername_new;
	}


	public MsgChangeServerName(String username, String servername_old, String servername_new) {
		super(MessageType.REQ_CHANGE_SERVERNAME);
		this.username = username;
		this.servername_old = servername_old;
		this.servername_new = servername_new;
		
	}


	public static  MsgChangeServerName fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgChangeServerName(m.group("username"), m.group("servernameold"), m.group("servernamenew"));

		}
		return null;
	}

}

