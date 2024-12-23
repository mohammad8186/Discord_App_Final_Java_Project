package sockets.message.request;
/*this class delete a channel in a server
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;
import java.util.regex.Matcher;

public class MsgDeleteChannel extends MsgBase {

	private static Pattern pattern = Pattern.compile("^/delete_channel (?<username>\\w+) (?<servername>\\w+) (?<channelname>\\w+)$", Pattern.CASE_INSENSITIVE);
	private String username;
  private  String servername;
	private  String channelname;
	 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public MsgDeleteChannel(String username, String servername, String channelname) {
		super(MessageType.REQ_DELETE_CHANNEL);
		this.username = username;
		this.servername = servername;
		this.channelname = channelname;
		
	}

	public static MsgDeleteChannel fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgDeleteChannel(m.group("username"), m.group("servername"), m.group("channelname"));

		}
		return null;
	}

}

