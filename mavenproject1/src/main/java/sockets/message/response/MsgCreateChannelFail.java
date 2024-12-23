package sockets.message.response;
/*this class is used to respond to user requster that 
can  not create a channel
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgCreateChannelFail extends MsgBase {
	private int errorCode;
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

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

	public MsgCreateChannelFail(int errorCode, String username, String servername, String channelname) {
		super(MessageType.RES_CREATE_CHANNEL_SUCCESS);
		this.errorCode = errorCode;
		this.username = username;
		this.servername = servername;
		this.channelname = channelname;
	
	}

	
}
