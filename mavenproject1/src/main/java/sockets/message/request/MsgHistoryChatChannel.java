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

public class MsgHistoryChatChannel extends MsgBase {

	private static Pattern pattern = Pattern.compile("^/history_chat_channel (?<servername>\\w+) (?<channelname>\\w+)$",  Pattern.CASE_INSENSITIVE); 
  private ArrayList<Chat> chats;
	
	
  public ArrayList<Chat> getChats() {
		return chats;
	}


	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
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


	private String servername;
	private String channelname;
	public   MsgHistoryChatChannel( String servername , String channelname) {
		super(MessageType.HISTORY_CHAT_CHANNEL);
	
		this.servername = servername;
		this.channelname = channelname;
		
	}


	public static  MsgHistoryChatChannel fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgHistoryChatChannel(m.group("servername") , m.group("channelname"));

		}
		return null;
	}

}

