package sockets.message.response;
/*this class is used to respond to user requster that 
can not add a user to specified channel
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;
public class MsgAddChannelUserFail extends MsgBase {
	private String username;
	private String servername;
	private String channelname;


	
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



	public MsgAddChannelUserFail(String username , String servername , String channelname ) {
		super(MessageType.RES_ADD_USER_TO_CHANNEL_FAIL);
		this.username = username;
		this.channelname = channelname;
		this.servername = servername;
	}
	
}
