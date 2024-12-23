package sockets.message.response;
/*this class is used to respond to user requster that 
can  change password and was successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChangePasswordSuccess extends MsgBase {
  


	

	public MsgChangePasswordSuccess(){
		super(MessageType.RES_CHANGE_PASSWORD_SUCCESS);
		
		
	
	}

	
}
