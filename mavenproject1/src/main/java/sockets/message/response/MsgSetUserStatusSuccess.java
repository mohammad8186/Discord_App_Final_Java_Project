package sockets.message.response;
/*this class is used to respond to user requster that 
can   change user status successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgSetUserStatusSuccess extends MsgBase {
	private String status;


	public String getStatus(){

		return this.status;
	}
	public void setStatus(String status){

		this.status= status;
	}
	public  MsgSetUserStatusSuccess (String status) {
		super(MessageType.RES_SET_USER_STATUS_SUCCESS);
		this.status = status;
	}
	
}
