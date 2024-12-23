package sockets.message.request;
/*this class gets whole users or a channel users to be showed for requester
 *users feathers is gotten from sqlite database  
 *between clients in a private mode
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgGetUsers extends MsgBase {
	private static Pattern pattern = Pattern.compile("^/get_users( (?<servername>\\w+) (?<channelname>\\w+))?$", Pattern.CASE_INSENSITIVE);
	private String servername;
	private String channelname;

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


	public MsgGetUsers(String servername, String channelname) {
		super(MessageType.REQ_GET_USERS);
		this.servername = servername;
		this.channelname = channelname;	

	}


	public static MsgGetUsers fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgGetUsers(m.group("servername"), m.group("channelname"));
			
		}
		return null;
	}
	
}
