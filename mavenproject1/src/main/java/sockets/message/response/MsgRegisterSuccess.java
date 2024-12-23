package sockets.message.response;
/*this class is used to respond to user requster that 
can  register successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgRegisterSuccess extends MsgBase {
    
        private String username;
	private String password;
	private String email;
	private String mobile;

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	
	
	public MsgRegisterSuccess(String username , String password , String email  , String mobile) {
		super(MessageType.RES_REGISTER_SUCCESS);
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
	}
	
}
