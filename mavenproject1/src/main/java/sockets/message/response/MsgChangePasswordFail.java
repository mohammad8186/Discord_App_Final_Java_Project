package sockets.message.response;
/*this class is used to respond to user requster that 
can not change password 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChangePasswordFail extends MsgBase {
	

	public  MsgChangePasswordFail() {
		super(MessageType.RES_CHANGE_PASSWORD_FAIL);
	
	
	}

	
}
