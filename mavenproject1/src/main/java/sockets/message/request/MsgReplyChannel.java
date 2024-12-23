package sockets.message.request;
/*this class is used to reply as a reaction or answer to another 
message by a ref_id in specified channel
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgReplyChannel extends MsgBase {
	private String sender;
	private String servername;
	private String channelname;
	private int ref_id;
	private String body;
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

	private static Pattern pattern = Pattern.compile("^/reply_channel (?<sender>\\w+) (?<servername>\\w+) (?<channelname>\\w+) (?<refid>\\d+) (?<body>.+)$",  Pattern.CASE_INSENSITIVE); 

	public MsgReplyChannel(String sender, String servername, String channelname, int ref_id, String body) {
		super(MessageType.REPLY_CHANNEL);		
		this.sender = sender;
		this.servername = servername;
    this.channelname = channelname;
		this.ref_id = ref_id;
		this.body = body;
	
	}

	public static MsgReplyChannel fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgReplyChannel(m.group("sender"), m.group("servername"), m.group("channelname"), Integer.valueOf(m.group("refid")), m.group("body"));
		
		}
		return null;
		
	}

	public String channelHash() {
		return String.format("%s:%s", servername, channelname);
	}
	
}
