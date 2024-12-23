package sockets.message.request;
/*this class is used to unblock a user 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;
import sockets.message.MessageType;
import sockets.message.MsgBase;
import java.util.regex.Matcher;

public class MsgUnBlockUser extends MsgBase {

	private static Pattern pattern = Pattern.compile("^/block (?<username>\\w+) (?<busername>\\w+)$",  Pattern.CASE_INSENSITIVE); 
	private  String username;
	private  String busername;
	public String getBusername() {
		return busername;
	}


	public void setBusername(String busername) {
		this.busername = busername;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	 
	public MsgUnBlockUser( String username , String busername) {
		super(MessageType.REQ_UNBLOCK_USER);
		this.username = username;
		this.busername  = busername;
		
		
	}


	public static MsgUnBlockUser fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgUnBlockUser(m.group("username") , m.group("busername"));

		}
		return null;
	}

}

