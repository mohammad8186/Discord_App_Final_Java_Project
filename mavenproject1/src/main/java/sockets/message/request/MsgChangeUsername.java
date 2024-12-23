/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets.message.request;

import sockets.message.MessageType;
import sockets.message.MsgBase;


public class MsgChangeUsername extends MsgBase {

	private String username_new;
        private String username_old;

    public String getUsername_old() {
        return username_old;
    }

    public void setUsername_old(String username_old) {
        this.username_old = username_old;
    }
	private String password;
	public String getUsername_new() {
		return username_new;
	}
	public void setUsername_new(String username_new) {
		this.username_new = username_new;
	}
	public String  getpassword() {
		return  password;
	}
	public void setpassword( String password) {
		this.password =  password;
	}
	public MsgChangeUsername(String username_old,String username_new, String  password) {
		super(MessageType.REQ_CHANGE_USERNAME);
		this.username_new = username_new;
		this. password = password;
                this.username_old = username_old;
               
	}

	

}
