/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets.message.request;

import sockets.message.MessageType;
import sockets.message.MsgBase;

/**
 *
 * @author Shahin
 */
public class MsgGetUserInformation extends MsgBase {
    
    private String password;
    private String emaiphon;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmaiphone() {
        return emaiphon;
    }

    public void setEmaiphone(String emaiphon) {
        this.emaiphon = emaiphon;
    }

    public MsgGetUserInformation(String password , String emaiphon) {
        super(MessageType.REQ_GET_USER_INFORMATION);
        this.password = password;
        this.emaiphon = emaiphon;
    }
    
    
    
}
