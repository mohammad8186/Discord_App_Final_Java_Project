package sockets.message.response;
/*this class is used to respond to user requster that 
can  not reply a message or a reaction in private chat
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChatReplyFail extends MsgBase {
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getRef_id() {
		return ref_id;
	}

	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	private String sender;
	private int  ref_id;
	private String body;

	public MsgChatReplyFail( String sender, int ref_id, String body) {
		super(MessageType. RES_REPLY_CHAT_FAIL);
		this.sender = sender;
		this.ref_id = ref_id;
		this.body = body;



	}

}
