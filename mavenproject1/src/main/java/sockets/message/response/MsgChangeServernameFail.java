package sockets.message.response;
/*this class is used to respond to user requster that 
can not change servername
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChangeServernameFail extends MsgBase {
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

	public String getServername_old() {
		return servername_old;
	}

	public void setServername_old(String servername_old) {
		this.servername_old = servername_old;
	}

	public String getServername_new() {
		return servername_new;
	}

	public void setServername_new(String servername_new) {
		this.servername_new = servername_new;
	}

	private int errorCode;
	private String username;
	private String servername_old;
	private String servername_new;

	public MsgChangeServernameFail(int errorCode, String username, String servername_old, String servername_new) {
		super(MessageType.RES_CHANGE_SERVERNAME_FAIL);
		this.errorCode = errorCode;
		this.username = username;
		this.servername_old = servername_old;
		this.servername_new = servername_new;
	
	}

	
}
