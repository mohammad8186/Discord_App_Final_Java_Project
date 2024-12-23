package sockets.message.response;
/*this class is used to respond to user requster that 
can  not get a user list from server or from specified channel 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgGetUsersFail extends MsgBase {
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	private int errorCode;
	private String servername;
	private String channelname;

	public MsgGetUsersFail(int errorCode, String servername, String channelname){
		super(MessageType.RES_GET_USERS_FAIL);
		this.errorCode = errorCode;
		this.servername = servername;
		this.channelname = channelname;	

	};
	
	

}
