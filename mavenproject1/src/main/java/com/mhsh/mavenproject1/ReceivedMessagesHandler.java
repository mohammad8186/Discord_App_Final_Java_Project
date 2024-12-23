package com.mhsh.mavenproject1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import sockets.message.MsgBase;
import sockets.message.request.*;
import sockets.message.response.*;

public class ReceivedMessagesHandler implements Runnable {

	private Socket server;
	private Client client;
	private ObjectInputStream serverInput;
        public static MsgBase msg;

	public ReceivedMessagesHandler(Socket server, Client client , ObjectInputStream  serverInput) throws IOException {
		this.server = server;
		this.client = client;
		this.serverInput = serverInput;

	}

	@Override
	public void run() {
		
		Logger logger = new Logger(false);

		loop: while (server.isConnected()) {
			try {
				msg = (MsgBase) serverInput.readObject();
				switch (msg.msgType) {
					case RES_LOGIN_SUCCESS:
						client.loginSuccess((MsgLoginSuccess) msg);
						break;

					case RES_LOGIN_FAIL:
						logger.log("failed to login; closing the connection");
						break;

					case RES_REGISTER_SUCCESS:
						client.registerSuccess((MsgRegisterSuccess) msg);
						break;

					case RES_REGISTER_FAIL:
						client.registerFail((MsgRegisterFail) msg);
						break;

					case RES_SET_USER_STATUS_SUCCESS:
						client.setUserStatusSuccess((MsgSetUserStatusSuccess) msg);
						break;

					case RES_SET_USER_STATUS_FAIL:
						logger.log("failed to change status");
						break;

					case RES_CREATE_SERVER_SUCCESS:
						client.createServerSuccess((MsgCreateServerSuccess) msg);
						break;

					case RES_CREATE_SERVER_FAIL:
						logger.log("failed to create server;");
						break;

					case RES_CREATE_CHANNEL_SUCCESS:
						client.createChannelSuccess((MsgCreateChannelSuccess) msg);
						break;

					case RES_CREATE_CHANNEL_FAIL:
						logger.log("failed to create channel");
						break;

//					case RES_GET_USERS:
//						client.logUsers((MsgUsers) msg);
//						break;

					case RES_GET_USERS_FAIL:
						logger.log("failed to get user list");
						break;

					case RES_DELETE_CHANNEL_FAIL:
						logger.log("failed to  delete channel");
						break;

					case RES_DELETE_CHANNEL_SUCCESS:
						client.deleteChannelSuccess((MsgDeleteChannelSuccess) msg);
						break;

					case RES_WELLCOME_ADD_CHANNEL_USER:
						client.wellcomeAddChanelUser((MsgWellcomeAddChannelUser) msg);
						break;

					case RES_CHANGE_SERVERNAME_SUCCESS:
						client.changeServernameSuccess((MsgChangeServernameSuccess) msg);
						break;

					case RES_CHANGE_SERVERNAME_FAIL:
						logger.log("failed to change server name");
						break;

					case RES_CHAT_USER_FAIL:
						client.sendMessageFailToUser((MsgChatUserFail) msg);
						break;

					case CHAT_CHANNEL:
						client.receiveChatChannel((MsgChatChannel) msg);
						break;

					case CHAT_USER:
						client.receiveChatUser((MsgChatUser) msg);
						break;

					case REPLY_CHANNEL:
						client.receiveReplyChannel((MsgReplyChannel) msg);
						break;

					case REPLY_USER:
						client.receiveReplyUser((MsgReplyUser) msg);
						break;

					case RES_CHANGE_PASSWORD_SUCCESS:
						client.changePasswordSuccess((MsgChangePasswordSuccess) msg);
						break;

					case RES_CHANGE_PASSWORD_FAIL:
						logger.log("failed to change password");
						break;

					case RES_ADD_USER_TO_CHANNEL_SUCCESS:
						client.addUserToChannelSuccess((MsgAddChannelUserSuccess) msg);
						break;

					case RES_ADD_USER_TO_CHANNEL_FAIL:
						client.addUserToChannelFail((MsgAddChannelUserFail) msg);
						break;

					case REQUEST_FRIENDSHIP:
						client.receiveRequestFriendShip((MsgRequestFriendship) msg);
						client.received_requestFriendship = true;
						break;
                                                
                                        case RES_FRIENDSHIP_SUCCESS:
                                            client.receiveRequestFriendShipSuccess((MsgFriendshipSuccess) msg);
                                            break;

					case REPLY_FRIENDSHIP:
						client.receiveReplyFriendShip((MsgReplyFriendship) msg);
						break;

					case RES_USER_FRIENDS:
						client.logUserFriends((MsgUserFriends) msg);
						break;

					case PIN_CHAT:
						client.pinChat((MsgPinChat) msg);
						break;

					case PIN_CHAT_FAIL:
						logger.log("failed to pinn message");
						break;

						case HISTORY_CHAT_CHANNEL:
						client.showHistoryOfChatChannels((MsgHistoryChatChannel)msg);
						break;


						case HISTORY_CHAT_USER:
						client.showHistoryOfChatUsers((MsgHistoryChatUser)msg);
						break;

						
          	case RES_HISTORY_CHAT_CHANNEL_FAIL:
						logger.log("failed to show history of chats channel");
						break;


          	case RES_HISTORY_CHAT_USER_FAIL:
						logger.log("failed to show history of chats users");
						break;
           

					default:
						System.out.println("please enter right order");
						break;

				}

			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				break;

			}
		}
		try {

			server.close();

		}
                       catch (IOException e) {
			e.printStackTrace();

		}


        }
  }