package sockets.message.response;
/*this class is used to respond to user requster that 
can  not send message to channel
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChatChannelFail extends MsgBase {
	private int errorCode;
	private String sender;
	private String servername;
	public int getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
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


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	private String channelname;
	private String body;


	public MsgChatChannelFail(int errorCode, String sender, String servername, String channelname) {
		super(MessageType.RES_CHAT_CHANNEL_FAIL);
		this.errorCode = errorCode;
		this.sender = sender;
		this.servername = servername;
    this.channelname = channelname;
	
	}

	
}
