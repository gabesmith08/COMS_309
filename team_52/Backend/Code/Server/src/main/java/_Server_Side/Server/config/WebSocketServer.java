package _Server_Side.Server.config;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import _Server_Side.Server.Services.User_Service;

@ServerEndpoint(value = "/websocket/{username}", configurator = CustomSpringConfigurator.class)
@Component
public class WebSocketServer {
	
    private static Map<Session, String> sessionUsernameMap = new HashMap<>();
    private static Map<String, Session> usernameSessionMap = new HashMap<>();
    private static Map<String, Boolean> isblockeduser = new HashMap<>();
    @Autowired
    private User_Service user_Service;
    
    private final Logger logging = LoggerFactory.getLogger(WebSocketServer.class);
    
    @OnOpen
    public void onOpen(
    	      Session session, 
    	      @PathParam("username") String username) throws IOException 
    {
    	logging.info("Entered into Open");
        
        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);
        if (username.equals("fyou")) {
        	isblockeduser.put("fyou",true);
        }
        
        String message="User:" + username + " has Joined the Chat";
        	broadcast(message);
		
    }
 
    @OnMessage
    public void onMessage(Session session, String message) throws IOException 
    {
        // Handle new messages
    	logging.info("Entered into Message: Got Message:"+message);
    	String username = sessionUsernameMap.get(session);
    	if(isblocked(session)) {
    		sendMessageToPArticularUser(username, "[DM] " + username + ": " + "You are blocked from this chat");
    	}
    	else {
    	if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
    	{
    		String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
    		sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
    		sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
    	}
    	else // Message to whole chat
    	{
	    	broadcast(username + ": " + message);
    	}
    	
    	System.out.println(user_Service.getAllUsers());
    	boolean status = user_Service.save_data(message);
    }
    }
    @OnClose
    public void onClose(Session session) throws IOException
    {
    	logging.info("Entered into Close");
    	
    	String username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);
        
    	String message= username + " disconnected";
        broadcast(message);
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
 
    	logging.info("Entered into Error");
    }
    
	private void sendMessageToPArticularUser(String username, String message) 
    {	
    	try {
    		usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logging.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }
	
	private  boolean  isblocked(Session session) {
		String username = sessionUsernameMap.get(session);
		if(isblockeduser.containsKey(username)&& isblockeduser.get(username)==true)
			return true;
		return false;
		
	}
    
    private static void broadcast(String message) 
    	      throws IOException 
    {	  
    	sessionUsernameMap.forEach((session, username) -> {
    		synchronized (session) {
	            try {
	                session.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
    		
	    });
	}
}

