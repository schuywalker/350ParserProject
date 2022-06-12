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

import java.io.IOException;

public class MapperParser {
    public static void mapperCommand(A_ParserHelper parserHelper, String[] commandText) throws IOException {
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
                    MapLoader loader;
                    Filespec file = new Filespec(commandText[6].replaceAll("\"", ""));
                    loader = new MapLoader(file);
                    A_Interpolator interpolator = null;
                    InterpolationMap map = loader.load();

                    if (commandText[i + 1].equals("linear"))
                    {
                        interpolator = new InterpolatorLinear(map);
                        i++;
                    }
                    else if (commandText[i + 1].equals("spline"))
                    {
                        interpolator = new InterpolatorSpline(map);
                        i++;
                    }
                    mapper = new MapperInterpolation(interpolator);
                    break;
                case "definition":

                    break;
            }
        }
        parserHelper.getSymbolTableMapper().add(mapperId, mapper);
    }
}
