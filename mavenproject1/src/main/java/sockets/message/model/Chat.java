package sockets.message.model;
import java.io.Serializable;
public class Chat implements Serializable {
	private int id;
	private String sender;
	private String servername;
	private String channelname;
	private String receiver;
	private 	String body;
	private 	int refId;
	private 	boolean pinned;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getRefId() {
		return refId;
	}

	public void setRefId(int refId) {
		this.refId = refId;
	}

	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}

	public boolean isPrivate(){
		return receiver != null;
	}

	public boolean isPublic(){
		return servername != null && channelname != null;
	}

	public Chat(int id, String sender, String servername, String channelname, String receiver, String body, int refId,
			boolean pinned) {
		this.id = id;
		
		this.sender = sender;
		this.servername = servername;
		this.channelname = channelname;
		this.receiver = receiver;
		this.body = body;
		this.refId = refId;
		this.pinned = pinned;
	}
}
