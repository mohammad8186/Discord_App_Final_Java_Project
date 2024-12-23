package sockets.message.response;
/*this class is used to show wellcome message that could 
successfully be added to channel

 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgWellcomeAddChannelUser extends MsgBase {
	private  String servername;
	private  String channelname;
	private  String username;

	
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public MsgWellcomeAddChannelUser ( String servername , String channelname  , String username) {
		super(MessageType.RES_WELLCOME_ADD_CHANNEL_USER);
		this.username = username;
		this.channelname = channelname;
		this.servername = servername;
		
	}
}
