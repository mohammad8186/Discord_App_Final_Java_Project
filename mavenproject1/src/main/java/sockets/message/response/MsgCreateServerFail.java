package sockets.message.response;
/*this class is used to respond to user requster that 
can  not create a server
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgCreateServerFail extends MsgBase {
	
	private int errorCode;
	private String username;
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	private String servername;

	public MsgCreateServerFail(int errorCode, String username, String servername) {
		super(MessageType.RES_CREATE_SERVER_FAIL);
		this.errorCode = errorCode;
		this.username = username;
		this.servername = servername;
	}

	
}
