package sockets.message;
/*this class consist many types of messages which are sent 
between server and clients 
*these messages are serialized couse they implements Serializable class
*the messages include a pttern to be written by user 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.io.Serializable;
import java.time.LocalDateTime;

import sockets.message.request.*;

public class MsgBase implements Serializable{
	public MessageType msgType;
	LocalDateTime timestamp = LocalDateTime.now();

	public MsgBase(MessageType msgType) {
		this.msgType = msgType;
	}

	public static Object fromString(String msg) {
		Object[] objects = {
			MsgLogin.fromString(msg),
			MsgLogout.fromString(msg),
			MsgRegister.fromString(msg),
			MsgSetUserStatus.fromString(msg),
			MsgCreateServer.fromString(msg),
			MsgCreateChannel.fromString(msg),
			MsgGetUsers.fromString(msg),
			MsgAddChannelUser.fromString(msg),
			MsgDeleteChannel.fromString(msg),
			MsgChangeServerName.fromString(msg),
			MsgChatChannel.fromString(msg),
			MsgChatUser.fromString(msg),
			MsgReplyChannel.fromString(msg),
			MsgReplyUser.fromString(msg),
			MsgPinChat.fromString(msg),
			MsgChangePassword.fromString(msg),
			MsgRequestFriendship.fromString(msg),
			MsgReplyFriendship.fromString(msg),
			MsgGetUserFriends.fromString(msg),
			MsgBlockUser.fromString(msg),
			MsgUnBlockUser.fromString(msg),
			MsgHistoryChatUser.fromString(msg),
			MsgHistoryChatChannel.fromString(msg),
			
		};
		
		for (Object obj: objects) {
			if (obj != null)
				return obj;
		}
		return null;
	}
}
