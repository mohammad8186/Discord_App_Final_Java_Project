package com.mhsh.mavenproject1;

/*this class is used to respond and hanlde messege sended by clients
 *this class is back_end and serverside 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import sockets.message.MsgBase;
import sockets.message.model.Chat;
import sockets.message.model.User;
import sockets.message.request.*;
import sockets.message.response.*;

class ClientSocket {

	public Socket socket;
	public ObjectOutputStream output;
	public ObjectInputStream input;

	public ClientSocket(Socket socket, ObjectOutputStream output, ObjectInputStream input) {
		this.socket = socket;
		this.output = output;
		this.input = input;
	}

}

public class Server {

	private List<ClientSocket> clients;
	private ServerSocket server;
	private Database db;
	private Logger logger = new Logger(false);

	public static void main(String[] args) throws IOException, SQLException {
		new Server().run();

	}

	public Server() throws SQLException{
            try {
                clients = new ArrayList<ClientSocket>();
                db = new Database();
                db.open();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
	}

	public void run() throws IOException {
		server = new ServerSocket(Config.port) {
			protected void finalize() throws IOException, SQLException {
				db.close();
				close();

			}
		};
		logger.log("listening on %s:%d", Config.host, Config.port);

		while (true) {
			Socket socket = server.accept();
			ClientSocket client = new ClientSocket(socket, new ObjectOutputStream(socket.getOutputStream()),
					new ObjectInputStream(socket.getInputStream()));
			logger.log("connection established with client: %s", socket.getInetAddress().getHostAddress());

			clients.add(client);

			new Thread(new ClientHandler(this, client)).start();
		}
	}

	boolean userIsServerOwner(String username, String servername) throws SQLException {
		return db.getServerOwner(servername).equals(username);
	}

	void login(MsgLogin msg, ObjectOutputStream clientOutput) throws IOException {
            String username;
            try {
		
                       
                        username = db.login(msg.getPasswd(), msg.getEmaiPhon());     
                               
			if (username != null) {
				db.setUserStatus(username, "online");
				clientOutput.writeUnshared(new MsgLoginSuccess(username, msg.getPasswd(), db.getUserChannels(msg.getPasswd())));

			} else {
				clientOutput.writeUnshared(new MsgLoginFail());

			}
		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgLoginFail());
			e.printStackTrace();

		}
		;
	}

	public void register(MsgRegister msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			if (!Pattern.compile("\\w{6,}").matcher(msg.getUsername()).matches()) {
				clientOutput.writeUnshared(
						new MsgRegisterFail(1001, msg.getUsername(), msg.getEmail(), msg.getMobile(), msg.getPasswd()));

			} else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$").matcher(msg.getPasswd())
					.matches()) {
				clientOutput.writeUnshared(
						new MsgRegisterFail(1002, msg.getUsername(), msg.getEmail(), msg.getMobile(), msg.getPasswd()));

			} else if (!Pattern
					.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
					.matcher(msg.getEmail()).matches()) {
				clientOutput.writeUnshared(
						new MsgRegisterFail(1003, msg.getUsername(), msg.getEmail(), msg.getMobile(), msg.getPasswd()));

			} else if (!Pattern.compile("^(?:0|98|\\+98|\\+980|0098|098|00980)?(9\\d{9})$").matcher(msg.getMobile())
					.matches()) {
				clientOutput.writeUnshared(
						new MsgRegisterFail(1004, msg.getUsername(), msg.getEmail(), msg.getMobile(), msg.getPasswd()));

			} else {
				db.createUser(msg.getUsername(), msg.getPasswd(), msg.getEmail(), msg.getMobile());
				clientOutput
						.writeUnshared(new MsgRegisterSuccess(msg.getUsername(), msg.getPasswd(), msg.getEmail(), msg.getMobile()));

			}
		} catch (SQLException e) {
			clientOutput.writeUnshared(
					new MsgRegisterFail(e.getErrorCode(), msg.getUsername(), msg.getEmail(), msg.getMobile(), msg.getPasswd()));

		}
	}

	public void setUserStatus(MsgSetUserStatus msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.setUserStatus(msg.getUsername(), msg.getStatus());
			clientOutput.writeUnshared(new MsgSetUserStatusSuccess(msg.getStatus()));

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgSetUserStatusFail());
			e.printStackTrace();

		}
	}

	public void createServer(MsgCreateServer msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.createServer(msg.getUsername(), msg.getServername());
			clientOutput.writeUnshared(new MsgCreateServerSuccess(msg.getUsername(), msg.getServername()));

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgCreateServerFail(e.getErrorCode(), msg.getUsername(), msg.getServername()));
			e.printStackTrace();

		}
	}

	public void createChannel(MsgCreateChannel msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.createChannel(msg.getServername(), msg.getChannelname());
			clientOutput
					.writeUnshared(new MsgCreateChannelSuccess(msg.getUsername(), msg.getServername(), msg.getChannelname()));

		} catch (SQLException e) {
			clientOutput.writeUnshared(
					new MsgCreateChannelFail(e.getErrorCode(), msg.getUsername(), msg.getServername(), msg.getChannelname()));
			e.printStackTrace();

		}
	}

	public void deleteChannel(MsgDeleteChannel msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.deleteChannel(msg.getServername(), msg.getChannelname());
			clientOutput
					.writeUnshared(new MsgDeleteChannelSuccess(msg.getUsername(), msg.getServername(), msg.getChannelname()));

		} catch (SQLException e) {
			clientOutput.writeUnshared(
					new MsgDeleteChannelFail(e.getErrorCode(), msg.getUsername(), msg.getServername(), msg.getChannelname()));
			e.printStackTrace();

		}

	}

	public void addChannelUser(MsgAddChannelUser msg, ObjectOutputStream clientOutput) throws IOException {

		try {

			db.addChannelUser(msg.getServername(), msg.getChannelname(), msg.getUsername());
			for (ClientSocket client : clients) {
				client.output
						.writeUnshared(new MsgWellcomeAddChannelUser(msg.getServername(), msg.getChannelname(), msg.getUsername()));
			}
			clientOutput
					.writeUnshared(new MsgAddChannelUserSuccess(msg.getUsername(), msg.getServername(), msg.getChannelname()));
		} catch (SQLException e) {
			clientOutput
					.writeUnshared(new MsgAddChannelUserFail(msg.getUsername(), msg.getServername(), msg.getChannelname()));
			e.printStackTrace();
		}

	}

	public void getUsers(MsgGetUsers msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			clientOutput.writeUnshared(new MsgUsers(msg.getServername(), msg.getChannelname(),
					db.getUsers(msg.getServername(), msg.getChannelname())));

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgGetUsersFail(e.getErrorCode(), msg.getServername(), msg.getChannelname()));
			e.printStackTrace();

		}

	}

	public void changeServerName(MsgChangeServerName msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.changeServername(msg.getUsername(), msg.getServername_old(), msg.getServername_new());
			clientOutput.writeUnshared(
					new MsgChangeServernameSuccess(msg.getUsername(), msg.getServername_old(), msg.getServername_new()));

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgChangeServernameFail(e.getErrorCode(), msg.getUsername(),
					msg.getServername_old(), msg.getServername_new()));
			e.printStackTrace();
		}

	}

	public void broadcastChatChannel(MsgChatChannel msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.createChat(msg.getSender(), msg.getServername(), msg.getChannelname(), msg.getBody());
			for (ClientSocket client : clients) {
				client.output.writeUnshared(msg);

			}

		} catch (SQLException e) {
			clientOutput.writeUnshared(
					(new MsgChatChannelFail(e.getErrorCode(), msg.getSender(), msg.getServername(), msg.getChannelname())));
			e.printStackTrace();

		}
	}

	public void broadcastChatUser(MsgChatUser msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			ArrayList<String> blackListUsers = db.getUserBlacklist(msg.getSender());
			for (String s : blackListUsers) {

				if (s.equals(msg.getReceiver())) {
					clientOutput.writeUnshared(new MsgChatUserFail(msg.getSender(), msg.getReceiver(), msg.getBody()));
					return;
				}
			}
			db.createChat(msg.getSender(), msg.getReceiver(), msg.getBody());
			for (ClientSocket client : clients) {
				client.output.writeUnshared(msg);

			}
		} catch (SQLException e) {
			clientOutput
					.writeUnshared(new MsgChatUserFail(msg.getSender(), msg.getReceiver(), msg.getBody()));
			e.printStackTrace();

		}
	}

	public void broadcastReplyUser(MsgReplyUser msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.createReply(msg.getSender(), msg.getReceiver(), msg.getRef_id(), msg.getBody());
			for (ClientSocket client : clients) {
				client.output.writeUnshared(msg);

			}
		} catch (SQLException e) {

			clientOutput.writeUnshared(new MsgChatReplyFail(msg.getSender(), msg.getRef_id(), msg.getBody()));
			e.printStackTrace();

		}
	}

	public void broadcastReplyChannel(MsgReplyChannel msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.createReply(msg.getSender(), msg.getServername(), msg.getChannelname(), msg.getRef_id(), msg.getBody());
			for (ClientSocket client : clients) {
				client.output.writeUnshared(msg);

			}
		} catch (SQLException e) {

			clientOutput.writeUnshared(new MsgChatReplyFail(msg.getSender(), msg.getRef_id(), msg.getBody()));
			e.printStackTrace();

		}

	}

	public void broadcastPinChat(MsgPinChat msg, ObjectOutputStream clientOutput) throws IOException {
		try {

			Chat chat = db.pinChat(msg.getId(), msg.isPinned());
			if (chat == null) {
				return;

			} else if (chat.isPublic()) {
				msg.setChat(chat);
				for (ClientSocket client : clients) {
					client.output.writeUnshared(msg);

				}

			} else if (chat.isPrivate()) {
				msg.setChat(chat);
				for (ClientSocket client : clients) {
					client.output.writeUnshared(msg);

				}
			}

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgPinnedFail());
			e.printStackTrace();

		}

	}

	public void changePassword(MsgChangePassword msg, ObjectOutputStream clientOutput) throws IOException {

		try {
			db.changePassword(msg.getUsername(), msg.getPassword_old(), msg.getPassword_new());
			clientOutput
					.writeUnshared(new MsgChangePassword(msg.getUsername(), msg.getPassword_old(), msg.getPassword_new()));

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgChangePasswordFail());
			e.printStackTrace();

		}

	}

	public void requestFriendship(MsgRequestFriendship msg, ObjectOutputStream clientOutput) throws IOException {
		try {
			db.requestFriendship(msg.getUsername(), msg.getFriendname());
			for (ClientSocket client : clients) {
				client.output.writeUnshared(msg);
                                
                                
			}
                        clientOutput
					.writeUnshared(new MsgFriendshipSuccess(msg.getUsername() , msg.getFriendname()));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void replyFriendship(MsgReplyFriendship msg, ObjectOutputStream clientOutput) throws IOException {

		try {
			db.replyFriendship(msg.getUsername(), msg.getRequester(), msg.getStatus());
			for (ClientSocket client : clients) {
				client.output.writeUnshared(msg);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void getUserFriends(MsgGetUserFriends msg, ObjectOutputStream clientOutput) throws IOException {

		try {
			clientOutput.writeUnshared(new MsgUserFriends(msg.getUsername(), db.getUserFriends(msg.getUsername())));

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void blockUser(MsgBlockUser msg, ObjectOutputStream clientOutput) {

		try {
			db.blockUser(msg.getUsername(), msg.getBusername());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void unBlockUser(MsgUnBlockUser msg, ObjectOutputStream clientOutput) {

		try {
			db.unblockUser(msg.getUsername(), msg.getBusername());
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void historyChatChannel(MsgHistoryChatChannel msg, ObjectOutputStream clientOutput) throws IOException {

		try {
			ArrayList<Chat> chats = db.getChats(msg.getServername(), msg.getChannelname());
			msg.setChats(chats);
			clientOutput.writeUnshared(msg);

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgHistoryChatChannelFail());
			e.printStackTrace();
		}

	}

	public void historyChatUser(MsgHistoryChatUser msg, ObjectOutputStream clientOutput) throws IOException {

		try {
			ArrayList<Chat> chats = db.getChats(msg.getSender());
			msg.setChats(chats);
			clientOutput.writeUnshared(msg);
		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgHistoryChatUserFail());
			e.printStackTrace();
		}

	}

    void changeUsername(MsgChangeUsername msg, ObjectOutputStream clientOutput) throws IOException {
        
        
        
        
        
        try {
			db.changeUsername(msg.getUsername_old(),msg.getUsername_new() , msg.getpassword());
			clientOutput
					.writeUnshared(new MsgChangeUsernameSuccess());

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgChangeUsernameFail());
			e.printStackTrace();

		}
        
    }

    void changeMobile(MsgChangeMobile msg, ObjectOutputStream clientOutput) throws IOException {
        
            try {
			db.changeMobile(msg.getMobile_new(), msg.getUsername());
			clientOutput
					.writeUnshared(new MsgChangeMobileSuccess());

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgChangeMobileFail());
			e.printStackTrace();

		}
    
    }

    void changeEmail(MsgChangeEmail msg, ObjectOutputStream clientOutput) throws IOException {
           try {
			db.changeEmail(msg.getEmail_new(), msg.getUsername());
			clientOutput
					.writeUnshared(new MsgChangeEmailSuccess());

		} catch (SQLException e) {
			clientOutput.writeUnshared(new MsgChangeEmailFail());
			e.printStackTrace();

		}
      
       
    }

    void setAvatar(MsgSetAvatar msg, ObjectOutputStream ClientOutput) {
       
        
            try {
                ImageIO.write(msg.getBufferedImage(), "jpg", new File("profileimageserver\\kosnant.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
      
    }

  

}

class ClientHandler implements Runnable {

	private Server server;
	private ClientSocket client;

	public ClientHandler(Server server, ClientSocket client) throws IOException {
		this.server = server;
		this.client = client;

	}

	@Override
	public void run() {
		MsgBase msg;
		Logger logger = new Logger(false);

		while (true) {
			try {
				msg = (MsgBase) client.input.readObject();
				logger.log("got a message: %s", msg.msgType.toString());

				switch (msg.msgType) {
					case REQ_LOGIN:
						server.login((MsgLogin) msg, client.output);
						break;

					case REQ_REGISTER:
						server.register((MsgRegister) msg, client.output);
						break;

					case REQ_SET_USER_STATUS:
						server.setUserStatus((MsgSetUserStatus) msg, client.output);
						break;
                                                
                                        case REQ_CHANGE_USERNAME:
				        server.changeUsername((MsgChangeUsername) msg, client.output);
				        break;
                                        
                                        case REQ_CHANGE_MOBILE:
				        server.changeMobile((MsgChangeMobile) msg, client.output);
				        break;
                                        
                                        case REQ_CHANGE_EMAIL:
				        server.changeEmail((MsgChangeEmail) msg, client.output);
				        break;
                                        

					case REQ_CREATE_SERVER:
						server.createServer((MsgCreateServer) msg, client.output);
						break;

					case REQ_CREATE_CHANNEL:
						server.createChannel((MsgCreateChannel) msg, client.output);
						break;

					case REQ_GET_USERS:
						server.getUsers((MsgGetUsers) msg, client.output);
						break;

					case REQ_DELETE_CHANNEL:
						server.deleteChannel((MsgDeleteChannel) msg, client.output);
						break;

					case REQ_ADD_CHANNEL_USER:
						server.addChannelUser((MsgAddChannelUser) msg, client.output);
						break;

					case REQ_CHANGE_SERVERNAME:
						server.changeServerName((MsgChangeServerName) msg, client.output);
						break;

					case REQ_CHANGE_PASSWORD:
						server.changePassword((MsgChangePassword) msg, client.output);
						break;

					case CHAT_CHANNEL:
						server.broadcastChatChannel((MsgChatChannel) msg, client.output);
						break;

					case CHAT_USER:
						server.broadcastChatUser((MsgChatUser) msg, client.output);
						break;

					case REPLY_USER:
						server.broadcastReplyUser((MsgReplyUser) msg, client.output);
						break;

					case REPLY_CHANNEL:
						server.broadcastReplyChannel((MsgReplyChannel) msg, client.output);
						break;

					case PIN_CHAT:
						server.broadcastPinChat((MsgPinChat) msg, client.output);
						break;
					case REQUEST_FRIENDSHIP:
						server.requestFriendship((MsgRequestFriendship) msg, client.output);
						break;

					case REPLY_FRIENDSHIP:
						server.replyFriendship((MsgReplyFriendship) msg, client.output);
						break;

					case REQ_GET_USER_FRIENDS:
						server.getUserFriends((MsgGetUserFriends) msg, client.output);
						break;

					case REQ_BLOCK_USER:
						server.blockUser((MsgBlockUser) msg, client.output);
						break;

					case REQ_UNBLOCK_USER:
						server.unBlockUser((MsgUnBlockUser) msg, client.output);
						break;

					case HISTORY_CHAT_CHANNEL:
						server.historyChatChannel((MsgHistoryChatChannel) msg, client.output);
						break;

					case HISTORY_CHAT_USER:
						server.historyChatUser((MsgHistoryChatUser) msg, client.output);
						break;
                                                
                                         case REQ_SET_AVATAR:
						server.setAvatar((MsgSetAvatar) msg, client.output);
						break;
                                                
                                         
					default:
						System.out.println("please enter right order");
						break;

				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				break;

			}
		}
		try {
			client.socket.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
