package cs350s22.component.ui.parser;

import cs350s22.message.ping.MessagePing;

public class MessageParser {
    public static void messageCommand(A_ParserHelper parserHelper, String[] commandText){
        for (int i = 2; i < commandText.length; i++)
        {
            switch (commandText[i])
            {
                case "ping":
                    MessagePing ping = new MessagePing();
                    break;
                case "id":
                    // add id
                    break;
                case "group":
                    // add group
                    break;
                case "position":
                    switch (commandText[i+1])
                    {
                        case "request":
                            // do position request with value
                            break;
                        case "report":
                            // do position report operation
                            break;
                    }
                    break;
            }
        }
    }
}
