package sockets.message.request;
/*this class supplies a envirement to make chats messages 
 *between clients in a specified channel
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChatChannel extends MsgBase {
	private String sender;
	private String servername;
	private String channelname;
	private String body;
	private static Pattern pattern = Pattern.compile("^/chat_channel (?<sender>\\w+) (?<servername>\\w+) (?<channelname>\\w+) (?<body>.+)$",  Pattern.CASE_INSENSITIVE); 

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

	public MsgChatChannel(String sender, String servername, String channelname, String body) {
		super(MessageType.CHAT_CHANNEL);		
		this.sender = sender;
		this.servername = servername;
    this.channelname = channelname;
		this.body = body;
	
	}

	public static MsgChatChannel fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgChatChannel(m.group("sender"), m.group("servername"), m.group("channelname"), m.group("body"));
		
		}
		return null;
		
	}

	public String channelHash() {
		return String.format("%s:%s", servername, channelname);
	}
	
}
