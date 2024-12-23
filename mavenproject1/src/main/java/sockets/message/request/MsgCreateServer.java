package sockets.message.request;
/*this class creats a specified server that could consist several channels
 *the server is used to do many tasks
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgCreateServer extends MsgBase {
 
	private static Pattern pattern = Pattern.compile("^/create_server (?<username>\\w+) (?<servername>\\w+)$", Pattern.CASE_INSENSITIVE); 
	private String servername;
  private String username;
	public String getServername() {
		return servername;
	}


	public void setServername(String servername) {
		this.servername = servername;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public MsgCreateServer(String servername , String username){
		super(MessageType.REQ_CREATE_SERVER);
		this.servername = servername;
		this.username = username;
	
	}


	public static MsgCreateServer fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgCreateServer(m.group("servername"), m.group("username"));
		}
		return null;
	}
	
	
}
