package sockets.message.request;

/*this class is used pin a specified message in channel 
or private chat 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;
import sockets.message.model.Chat;

import java.util.regex.Matcher;

public class MsgPinChat extends MsgBase {

	private static Pattern pattern_pin = Pattern.compile("^/pin_chat (?<username>\\w+) (?<id>\\d+)$",
			Pattern.CASE_INSENSITIVE);
	private static Pattern pattern_unpin = Pattern.compile("^/unpin_chat (?<username>\\w+) (?<id>\\d+)$",
			Pattern.CASE_INSENSITIVE);
	private String username;
	private int id;
	private Chat chat;
	private boolean pinned;

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}

	public MsgPinChat(String username, int id, boolean pinned) {
		super(MessageType.PIN_CHAT);
		this.username = username;
		this.id = id;
		this.pinned = pinned;
	}

	public static MsgPinChat fromString(String msg) {
		Matcher m_pin = pattern_pin.matcher(msg);
		Matcher m_unpin = pattern_unpin.matcher(msg);
		if (m_pin.matches()) {
			return new MsgPinChat(m_pin.group("username"), Integer.valueOf(m_pin.group("id")), true);

		} else if (m_unpin.matches()) {
			return new MsgPinChat(m_unpin.group("username"), Integer.valueOf(m_unpin.group("id")), false);

		}
		return null;
	}

}
