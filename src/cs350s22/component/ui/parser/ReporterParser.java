package cs350s22.component.ui.parser;

import cs350s22.support.Identifier;
import cs350s22.component.sensor.reporter.*;
import java.util.*;
public class ReporterParser {
    public static void reporterCommand(A_ParserHelper parserHelper, String[] commandText)
    {
         List <Identifier> idList = new ArrayList<Identifier>(0);
         List <Identifier> groupList = new ArrayList<Identifier>(0);
         String [] ids = null;
         String [] groups = null;
         A_Reporter report;
         int cur = 5;
         if(commandText[5].equalsIgnoreCase("ids") || commandText[5].equalsIgnoreCase("id"))
         {
            int startIndex = cur;
            while(!((cur >= commandText.length) ||(commandText[cur].equalsIgnoreCase("group")) || (commandText[cur].equalsIgnoreCase("groups")) || (commandText[cur].equalsIgnoreCase("delta")) || (commandText[cur].equalsIgnoreCase("frequency"))))
            {
               cur++;
            }               
            ids = Arrays.copyOfRange(commandText, startIndex, cur);
         }
         if(commandText[cur].equalsIgnoreCase("groups") || commandText[cur].equalsIgnoreCase("group"))
         {
            groups = Arrays.copyOfRange(commandText, cur, commandText.length - 2);
         }   
         if(ids != null)
         {
            idList = Identifier.makeList(ids);
         }
         if(groups != null)
         {
            groupList = Identifier.makeList(groups);
         }
         if(groups == null && ids == null)
         {
            throw new RuntimeException("both groups, and ids are null in reporter class");
         }
         if(commandText[2].equalsIgnoreCase("change"))
         {            
              report = new ReporterChange(idList, groupList, Integer.parseInt(commandText[commandText.length - 1]));
         }
         else
            report = new ReporterFrequency(idList, groupList, Integer.parseInt(commandText[commandText.length - 1]));
            
         parserHelper.getSymbolTableReporter().add(Identifier.make(commandText[3]), report);
      }
    
}
