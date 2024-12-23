package com.mhsh.mavenproject1;


/*this DATABASE class is used to create and hold tables including
rows and colums to hold program information as String or Integer
relted to user , channels , servers etc.
*SQLite database is used 
 * @author MohammadHosein Shahbazi
 * @since 2022-06-12
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sockets.message.model.Chat;
import sockets.message.model.User;

public class Database {
    
	Connection conn;
	java.sql.Statement cur;

	public Database() throws SQLException, ClassNotFoundException {
		conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite3");
		conn.setAutoCommit(true);
		cur = conn.createStatement();

	}

	public void open() throws SQLException {
		cur.executeUpdate("PRAGMA foreign_keys = ON");
		
		cur.executeUpdate(
			"CREATE TABLE IF NOT EXISTS users (username TEXT NOT NULL PRIMARY KEY, firstname TEXT, lastname TEXT, passwd TEXT NOT NULL UNIQUE, email TEXT NOT NULL UNIQUE, mobile TEXT UNIQUE, status TEXT DEFAULT 'idle')");
		cur.executeUpdate(
				"CREATE TABLE IF NOT EXISTS servers (username TEXT NOT NULL, servername TEXT NOT NULL, PRIMARY KEY (username, servername) FOREIGN KEY (username) REFERENCES users(username))");
		cur.executeUpdate(
				"CREATE TABLE IF NOT EXISTS channels (servername TEXT NOT NULL, channelname TEXT NOT NULL, PRIMARY KEY (servername, channelname), FOREIGN KEY (servername) REFERENCES servers(servername))");
		cur.executeUpdate(
				"CREATE TABLE IF NOT EXISTS channel_users (servername TEXT NOT NULL, channelname TEXT NOT NULL, username TEXT NOT NULL, PRIMARY KEY (servername, channelname, username), FOREIGN KEY (username, servername) REFERENCES servers(username, servername), FOREIGN KEY (servername, channelname) REFERENCES channels(servername, channelname), FOREIGN KEY (username) REFERENCES users(username))");
		cur.executeUpdate(
				"CREATE TABLE IF NOT EXISTS chats (id INTEGER PRIMARY KEY AUTOINCREMENT, sender TEXT NOT NULL, servername TEXT, channelname TEXT, receiver TEXT, body TEXT, ref_id INT, pinned BOOL default false, timestamp DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (servername, channelname) REFERENCES channels(servername, channelname), FOREIGN KEY (sender) REFERENCES users(username), FOREIGN KEY (receiver) REFERENCES users(username), FOREIGN KEY (ref_id) REFERENCES chats(id))");
		cur.executeUpdate(
				"CREATE TABLE IF NOT EXISTS friends (username TEXT NOT NULL, friendname TEXT NOT NULL, status TEXT NOT NULL default 'pending', timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (username, friendname), FOREIGN KEY (username) REFERENCES users(username), FOREIGN KEY (friendname) REFERENCES users(username))");
		cur.executeUpdate(
				"CREATE TABLE IF NOT EXISTS blacklist (username TEXT NOT NULL, busername TEXT NOT NULL, timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (username, busername), FOREIGN KEY (username) REFERENCES users(username), FOREIGN KEY (busername) REFERENCES users(username))");

	}

	public void close() throws SQLException {
		conn.close();

	}

	public PreparedStatement prepareStatement(String statement, Object... args) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(statement);
		Object arg;
		for (int i = 1; i <= args.length; i++) {
			arg = args[i - 1];

			if (arg instanceof Integer) {
				stmt.setInt(i, (Integer) arg);

			} else if (arg instanceof Boolean) {
				stmt.setBoolean(i, (Boolean) arg);

			} else {
				stmt.setString(i, arg.toString());

			}
		}
		return stmt;

	}

	public void executeUpdate(String statement, Object... args) throws SQLException {
		prepareStatement(statement, args).executeUpdate();

	}

	public ResultSet executeQuery(String statement, Object... args) throws SQLException {
		return prepareStatement(statement, args).executeQuery();

	}

	public void createUser(String username, String passwd, String email, String mobile) throws SQLException {
		executeUpdate("INSERT INTO users (username, passwd, email, mobile) VALUES (?, ?, ?, ?)", username, passwd, email,
				mobile);

	}

	public String login(String passwd, String mobileOrEmail) throws SQLException { // was changed
		ResultSet rs = executeQuery("SELECT username FROM users WHERE passwd = ? AND ? in (mobile, email)", passwd , mobileOrEmail);
                if (rs.next()){
                    return rs.getString(1);
                }
                return null;

	}
       

	public ArrayList<String> getUserChannels(String username) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		ResultSet rs = executeQuery("SELECT servername || ':' || channelname FROM channel_users WHERE username = ?",
				username);

		while (rs.next()) {
			result.add(rs.getString(1));

		}
		return result;

	}

	public void setUserStatus(String username, String status) throws SQLException {//was changed
		executeUpdate("UPDATE users SET status = ? WHERE username = ?", status, username);

	}

	public void createServer(String username, String servername) throws SQLException {
		executeUpdate("INSERT INTO servers (username, servername) VALUES (?, ?)", username, servername);

	}

	public void createChannel(String servername, String channelname) throws SQLException {
		executeUpdate("INSERT INTO channels (servername, channelname) VALUES (?, ?)", servername, channelname);
		executeUpdate(
				"INSERT INTO channel_users (servername, channelname, username) SELECT s.servername, ?, s.username from servers s where s.servername = ?",
				channelname, servername);

	}

	public void deleteChannel(String servername, String channelname) throws SQLException {
		executeUpdate("DELETE FROM channels WHERE servername = ? AND channelname = ?", servername, channelname);

	}

	public void deleteServer(String servername, String username) throws SQLException {
		executeUpdate("DELETE FROM servers WHERE servername = ? AND username = ?", servername, username);

	}

	public void changePassword(String username, String password_old, String password_new) throws SQLException {
		executeUpdate("UPDATE users SET passwd = ? WHERE username = ? and passwd = ?", password_new, username,
				password_old);

	}
        
        
	public void changeUsername(String username_old ,String username_new, String password) throws SQLException {
		executeUpdate("UPDATE users SET username = ? WHERE passwd = ? and username = ?", username_new, password , username_old);
				

	}
        
           public void changeEmail(String email_new, String username) throws SQLException {
		executeUpdate("UPDATE users SET email = ? WHERE username = ?", email_new, username);
				

	}
        
        public void changeMobile(String mobile_new, String username) throws SQLException {
		executeUpdate("UPDATE users SET mobile = ? WHERE username = ?", mobile_new, username);
				

	}
        
        
        
        

	public ArrayList<String> getUsers(String servername, String channelname) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		ResultSet rs;

		if (servername == null || channelname == null) {
			rs = executeQuery("SELECT username FROM users");

		} else {
			rs = executeQuery("SELECT username FROM channel_users WHERE servername = ? AND channelname = ?", servername,
					channelname);

		}
		while (rs.next()) {
			result.add(rs.getString(1));

		}
		return result;

	}

	public void addChannelUser(String servername, String channelname, String username) throws SQLException {
		executeUpdate("INSERT INTO channel_users (servername, channelname, username) VALUES (?, ?, ?)", servername,
				channelname, username);

	}

	public void changeServername(String username, String servername_old, String servername_new) throws SQLException {
		executeUpdate("UPDATE servers SET servername = ? WHERE username = ? AND servername = ?", servername_new, username,
				servername_old);

	}

	public void createChat(String sender, String servername, String channelname, String body) throws SQLException {
		executeUpdate("INSERT INTO chats (sender, servername, channelname, body) VALUES (?, ?, ?, ?)", sender, servername,
				channelname, body);

	}

	public void createChat(String sender, String receiver, String body) throws SQLException {
		executeUpdate("INSERT INTO chats (sender, receiver, body) VALUES (?, ?, ?)", sender, receiver, body);

	}

	public void createReply(String sender, String servername, String channelname, Integer ref_id, String body)
			throws SQLException {
		executeUpdate("INSERT INTO chats (sender, servername, channelname, ref_id, body) VALUES (?, ?, ?, ?, ?)", sender,
				servername, channelname, ref_id, body);

	}

	public void createReply(String sender, String receiver, Integer ref_id, String body) throws SQLException {
		executeUpdate("INSERT INTO chats (sender, receiver, ref_id, body) VALUES (?, ?, ?, ?)", sender, receiver, ref_id,
				body);

	}
        
        
   

	public Chat pinChat(Integer id, Boolean pinned) throws SQLException {
		executeUpdate("UPDATE chats SET pinned = ? WHERE id = ?", pinned, id);
		return getChat(id);
	}

	public String requestFriendship(String username, String friendname) throws SQLException {
            executeUpdate("INSERT INTO friends (username, friendname) VALUES (?, ?)", username,	friendname);	
            ResultSet rs = executeQuery("SELECT status FROM friends WHERE username = ? and friendname = ?", username, friendname);
            if (rs.next()) {
                    return rs.getString(1);
            }
            return null;

	}

	public String replyFriendship(String username, String requester, String status) throws SQLException {
            executeUpdate("UPDATE friends SET status = ? WHERE username = ? and friendname = ?", status, requester, username);	
            ResultSet rs = executeQuery("SELECT status FROM friends WHERE username = ? and friendname = ?", requester, username);
            if (rs.next()) {
                    return rs.getString(1);
            }
            return null;
	}

	public ArrayList<User> getUserFriends(String username) throws SQLException {

		ArrayList<User> result = new ArrayList<User>();
		ResultSet rs = executeQuery(
				"SELECT f.friendname, u.status FROM friends f JOIN users u ON u.username = f.friendname WHERE f.username = ?",
				username);
		while (rs.next()) {
			User user = new User(rs.getString(1));
			user.setStatus(rs.getString(2));
			result.add(user);
		}
		return result;

	}

	public void blockUser(String username, String busername) throws SQLException {
		executeUpdate("INSERT INTO blacklist (username, busername) VALUES (?, ?)", username, busername);

	}

	public void unblockUser(String username, String busername) throws SQLException {
		executeUpdate("DELETE FROM blacklist WHERE username = ? AND busername = ?", username, busername);

	}

	public ArrayList<Chat> getChats(String sender) throws SQLException {
		ArrayList<Chat> result = new ArrayList<Chat>();
		ResultSet rs = executeQuery(
				"SELECT id, sender, servername, channelname, receiver, body, ref_id, pinned FROM chats WHERE sender = ? AND servername IS NULL AND channelname IS NULL",
				sender);
		while (rs.next()) {
			result.add(new Chat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7), rs.getBoolean(8)));
		}
		return result;
	}

	public ArrayList<Chat> getChats(String servername, String channelname) throws SQLException {
		ArrayList<Chat> result = new ArrayList<Chat>();
		ResultSet rs = executeQuery(
				"SELECT id, sender, servername, channelname, receiver, body, ref_id, pinned FROM chats WHERE servername = ? AND channelname = ?",
				servername, channelname);
		while (rs.next()) {
			result.add(new Chat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7), rs.getBoolean(8)));
		}
		return result;
	}

	public Chat getChat(int id) throws SQLException {
		ResultSet rs = executeQuery(
				"SELECT id, sender, servername, channelname, receiver, body, ref_id, pinned FROM chats WHERE id = ?", id);
		if (rs.next()) {
			return new Chat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
					rs.getInt(7), rs.getBoolean(8));
		}
		return null;
	}

	public ArrayList<String> getUserBlacklist(String username) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		ResultSet rs = executeQuery("SELECT busername FROM blacklist WHERE username = ?", username);
		while (rs.next()) {
			result.add(rs.getString(1));
		}
		return result;

	}

	public String getServerOwner(String servername) throws SQLException {
		ResultSet rs = executeQuery("SELECT username FROM servers WHERE servername = ?", servername);
		if (rs.next()) {
			return rs.getString(1);
		}
			return null;
	}

}
