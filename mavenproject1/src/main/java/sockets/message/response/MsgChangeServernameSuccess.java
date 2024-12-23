package sockets.message.response;
/*this class is used to respond to user requster that 
can  change servername and was successfully
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgChangeServernameSuccess extends MsgBase {
	private String username;
	private String servername_old;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getServername_old() {
		return servername_old;
	}

	public void setServername_old(String servername_old) {
		this.servername_old = servername_old;
	}

	public String getServername_new() {
		return servername_new;
	}

	public void setServername_new(String servername_new) {
		this.servername_new = servername_new;
	}

	private String servername_new;

	public MsgChangeServernameSuccess(String username, String servername_old, String servername_new) {
		super(MessageType.RES_CHANGE_SERVERNAME_SUCCESS);
		this.username = username;
		this.servername_old = servername_old;
		this.servername_new = servername_new;
	
	}

	
}
