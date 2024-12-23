package sockets.message.request;


/*this class adds users to specified channel
 * is saved in database
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;

import sockets.message.MessageType;
import sockets.message.MsgBase;

import java.util.regex.Matcher;

public class MsgChangeEmail extends MsgBase {


   private String email_new;
   private String username;

    public String getEmail_new() {
        return email_new;
    }

    public void setEmail_new(String email_new) {
        this.email_new = email_new;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MsgChangeEmail(String email_new, String username) {
        super(MessageType.REQ_CHANGE_EMAIL);
        this.email_new = email_new;
        this.username = username;
    }

    
   
        
        
        
	

}

