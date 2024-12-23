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

public class MsgChangeMobile extends MsgBase {

     
    
    private String mobile_new;
    private String username;
        
    public String getMobile_new() {
        return mobile_new;
    }

    public void setMobile_new(String mobile_new) {
        this.mobile_new = mobile_new;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
	
        public MsgChangeMobile(String mobile_new  , String username){
            super(MessageType.REQ_CHANGE_MOBILE);
            this.username = username;
            this.mobile_new = mobile_new;
        }
        
        
        
	

}

