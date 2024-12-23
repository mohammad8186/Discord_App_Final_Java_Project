package sockets.message.request;


/*this class adds users to specified channel
 * is saved in database
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;

import sockets.message.MessageType;
import sockets.message.MsgBase;
import sockets.message.model.Chat;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MsgHistoryChatUser extends MsgBase {

	private static Pattern pattern = Pattern.compile("^/history_chat_user (?<sender>\\w+)$",  Pattern.CASE_INSENSITIVE); 
 
	
  private String sender;
	private ArrayList<Chat> chats;
	
	
  public ArrayList<Chat> getChats() {
		return chats;
	}


	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
	}

	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public  MsgHistoryChatUser( String sender) {
		super(MessageType.HISTORY_CHAT_USER);
		this.sender = sender;
	
		
	}


	public static MsgHistoryChatUser fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgHistoryChatUser(m.group("sender"));

		}
		return null;
	}

}

