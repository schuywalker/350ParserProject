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

   private void parse(final String parse) throws Exception {
      System.out.println("PARSE> "+ parse);

      Parser parser = new Parser(_parserHelper, parse);

      parser.parse();
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
