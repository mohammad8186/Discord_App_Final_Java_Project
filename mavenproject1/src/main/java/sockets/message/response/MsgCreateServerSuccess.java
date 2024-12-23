package sockets.message.response;
/*this class is used to respond to user requster that 
can   create a server was successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgCreateServerSuccess extends MsgBase {
	public String getServername() {
		return servername;
	}
	public void setServername(String servername) {
		this.servername = servername;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String servername;
	private String username;
	public MsgCreateServerSuccess(String username, String servername) {
		super(MessageType.RES_CREATE_SERVER_SUCCESS);
		this.username = username;
		this.servername = servername;
	}

	
}
