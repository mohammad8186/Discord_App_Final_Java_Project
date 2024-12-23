package sockets.message.response;
/*this class is used to respond to user requster that 
can  not login to account
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgLoginFail extends MsgBase {
	
	public MsgLoginFail() {
		super(MessageType.RES_LOGIN_FAIL);
	}

	
}
