package cs350s22.component.ui.parser;

import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.mapper.MapperEquation;
import cs350s22.component.sensor.mapper.MapperInterpolation;
import cs350s22.component.sensor.mapper.function.equation.A_Equation;
import cs350s22.component.sensor.mapper.function.equation.EquationNormalized;
import cs350s22.component.sensor.mapper.function.equation.EquationPassthrough;
import cs350s22.component.sensor.mapper.function.equation.EquationScaled;
import cs350s22.component.sensor.mapper.function.interpolator.A_Interpolator;
import cs350s22.component.sensor.mapper.function.interpolator.InterpolationMap;
import cs350s22.component.sensor.mapper.function.interpolator.InterpolatorLinear;
import cs350s22.component.sensor.mapper.function.interpolator.InterpolatorSpline;
import cs350s22.component.sensor.mapper.function.interpolator.loader.A_MapLoader;
import cs350s22.component.sensor.mapper.function.interpolator.loader.MapLoader;
import cs350s22.support.Filespec;
import cs350s22.support.Identifier;

public class MapperParser {
    public static void mapperCommand(A_ParserHelper parserHelper, String[] commandText){
        A_Mapper mapper = null;
        Identifier mapperId = Identifier.make(commandText[2]);
        for (int i = 3; i < commandText.length; i++)
        {
            switch (commandText[i])
            {
                case "equation":
                    A_Equation equation = null;
                    switch (commandText[4])
                    {
                        case "passthrough":
                            equation = new EquationPassthrough();
                            break;
                        case "scale":
                            equation = new EquationScaled(Double.valueOf(commandText[5]));
                            break;
                        case "normalize":
                            equation = new EquationNormalized(Double.valueOf(commandText[5]),Double.valueOf(commandText[6]));
                            break;
                    }
                    mapper = new MapperEquation(equation);
                    break;
                case "interpolation":
                    A_Interpolator interpolator = null;
                    InterpolationMap map = new InterpolationMap();

                    if (commandText[i + 1] == "linear")
                    {
                        interpolator = new InterpolatorLinear(map);

                        i++;
                    }
                    else if (commandText[i + 1] == "spline")
                    {
                        map = new InterpolationMap();
                        InterpolatorSpline spline = new InterpolatorSpline(map);
                        i++;
                    }
                    mapper = new MapperInterpolation(interpolator);
                    break;
                case "definition":
                    A_MapLoader loader;
                    Filespec file = new Filespec(commandText[i + 1]);
                    loader = new MapLoader(file);
                    break;
                default:
                    throw new RuntimeException("command incorrect");

            }
        }
        parserHelper.getSymbolTableMapper().add(mapperId, mapper);
    }
}
