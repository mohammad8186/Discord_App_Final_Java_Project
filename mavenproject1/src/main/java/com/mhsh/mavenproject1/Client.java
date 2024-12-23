package com.mhsh.mavenproject1;

/*this class is used to manage clients tasks such as sending messages by
specified messages 
* the written messages by client(user) are request_messages type which will be
* responded by messages from serverside by sockets streams
* this class is front_end and clientside and softeware
* this class has command line UI 
* @author MohammadHosein Shahbazi
* @since 2022-06-12
*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import sockets.message.MsgBase;
import sockets.message.model.Chat;
import sockets.message.model.User;
import sockets.message.request.*;
import sockets.message.response.*;

public class Client {
	public  String username;
	public   String password;
	public String mobile;
	public  String email;
	public  String status;
        public int pendingCounter;
	public boolean received_requestFriendship = false;////
	private ArrayList<String> channel_hash_list = new ArrayList<String>();
	private Logger logger = new Logger(false);
        public  Socket server;
        public  ObjectOutputStream output;
        public  ObjectInputStream input;
        public ReceivedMessagesHandler receivedMessagesHandler;



	public Client() {
	}

	public void main(){
            connect();
        }
        
        public  void connect()  {
            try {
             
                server = new Socket(Config.host, Config.port);
                    output = new ObjectOutputStream(server.getOutputStream());
                 input = new  ObjectInputStream(server.getInputStream());
                receivedMessagesHandler = new ReceivedMessagesHandler(server, this ,input );
                Thread thread = new Thread(receivedMessagesHandler);
		thread.start();			                            
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
                

	
                
                
                
        }

	void loginSuccess(MsgLoginSuccess msg) {
		username = msg.getUsername();
                password = msg.getPassword();
                email = msg.getEmail();
                mobile = msg.getMobile();
		channel_hash_list = msg.getServer_channels();
		logger.log("user %s logged in successfully", username);

	}

	void logout(MsgLogout msg) {
		username = null;
		channel_hash_list = new ArrayList<String>();
		logger.log("user %s logged out successfully", msg.getUsername());

	}

	void registerSuccess(MsgRegisterSuccess msg) {
		username = msg.getUsername();
		password = msg.getPassword();
		email = msg.getEmail();
		mobile = msg.getMobile();
		logger.log("user %s registered successfully", username);

	}

	void createServerSuccess(MsgCreateServerSuccess msg) {
		logger.log("server %s created successfully by %s", msg.getServername(), msg.getUsername());

	}

	void createChannelSuccess(MsgCreateChannelSuccess msg) {
		channel_hash_list.add(String.format("%s:%s", msg.getServername(), msg.getChannelname()));
		logger.log("channel %s created successfully by %s", msg.getChannelname(), msg.getUsername());

	}

	public void setUserStatusSuccess(MsgSetUserStatusSuccess msg) {
		status = msg.getStatus();
	}

	void registerFail(MsgRegisterFail msg) {
		switch (msg.getErrorCode()) {
			case 19: // unique constraint
				logger.log("the user %s already exists", msg.getUsername());
				break;

			case 1001: // invalid user
				logger.log("the user %s is invalid", msg.getUsername());
				break;

			case 1002: // invalid password
				logger.log("the password of the user %s is invalid", msg.getUsername());
				break;

			case 1003: // invalid password
				logger.log("the email %s is invalid", msg.getEmail());
				break;

			case 1004: // invalid password
				logger.log("the mobile %s is invalid", msg.getMobile());
				break;

			default:
				logger.log("failed to register user %s", msg.getUsername());
				break;

		}

	}

	/*public void logUsers(MsgUsers msg) {
		logger.log("users of the channel %s on the server %s", msg.getChannelname(), msg.getServername());
		for (String username : msg.getUsers()) {
			logger.log(username);

		}
        

	}
*/
	public void changeServernameSuccess(MsgChangeServernameSuccess msg) {
		logger.log("server %s successfully was changed name to %s  ", msg.getServername_old(), msg.getServername_new());

	}

	public void deleteChannelSuccess(MsgDeleteChannelSuccess msg) {
		channel_hash_list.remove(String.format("%s:%s", msg.getServername(), msg.getChannelname()));
		logger.log("channel % deleted successfully", msg.getChannelname());

	}

	public void receiveChatChannel(MsgChatChannel msg) {
		if (channel_hash_list.contains(msg.channelHash())) {
			logger.log("@%s: %s", msg.getSender(), msg.getBody());
		}

	}

	public void receiveChatUser(MsgChatUser msg) {
		if (username.equals(msg.getReceiver())) {
			logger.log("@%s: %s", msg.getSender(), msg.getBody());
		}

	}

	public void receiveReplyUser(MsgReplyUser msg) {
		if (username.equals(msg.getReceiver())) {
			logger.log("@%s replied to message : %s", msg.getSender(), msg.getBody());
		}

	}

	public void receiveReplyChannel(MsgReplyChannel msg) {
		if (channel_hash_list.contains(msg.channelHash())) {
			logger.log("@%s replied %s to message", msg.getSender(), msg.getBody());
		}

	}

	public void sendMessageFailToChannel(MsgChatChannelFail msg) {
		logger.log("failed to send message to channel %s ", msg.getChannelname());

	}

	public void sendMessageFailToUser(MsgChatUserFail msg) {
		logger.log("failed to send message from %s to user %s", msg.getSender(), msg.getReceiver());

	}

	public void changePasswordSuccess(MsgChangePasswordSuccess msg) {
		logger.log("your password successfully changed ");

	}

	public void receiveRequestFriendShip(MsgRequestFriendship msg) {
		if (username.equals(msg.getFriendname())) {
			logger.log("@%s has sent you a friendship request ", msg.getUsername());

		}

	}

	public void FriendShipFail(MsgFriendshipFail msg) {
		if (username.equals(msg.getReciever())) {
			logger.log("%s rejected your request!", msg.getReciever());

		}

	}

	public void FriendShipSuccess(MsgFriendshipSuccess msg) {
		if (username.equals(msg.getReciever())) {
			logger.log("%s accepted your request", msg.getReciever());

		}

	}

	public void logUserFriends(MsgUserFriends msg) {
		logger.log("friends of %s are:", msg.getUsername());
		for (User friend : msg.getFriends()) {
			logger.log("@%s: %s", friend.getUsername(), friend.getStatus());

		}

	}

	public void addUserToChannelFail(MsgAddChannelUserFail msg) {
		logger.log("failed to add user %s to channel %s in server %s", msg.getUsername(), msg.getChannelname(),
				msg.getServername());

	}

	public void addUserToChannelSuccess(MsgAddChannelUserSuccess msg) {
		logger.log("user %s  suucess fully added to channel %s in server %s", msg.getUsername(), msg.getChannelname(),
				msg.getServername());

	}

	public void replyChatFail(MsgChatReplyFail msg) {
		logger.log("failed to reply message %s sended by ", msg.getBody(), msg.getSender());

	}

	public void wellcomeAddChanelUser(MsgWellcomeAddChannelUser msg) {

		if (username.equals(msg.getUsername())) {
			channel_hash_list.add(String.format("%s:%s", msg.getServername(), msg.getChannelname()));
			logger.log("%s wellcome to the channel!", msg.getUsername());
		}

	}

	public void receiveReplyFriendShip(MsgReplyFriendship msg) {
		if (username.equals(msg.getRequester())) {
			logger.log("@%s has %s your friendship request", msg.getUsername(), msg.getStatus());
		}

	}

	public void pinChat(MsgPinChat msg) {

		if (msg.getChat().getReceiver() == this.username && msg.isPinned() == true) {
			logger.log("%s pinned  ", msg.getChat().getBody());
		}

		else if (channel_hash_list.contains(msg.getChat().getChannelname()) && msg.isPinned() == true) {
			logger.log("@%s pinned  ", msg.getChat().getBody());
		}

		else if (msg.getChat().getReceiver() == this.username && msg.isPinned() == false) {
			logger.log("%s unpinned  ", msg.getChat().getBody());
		}

		else if (channel_hash_list.contains(msg.getChat().getChannelname()) && msg.isPinned() == false) {
			logger.log("@%s unpinned  ", msg.getChat().getBody());
		}

	}

	public void showHistoryOfChatChannels(MsgHistoryChatChannel msg) {
     int i = 0;
		for (Chat chat: msg.getChats()){
			System.out.format("message %s: -- channel:%s -- server:%s -- sender:@%s \n" ,chat.getBody() , msg.getChats().get(i).getChannelname() , msg.getChats().get(i).getServername() , msg.getChats().get(i).getSender());
			i+=1;
		}
	}

	public void showHistoryOfChatUsers(MsgHistoryChatUser msg) {

		int i = 0;
		for (Chat chat : msg.getChats()){
 
			System.out.format("message %s: -- sender:%s -- reciever:%s \n" , msg.getChats().get(i).getBody()  , msg.getChats().get(i).getSender() , msg.getChats().get(i).getReceiver());
			i+=1;
		}
	}

    void receiveRequestFriendShipSuccess(MsgFriendshipSuccess msg) {
         logger.log("@%s has riecived a requestFriendShip from @%s", msg.getReciever() , msg.getSender());
    }


    
	}