package sockets.message.response;
/*this class is used to respond to user requster that 
can not add a user to specified channel
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;
public class MsgHistoryChatUserFail extends MsgBase {




	public MsgHistoryChatUserFail() {
		super(MessageType.RES_HISTORY_CHAT_USER_FAIL);
	
}
}
