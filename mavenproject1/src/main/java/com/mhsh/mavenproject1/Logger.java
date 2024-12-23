package com.mhsh.mavenproject1;

import sockets.message.request.MsgHistoryChatChannel;

/*this class is used to log and print result,Error,Event and...
messages into console and termianl .
 *thid class is a part of termianl UI
 *
 */
public class Logger {
	private boolean saveToDB;

	public Logger(boolean saveToDB) {
		this.saveToDB = saveToDB;
	}

	void log(String msg, Object... args){
		System.out.println(String.format(msg, args));
		if (saveToDB){
			// TODO: save to db

		}
	}

}
