package sockets.message.request;
/*this class is used to reply as a reaction or answer to another 
message by a ref_id in a private chat between two users
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgReplyUser extends MsgBase {
	private String sender;
	private String receiver;
	private int ref_id;
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

	private String body;
	private static Pattern pattern = Pattern.compile("^/reply_user (?<sender>\\w+) (?<receiver>\\w+) (?<refid>\\d+) (?<body>.+)$",  Pattern.CASE_INSENSITIVE); 

	public MsgReplyUser(String sender, String receiver, int ref_id, String body) {
		super(MessageType.REPLY_USER);		
		this.sender = sender;
		this.receiver = receiver;
    this.ref_id = ref_id;
		this.body = body;
	
	}
 
	public static MsgReplyUser fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgReplyUser(m.group("sender"), m.group("receiver"), Integer.valueOf(m.group("refid")), m.group("body"));
		
		}
		return null;
		
	}
	
}
