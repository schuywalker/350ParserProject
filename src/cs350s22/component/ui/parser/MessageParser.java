package cs350s22.component.ui.parser;

import cs350s22.message.A_Message;
import cs350s22.message.actuator.MessageActuatorReportPosition;
import cs350s22.message.actuator.MessageActuatorRequestPosition;
import cs350s22.message.ping.MessagePing;
import cs350s22.support.Identifier;

public class MessageParser {
    public static void messageCommand(A_ParserHelper parserHelper, String[] commandText){
        for (int i = 2; i < commandText.length; i++)
        {
            switch (commandText[i])
            {
                case "ping":
                    MessagePing ping = new MessagePing();
                    parserHelper.getCommandLineInterface().issueMessage(ping);
                    break;
                case "id":
                    Identifier id = Identifier.make(commandText[i + 1]);
                    i++;
                    break;
                case "group":
                    // add group
                    break;
                case "position":
                    switch (commandText[i+1])
                    {
                        case "request":
                            // A_Message message = new MessageActuatorRequestPosition(id, i + 2);
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
