package sockets.message.request;
/*this class is used to register users and to log it in database
*for the first time in which user come , it has to register 
*the requierd information to register are username , password , 
email(optional) , mobile(optional)
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgRegister extends MsgBase {
	private String username;
	private String passwd;
	private String email;
	private String mobile;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public MsgRegister(String username, String passwd, String email, String mobile) {
		super(MessageType.REQ_REGISTER);
		this.username = username;
		this.passwd = passwd;
		this.email = email;
		this.mobile = mobile;
		
	}

	
}
