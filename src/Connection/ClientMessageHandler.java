package Connection;

import Common.*;

public class ClientMessageHandler {
    public static Object handle(Object object) {
        Object result = null;
        if(object instanceof MessageType){
            MessageType serverMessage = (MessageType) object;
            if(serverMessage.equals(MessageType.canSignUp))
                result = true;
            else if(serverMessage.equals(MessageType.NoSignUp))
                result = false;
            else if(serverMessage.equals(MessageType.canSignIn))
                result = true;
            else if(serverMessage.equals(MessageType.NoSignIn))
                result = false;
        }
        else if(object instanceof Message){
            Message message = (Message) object;
            result = message.getUser();
        }
        return result;
    }
}
