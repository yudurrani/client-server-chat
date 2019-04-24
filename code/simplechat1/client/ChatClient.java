
package client;

import ocsf.client.*;
import common.*;
import java.io.*;

public class ChatClient extends AbstractClient {

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
	}

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		clientUI.display(msg.toString());
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */
	public void handleMessageFromClientUI(String message) {
		try {
			if (message.charAt(0) == '#') {
				if (message.equals("#quit")) {
					quit();
				} 
				else if (message.equals("#logoff")) {
					closeConnection();
				} 
				else if (message.startsWith("#sethost")) {
					if ( isConnected()) {
						System.out.println("You are now connected, log off to set host.");
						
					}
					else {
						int prefixLength = "#sethost ".length();
						
					}
					
				}
				
			}
				
			sendToServer(message);
		} catch (IOException e) {
			clientUI.display("Could not send message to server.  Terminating client.");
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	protected void connectionClosed() {
		clientUI.display("The server has shutdown");
		System.exit(0);

	}

	protected void connectionException(Exception exception) {
		clientUI.display("There is a server error" + exception.getMessage());
		connectionClosed();
	}
}