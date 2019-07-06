package Connection;

import Common.*;

import static Model.Main.WAIT;

public class ClientMessageHandler {
    public static Object handle(Object object) {

        Object result = null;
        if (object instanceof MessageType) {
            MessageType serverMessage = (MessageType) object;
            if (serverMessage.equals(MessageType.canSignUp)) {
                result = true;
                System.out.println("can sign up");
            } else if (serverMessage.equals(MessageType.NoSignUp)) {
                result = false;
                System.out.println("no sign up");
            } else if (serverMessage.equals(MessageType.canSignIn)) {
                result = true;
                System.out.println("can sign up");
            } else if (serverMessage.equals(MessageType.NoSignIn)) {
                result = false;
                System.out.println("no sign in");
            }
        } else if (object instanceof Message) {
            Message message = (Message) object;
            if(message.getMessageType().equals(MessageType.canSignIn)){
                result=message.getUser();
            }
            if(message.getMessageType().equals(MessageType.Inbox)){
                result=message.getMailArrayList();
            }
            if(message.getMessageType().equals(MessageType.receiveMails)){
                result=message.getMailArrayList();
            } if(message.getMessageType().equals(MessageType.Sent)){
                result=message.getMailArrayList();
            } if(message.getMessageType().equals(MessageType.Refresh)){
                result=message.getMailArrayList();
            }


        }else if(object instanceof User){
            result=(User) object;
        }

        System.out.println("result in handler : "+result);
        return result;
    }
}
