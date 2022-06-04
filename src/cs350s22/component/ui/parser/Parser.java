package cs350s22.component.ui.parser;

import cs350s22.component.actuator.*;
import cs350s22.support.*;

import java.io.IOException;


public class Parser {

    String command;
    A_ParserHelper parserHelper;
    public Parser(A_ParserHelper parserHelper, String commandText){
        this.command = commandText;
        this.parserHelper = parserHelper;
    }
    public void parse() throws IOException {

        String[] splitCommands = command.toUpperCase().split(" ");
        boolean metaCommand = false;
        if(splitCommands[0].charAt(0) == '@')
        {
            metaCommand = true;
            splitCommands[0] = splitCommands[0].substring(1).toLowerCase();
        }

        switch (splitCommands[0])
        {
            case "exit":
                System.exit(0);
                break;
            case "clock":
                if(splitCommands.length == 1)
                {
                    // print current time


                }
                else
                {
                    for(int i = 1; i < splitCommands.length; i++)
                    {
                        switch (splitCommands[i])
                        {
                            case "pause":
                                // call pause method in clock
                                break;
                            case "resume":
                                // call resume method in clock
                                break;
                            case "onstep":
                                // call onstep method and pass next substring
                                i++;
                                break;
                            case "set":
                                i++;
                                //call set rate and pass next value;
                                i++;
                                break;
                            case "wait":
                                if(splitCommands[i + 1] == "for")
                                {
                                    i +=2;
                                    // call method and pass in command
                                }
                                if(splitCommands[i + 1] == "until")
                                {
                                    i+=2;
                                    // call method and pass in command
                                }
                        }
                    }
                }
                break;
            case "run":
                System.out.println("run");
                // go to run method and pass rest of split commands
                break;
            case "configure":
                System.out.println("configure");
                for (int i = 1; i < splitCommands.length; i++)
                {
                    switch (splitCommands[i].toLowerCase())
                    {
                        case "log":
                            System.out.println("log");
                            // go to log method and pass next string
                            i++;
                            break;
                        case "dot":
                            System.out.println("dot");
                            // go to dot sequence and pass next string
                            i += 2;
                            break;
                        case "network":
                            System.out.println("network");
                            // pass network next string
                            i++;
                            break;
                        case "xml":
                            System.out.println("xml");
                            // pass xml method next string
                            i++;
                    }
                }
        }
    }

}
