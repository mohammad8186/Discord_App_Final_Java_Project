package sockets.message.request;


/*this class adds users to specified channel
 * is saved in database
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;

import sockets.message.MessageType;
import sockets.message.MsgBase;

import java.util.regex.Matcher;

public class MsgAddChannelUser extends MsgBase {

	private static Pattern pattern = Pattern.compile("^/add_channel_user (?<servername>\\w+) (?<channelname>\\w+) (?<username>\\w+)$",  Pattern.CASE_INSENSITIVE); 
  private  String servername;
	private  String channelname;
	private  String username;

	 
	public void setServername(String servername) {
		this.servername = servername;
	}


	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getServername() {
		return servername;
	}


	public String getChannelname() {
		return channelname;
	}


	public String getUsername() {
		return username;
	}


	public MsgAddChannelUser( String servername , String channelname  , String username) {
		super(MessageType.REQ_ADD_CHANNEL_USER);
		this.username = username;
		this.channelname = channelname;
		this.servername = servername;
		
	}


	public static MsgAddChannelUser fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgAddChannelUser(m.group("servername") , m.group("channelname") , m.group("username"));

		}
		return null;
	}

}

