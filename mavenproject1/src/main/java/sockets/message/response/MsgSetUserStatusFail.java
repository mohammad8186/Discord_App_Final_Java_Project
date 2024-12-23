package sockets.message.response;
/*this class is used to respond to user requster that 
can  not change user status
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgSetUserStatusFail extends MsgBase {
  
	public  MsgSetUserStatusFail () {
		super(MessageType.RES_SET_USER_STATUS_SUCCESS);
		
	}
	
}
