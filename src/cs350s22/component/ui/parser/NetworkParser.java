package cs350s22.component.ui.parser;

public class NetworkParser {
    public static void networkCommand(A_ParserHelper parserHelper, String[] commandText){
        for (int i = 2; i < commandText.length; i++)
        {
            switch (commandText[i])
            {
                case "with":
                    switch (commandText[i + 1])
                    {
                        case "component":
                            break;
                        case "components":
                            break;
                    }
            }
        }
    }
}
