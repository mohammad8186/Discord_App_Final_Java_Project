package sockets.message.request;
/*this class changes account password related to a user
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;

import sockets.message.MessageType;
import sockets.message.MsgBase;

import java.util.regex.Matcher;


public class MsgChangePassword extends MsgBase {

	private String password_old;
        private String password_new;
        private String username;

    public String getPassword_old() {
        return password_old;
    }

    public void setPassword_old(String password_old) {
        this.password_old = password_old;
    }

    public String getPassword_new() {
        return password_new;
    }

    public void setPassword_new(String password_new) {
        this.password_new = password_new;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MsgChangePassword(String username, String password_old, String password_new) {
        super(MessageType.REQ_CHANGE_PASSWORD);
        this.password_old = password_old;
        this.password_new = password_new;
        this.username = username;
    }
        
	

}

