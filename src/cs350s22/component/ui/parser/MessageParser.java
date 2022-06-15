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
        List<Identifier> ids = new ArrayList<>();
        boolean hasGroup = false;
        boolean hasId =  false;
        for (int i = 2; i < commandText.length; i++)
        {
            switch (commandText[i])
            {
                case "ping":
                    MessagePing ping = new MessagePing();
                    parserHelper.getCommandLineInterface().issueMessage(ping);
                    break;
                case "id":
                    hasId = true;
                    ids.add(Identifier.make(commandText[i + 1]));
                    break;
                case "groups":
                    hasGroup = true;
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
                            if (hasId == true && hasGroup == true)
                            {
                                A_Message message1 = new MessageActuatorRequestPosition(groups, Double.valueOf(commandText[i+2]));
                                A_Message message2 = new MessageActuatorRequestPosition(ids, Double.valueOf(commandText[i+2]));
                                cli1.issueMessage(message1);
                                cli1.issueMessage(message2);
                            }
                            else if (hasId == true && hasGroup == false)
                            {
                                A_Message message1 = new MessageActuatorRequestPosition(ids, Double.valueOf(commandText[i+2]));
                                cli1.issueMessage(message1);
                            }
                            else if (hasId == false && hasGroup == true)
                            {
                                A_Message message1 = new MessageActuatorRequestPosition(groups, Double.valueOf(commandText[i+2]));
                                cli1.issueMessage(message1);
                            }


                            break;
                        case "report":
                            CommandLineInterface cli2 = parserHelper.getCommandLineInterface();
                            if (hasId == true && hasGroup == true)
                            {
                                A_Message message1 = new MessageActuatorReportPosition(groups);
                                A_Message message2 = new MessageActuatorReportPosition(ids);
                                cli2.issueMessage(message1);
                                cli2.issueMessage(message2);
                            }
                            else if (hasId == true && hasGroup == false)
                            {
                                A_Message message1 = new MessageActuatorReportPosition(ids);
                                cli2.issueMessage(message1);
                            }
                            else if (hasId == false && hasGroup == true)
                            {
                                A_Message message1 = new MessageActuatorReportPosition(groups);
                                cli2.issueMessage(message1);
                            }
                            break;
                    }
                    break;
            }
        }
    }
}
