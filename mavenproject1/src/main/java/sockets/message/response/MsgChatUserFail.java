package sockets.message.response;
/*this class is used to respond to user requster that 
can  not send message to a specified user 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChatUserFail extends MsgBase {
	private int errorCode;
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	private String sender;
	private String receiver;
	private String body;

	public MsgChatUserFail( String sender, String receiver, String body) {
		super(MessageType.RES_CREATE_CHANNEL_SUCCESS);
		this.sender = sender;
		this.receiver = receiver;
		this.body = body;
	
	}

	
}
