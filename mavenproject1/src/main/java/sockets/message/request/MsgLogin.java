package sockets.message.request;
/*this class is used to be loggined by users to their accounts
 *befor all , the user has to login to send related commands 
 *the loggin step is taken in the beggining in by this class
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgLogin extends MsgBase {
	private String emaiPhon;
	private String passwd;
    


 
    public String getEmaiPhon() {
        return emaiPhon;
    }

    public void setEmaiPhon(String emaiPhon) {
        this.emaiPhon = emaiPhon;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
	

	public MsgLogin(String passwd, String emaiPhon) {
		super(MessageType.REQ_LOGIN);
		this.emaiPhon = emaiPhon;
		this.passwd = passwd;
                
	}

	
}
