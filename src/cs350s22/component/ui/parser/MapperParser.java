package cs350s22.component.ui.parser;

import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.support.Identifier;

public class MapperParser {
    public static void mapperCommand(A_ParserHelper parserHelper, String[] commandText){
        Identifier mapperId = Identifier.make(commandText[2]);
        for (int i = 3; i < commandText.length; i++)
        {
            switch (commandText[i])
            {
                case "equation":
                    switch (commandText[4])
                    {
                        case "passthrough":
                            // do something
                            break;
                        case "scale":
                            // do something
                            break;
                        case "normalize":
                            // do something
                            break;
                    }
                    break;
                case "interpolation":
                    if (commandText[i + 1] == "linear")
                    {
                        // do something
                        i++;
                    }
                    else if (commandText[i+1] == "spline")
                    {
                        // do something
                        i++;
                    }
                    break;
                case "definition":
                    // do something
                    break;
            }
        }
    }
}
