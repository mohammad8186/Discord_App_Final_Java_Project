package sockets.message.request;
/*this class supplies a envirement to make chats messages 
 *between clients in a private mode
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChatUser extends MsgBase {
	private String sender;
	private String receiver;
	private String body;
	
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

	public MsgChatUser(String sender, String receiver, String body) {
		super(MessageType.CHAT_USER);		
		this.sender = sender;
    this.receiver = receiver;
		this.body = body;
	
	}
 
	
	
}
