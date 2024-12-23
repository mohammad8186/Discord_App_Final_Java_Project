package sockets.message;

public enum MessageType {
	REQ_LOGIN, REQ_LOGOUT, REQ_REGISTER, REQ_CHAT, REQ_SET_USER_STATUS, REQ_CREATE_SERVER,
	REQ_CREATE_CHANNEL, REQ_GET_USERS,
	REQ_ADD_CHANNEL_USER, REQ_DELETE_CHANNEL, REQ_ADD_USER_TO_CHANNEL, REQ_CHANGE_SERVERNAME, REQ_CHANGE_PASSWORD,
	REQ_GET_USER_FRIENDS, REQ_BLOCK_USER, REQ_UNBLOCK_USER,
	REQ_HISTORY_CHAT_CHANNEL,REQ_CHANGE_USERNAME,REQ_CHANGE_MOBILE, REQ_CHANGE_EMAIL,REQ_SET_AVATAR,REQ_GET_USER_INFORMATION,

	CHAT_CHANNEL, CHAT_USER, REPLY_CHANNEL, REPLY_USER, PIN_CHAT, UNPIN_CHAT, REQUEST_FRIENDSHIP, REPLY_FRIENDSHIP,
	PIN_CHAT_FAIL, HISTORY_CHAT_USER, HISTORY_CHAT_CHANNEL,

	RES_LOGIN_SUCCESS, RES_LOGIN_FAIL, RES_REGISTER_SUCCESS, RES_REGISTER_FAIL,
	RES_LOGOUT_FAIL, RES_LOGOUT_SUCCESS, RES_CREATE_SERVER_FAIL, RES_CREATE_SERVER_SUCCESS,
	RES_CREATE_CHANNEL_FAIL, RES_CREATE_CHANNEL_SUCCESS, RES_GET_USER_LIST_SUCCESS,
	RES_GET_USERS_FAIL, RES_GET_USERS, RES_GET_USER_LIST_FROM_CHANNEL_OR_TOTAL_FAIL, RES_ADD_USER_TO_CHANNEL_SUCCESS,
	RES_ADD_USER_TO_CHANNEL_FAIL, RES_DELETE_CHANNEL_FAIL,
	RES_CHANGE_SERVERNAME_FAIL, RES_DELETE_CHANNEL_SUCCESS, RES_CHANGE_SERVERNAME_SUCCESS, RES_SET_USER_STATUS_SUCCESS,
	RES_SET_USER_STATUS_FAIL, RES_CHAT_CHANNEL_FAIL,
	RES_USERS, RES_CHAT_USER_FAIL, RES_CHANGE_PASSWORD_FAIL, RES_CHANGE_PASSWORD_SUCCESS, RES_FRIENDSHIP_SUCCESS,
	RES_FRIENDSHIP_FAIL, RES_GET_FRIENDS_LIST_FAIL, RES_USER_FRIENDS,
	RES_REPLY_CHAT_FAIL, RES_WELLCOME_ADD_CHANNEL_USER, RES_PINNED_SUCCESS, RES_PINNED_FAIL, RES_HISTORY_CHAT_USER_FAIL,
	RES_HISTORY_CHAT_CHANNEL_FAIL , RES_CHANGE_MOBILE_SUCCESS,RES_CHANGE_MOBILE_FAIL,RES_CHANGE_EMAIL_SUCCESS,RES_CHANGE_EMAIL_FAIL,RES_CHANGE_USERNAME_SUCCESS,
        RES_CHANGE_USERNAME_FAIL,RES_GET_USER_INFORMATION_SUCCESS

}