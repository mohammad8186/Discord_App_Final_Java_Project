/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhsh.mavenproject1;

import javafx.application.Platform;
import sockets.message.request.MsgChatUser;
import sockets.message.response.*;


public class ClientFx extends Client {
    
    
    private  LoginController loginController;
    private   RegisterController registerController ;
    private    ProfileController profileController;
    private  EditUsernameController editUsernameController ;
    private  FriendShipController friendShipController;
    private  addFriendController addfriendController;
    private PendingContoller pendingContoller;
    private ChatController chatController;

    public ChatController getChatController() {
        return chatController;
    }

    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }

    public PendingContoller getPendingContoller() {
        return pendingContoller;
    }

    public void setPendingContoller(PendingContoller pendingContoller) {
        this.pendingContoller = pendingContoller;
    }

    public addFriendController getAddfriendController() {
        return addfriendController;
    }

    public void setAddfriendController(addFriendController addfriendController) {
        this.addfriendController = addfriendController;
    }
    

    public FriendShipController getFriendShipController() {
        return friendShipController;
    }

    public void setFriendShipController(FriendShipController friendShipController) {
        this.friendShipController = friendShipController;
    }

    public  EditUsernameController getEditUsernameController() {
        return editUsernameController;
        
    }

    public void  setEditUsernameController(EditUsernameController editUsernameController) {
        this.editUsernameController = editUsernameController;
        
    }

    public     LoginController getLoginController() {
        return loginController;
       
    }

    public     RegisterController getRegisterController() {
        return registerController;
    }

    public   ProfileController getProfileController() {
        return profileController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
        
        
    }
    
    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

    public  void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
    
   
    
    
    @Override
   public  void loginSuccess(MsgLoginSuccess msg){
        super.loginSuccess(msg);
        if (loginController != null) {
            Platform.runLater(() -> {
                loginController.loginSuccess();
            });
        }
    }
    
    @Override
    	public void registerSuccess(MsgRegisterSuccess msg) {
            super.registerSuccess(msg);
            if(registerController != null){
                  Platform.runLater(() -> {
              registerController.registerSuccess();
            });
                          }
        }
        
       public  void editUsernameSuccess(MsgChangeUsernameSuccess msg){
           // TODO
            if(profileController != null ){
             profileController.changeUsernameSuccess(EditUsernameController.newUsername);
            

           
       }
       }
       
    @Override
       public void  receiveRequestFriendShipSuccess(MsgFriendshipSuccess msg){
           super.receiveRequestFriendShipSuccess(msg);
           if( addfriendController!=null){
                 Platform.runLater(() -> {
                addfriendController.send_RequestFriendShip_Success(msg.getReciever());
               
           });
                         }
           
       }
       
       public void receiveChatUser(MsgChatUser msg){
           //super.receiveChatUser(msg);
           if (username.equals(msg.getReceiver()) && chatController!=null) {
                    Platform.runLater(() -> {
               chatController.receiveChat(msg.getBody());
           });
			
		}

         
           
       }
       }

   
    
