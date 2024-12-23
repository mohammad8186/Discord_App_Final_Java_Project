package sockets.message.request;
/*this class creats a channel in a server
 * on the other hand creats a subserver
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgCreateChannel extends MsgBase {
 
	private static Pattern pattern = Pattern.compile("^/create_channel (?<username>\\w+) (?<servername>\\w+) (?<channelname>\\w+)$", Pattern.CASE_INSENSITIVE); // ask your teammate
	private String channelname;
  private String servername;
	private String username;
	public String getChannelname() {
		return channelname;
	}


	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}


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


	public  MsgCreateChannel(String username, String servername , String channelname){
		super(MessageType.REQ_CREATE_CHANNEL);
		this.username = username;
		this.servername = servername;
		this.channelname = channelname;
	
	}


	public static MsgCreateChannel fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgCreateChannel(m.group("username"), m.group("servername"), m.group("channelname"));
		}
		return null;
	}
	
	
}
