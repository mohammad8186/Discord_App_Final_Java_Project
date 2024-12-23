package sockets.message.response;
/*this class is used to respond to user requster that 
can not change password 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgPinnedFail extends MsgBase {
	private String body;
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public  MsgPinnedFail() {
		super(MessageType.RES_PINNED_FAIL);
	}

	
}
