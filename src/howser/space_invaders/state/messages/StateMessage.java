package howser.space_invaders.state.messages;

import java.util.ArrayList;


public class StateMessage {

	public String sender;
	public String recipient;
	public ArrayList<Object> message;
	
	public StateMessage(String sender, String recipient, ArrayList<Object> args){
		this.sender = sender;
		this.recipient = recipient;
		message = args;
	}
}
