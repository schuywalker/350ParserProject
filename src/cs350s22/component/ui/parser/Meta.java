package cs350s22.component.ui.parser;

import java.io.IOException;
import cs350s22.component.logger.LoggerMessage;
import cs350s22.component.logger.LoggerMessageSequencing;
import cs350s22.support.Filespec;

public class Meta {
    public Meta(A_ParserHelper parserHelper, String commandText)
    {
      
    }
    public void configure(String [] splitCommands)
    {
      String log="";
      String dot="";
      String network="";
      String xml="";
      for (int i = 1; i < splitCommands.length; i++)
      {
         switch (splitCommands[i].toLowerCase())
         {
            case "log":
               System.out.println("log");               
               i++;
               log = splitCommands[i].replaceAll("\"", "");
               break;
            case "dot":
               System.out.println("dot");
               i += 2;
               dot = splitCommands[i].replaceAll("\"", "");
               break;
            case "network":
               System.out.println("network");
               i++;
               network = splitCommands[i].replaceAll("\"", "");
               break;
            case "xml":
               System.out.println("xml");
               i++;
               xml = splitCommands[i].replaceAll("\"", "");
               break;            
         }
      }
      LoggerMessage.initialize(Filespec.make(log));
      LoggerMessageSequencing.initialize(Filespec.make(dot), Filespec.make(network));
    }
    public void clock(String state)
    {
         Clock.getInstance().isActive(state.equalsIgnoreCase("resume"));
    }
    
}
