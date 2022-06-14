package cs350s22.component.ui.parser;

import java.io.IOException;
import cs350s22.component.logger.LoggerMessage;
import cs350s22.component.logger.LoggerMessageSequencing;
import cs350s22.support.Clock;
import cs350s22.support.Filespec;

public class Meta
{
    public static void metaCommand(final A_ParserHelper parserHelper, final String [] splitCommands) throws ParseException, IOException
    {
      switch(splitCommands[0].toUpperCase())
      {
         case "CLOCK":
         {
            clock(parserHelper, splitCommands);
            
         }
         break;
         case "RUN":
         {
            String fin = splitCommands[1].replaceAll("\"", "");
            parserHelper.run(fin);
            
         }
         break;
         case "CONFIGURE":
         {
            configure(parserHelper, splitCommands);
            
         }
         break;
         case "EXIT":
         {
            parserHelper.exit();
         }
      }
    }
    public static void configure(final A_ParserHelper parserHelper, final String [] splitCommands)throws IOException
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
    public static void clock(final A_ParserHelper parserHelper, final String [] splitCommands)
    {
      switch (splitCommands[1].toUpperCase())
      {
         case "RESUME":
         {
            Clock.getInstance().isActive(true);
            
         }
         break;
         case "PAUSE":
         {
            Clock.getInstance().isActive(false);
            
         }
         break;
         case "ONESTEP":
         {
            Clock.getInstance().onestep(Integer.parseInt(splitCommands[2]));
            
         }
         break;
         case "SET":
         {
            Clock.getInstance().setRate(Integer.parseInt(splitCommands[3]));
            
         }
         break;
      }
    }    
}
