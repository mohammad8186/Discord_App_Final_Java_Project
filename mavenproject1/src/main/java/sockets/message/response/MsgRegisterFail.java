package sockets.message.response;
/*this class is used to respond to user requster that 
can  not register  
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgRegisterFail extends MsgBase {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	private int errorCode;
	private String username;
	private String email;
	private String mobile;
	private String passwd;
	
	public MsgRegisterFail(int errorCode, String username, String email, String mobile , String passwd) {
		super(MessageType.RES_REGISTER_FAIL);
		this.username = username;
		this.email = email;
		this.passwd = passwd;
		this.mobile = mobile;
		this.errorCode = errorCode;
	}
	
}
