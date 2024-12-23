package sockets.message.response;
/*this class is used to respond to user requster that 
can  not make a friendship request
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgFriendshipFail extends MsgBase {
	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getReciever() {
		return reciever;
	}


	public void setReciever(String reciever) {
		this.reciever = reciever;
	}


	private String sender;
	private String reciever;


	public   MsgFriendshipFail(String sender , String reciever){
		super(MessageType.RES_FRIENDSHIP_FAIL);
		this.reciever  = reciever;
		this.sender = sender;
	
	
	}

	
}
