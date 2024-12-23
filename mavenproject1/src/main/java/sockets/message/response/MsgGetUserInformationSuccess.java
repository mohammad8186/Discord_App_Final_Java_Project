/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets.message.response;

import sockets.message.MessageType;
import sockets.message.MsgBase;

public class MsgGetUserInformationSuccess extends MsgBase {
    
      private String password;
    private String emaiphon;
    private String username;

    public MsgGetUserInformationSuccess(String password, String emaiphon, String username) {
        super(MessageType.RES_GET_USER_INFORMATION_SUCCESS);
        this.password = password;
        this.emaiphon = emaiphon;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmaiphon() {
        return emaiphon;
    }

    public void setEmaiphon(String emaiphon) {
        this.emaiphon = emaiphon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
