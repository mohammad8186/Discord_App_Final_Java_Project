package sockets.message.request;
/*this class blocks  a specified user which is saved in database
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.util.regex.Pattern;

import sockets.message.MessageType;
import sockets.message.MsgBase;

import java.util.regex.Matcher;

public class MsgBlockUser extends MsgBase {

	private static Pattern pattern = Pattern.compile("^/block (?<username>\\w+) (?<busername>\\w+)$",  Pattern.CASE_INSENSITIVE); 
  
	private  String busername;
	private  String username;
	 
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


	public MsgBlockUser( String username , String busername) {
		super(MessageType.REQ_BLOCK_USER);
		this.username = username;
		this.busername  = busername;
		
		
	}


	public static MsgBlockUser fromString(String msg){
		Matcher m = pattern.matcher(msg);
		if (m.matches()){
			return new MsgBlockUser(m.group("username") , m.group("busername"));

		}
		return null;
	}

}

