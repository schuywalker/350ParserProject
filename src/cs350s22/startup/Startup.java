package cs350s22.startup;

import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.component.ui.parser.Parser;
import cs350s22.component.ui.parser.ParserHelper;

import java.util.Locale;

//=================================================================================================================================================================================
public class Startup
{
   private final A_ParserHelper _parserHelper = new ParserHelper();
   
   // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   public Startup()
   {
      System.out.println("Welcome to your Startup class");
   }

   // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(final String[] arguments) throws Exception
   {
      Startup startup = new Startup();
      
      // this command must come first. The filenames do not matter here
      startup.parse("@CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"");

      // testing - written by schuyler. can be deleted
      startup.parse("BUILD NETWORK WITH COMPONENT[S] id+");
      startup.parse("SEND MESSAGE [ids] [groups] POSITION REPORT");
      startup.parse("CREATE MAPPER id INTERPOLATION (LINEAR | SPLINE) DEFINITION string");
      startup.parse("@CLOCK SET RATE value");


      // run your tests like this
      startup.parse("@exit");
   }

   private void parse(final String command) throws Exception {
      Parser parser = new Parser(_parserHelper, command);
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
            System.out.println("clock");
            // send clock class rest of split commands
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


   // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//   private void parse(final String parse) throws Exception
//   {
//      System.out.println("PARSE> "+ parse);
//
//      Parser parser = new Parser(_parserHelper, parse);
//
//      String[] parsedWords = parse.toUpperCase().split(" ");
//
//      if (parsedWords[0].charAt(0) == '@'){
//         // its a meta command. see pages 7 and 8
//         System.out.println(parse);
//         switch (parsedWords[0].substring(1).toLowerCase()) {
//            case "exit":
//               System.exit(0);
//               break;
//            case "clock":
//               // go to clock section
//               break;
//            case "run":
//               // go to run method and pass parsedWords[1]
//            case "configure":
//
//         }
//      }
//      else if (parsedWords[0].equalsIgnoreCase("CREATE")){
//         // create new parser
//         System.out.println(parse);
//      }
//      else if (parsedWords[0].equalsIgnoreCase("SEND")){
//         //  SEND MESSAGE PING
//         //  SEND MESSAGE [ids] [groups] POSITION REQUEST value
//         //  SEND MESSAGE [ids] [groups] POSITION REPORT
//         System.out.println(parse);
//      }
//      else if (parsedWords[0].equalsIgnoreCase("BUILD")){
//         //  F. Network Commands:
//         //  BUILD NETWORK WITH COMPONENT[S] id+
//         System.out.println(parse);
//      }
//      else {
//         throw new IllegalArgumentException("bad parse message");
//      }
//
//      parser.parse();
//   }
}
