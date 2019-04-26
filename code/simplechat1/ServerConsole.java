import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.ChatClient;
import common.ChatIF;

public class ServerConsole implements ChatIF {
	/**
	 * The instance of the server that created this ConsoleChat.
	 */
	EchoServer server;

	ServerConsole(int port) {
		server = new EchoServer(port);
	}

	public void accept() {
		try {
			server.listen(); // Start listening for connections
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR - Could not listen for clients!");
		}
		try {
			BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
				server.handleMessageFromServerUI(message);

			}

		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	@Override
	public void display(String message) {
		System.out.println("Server> " + message);

	}

	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = 8080; // Set port to 5555
		}
		ServerConsole chat= new ServerConsole(port);
	    chat.accept();

	}
}
