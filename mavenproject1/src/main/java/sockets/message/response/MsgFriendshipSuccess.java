package sockets.message.response;
/*this class is used to respond to user requster that 
can   make a friendship request successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgFriendshipSuccess extends MsgBase {
	
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
	private  String sender;
	private  String reciever;
	public  MsgFriendshipSuccess (String sender , String reciever ){
	
		super(MessageType.RES_FRIENDSHIP_SUCCESS);

		this.reciever = reciever;
		this.sender = sender;
		
	
	
	}

	
}
