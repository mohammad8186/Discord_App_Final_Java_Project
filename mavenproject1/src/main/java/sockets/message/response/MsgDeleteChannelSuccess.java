package sockets.message.response;
/*this class is used to respond to user requster that 
can  delete a channel and was successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgDeleteChannelSuccess extends MsgBase {
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

	private String username;
	private String servername;
	private String channelname;

	public MsgDeleteChannelSuccess(String username, String servername, String channelname) {
		super(MessageType.RES_DELETE_CHANNEL_SUCCESS);
		this.username = username;
		this.servername = servername;
		this.channelname = channelname;
	
	}

	
}
