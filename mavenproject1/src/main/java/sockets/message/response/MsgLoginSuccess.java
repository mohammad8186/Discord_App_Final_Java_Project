package sockets.message.response;
/*this class is used to respond to user requster that 
can  login to account successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.ArrayList;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgLoginSuccess extends MsgBase {



	private String password;
        private String username;
        private String mobile;
        private String email;
        private ArrayList<String> server_channels;
        
        
        	public ArrayList<String> getServer_channels() {
		return server_channels;
	}

	public void setServer_channels(ArrayList<String> server_channels) {
		this.server_channels = server_channels;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
     
        
	
	public MsgLoginSuccess(  String username,String password, ArrayList<String> server_channels) {
		super(MessageType.RES_LOGIN_SUCCESS);
		this.password = password;
		this.server_channels = server_channels;
                this.username = username;
		
	}
	
}
