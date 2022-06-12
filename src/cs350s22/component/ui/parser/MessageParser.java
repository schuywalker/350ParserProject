package cs350s22.component.ui.parser;

import cs350s22.component.ui.CommandLineInterface;
import cs350s22.message.A_Message;
import cs350s22.message.actuator.MessageActuatorReportPosition;
import cs350s22.message.actuator.MessageActuatorRequestPosition;
import cs350s22.message.ping.MessagePing;
import cs350s22.support.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MessageParser {
    public static void messageCommand(A_ParserHelper parserHelper, String[] commandText){
        List<Identifier> groups = new ArrayList<>();
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
                    break;
                case "groups":
                    int count = 0;
                    while (!commandText[i].equals("position"))
                    {
                        count++;
                        i++;
                    }
                    for (i = 5; i< count+4; i++)
                    {
                        groups.add(Identifier.make(commandText[i]));
                    }
                    i =5;
                    break;
                case "position":
                    switch (commandText[i+1])
                    {
                        case "request":
                            CommandLineInterface cli1 = parserHelper.getCommandLineInterface();
                            A_Message message1 = new MessageActuatorRequestPosition(groups, Integer.valueOf(commandText[i+2]));
                            cli1.issueMessage(message1);
                            break;
                        case "report":
                            CommandLineInterface cli2 = parserHelper.getCommandLineInterface();
                            A_Message message2 = new MessageActuatorReportPosition(groups, Integer.valueOf(commandText[i+2]));
                            cli2.issueMessage(message2);
                            break;
                    }
                    break;
            }
        }
    }
}
