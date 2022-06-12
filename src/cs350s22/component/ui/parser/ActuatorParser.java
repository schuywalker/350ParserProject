package cs350s22.component.ui.parser;

import cs350s22.component.actuator.A_Actuator;
import cs350s22.component.actuator.ActuatorLinear;
import cs350s22.component.actuator.ActuatorRotary;
import cs350s22.component.actuator.state.A_State;
import cs350s22.component.sensor.A_Sensor;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ActuatorParser {
    public static void actuatorCommand(A_ParserHelper parserHelper, String[] commandText) {
        A_Actuator actuator;
        ActuatorPrototype actuatorPrototype;
        Identifier id = Identifier.make(commandText[3]);
        double accelerationLeadin = 0.0;
        double accelerationLeadout = 0.0;
        double accelerationRelax = 0.0;
        double velocityLimit = 0.0;
        double valueInitial = 0.0;
        double valueMin = 0.0;
        double valueMax = 0.0;
        double inflectionJerkThreshold = 0.0;
        List<Identifier> groups = new ArrayList<Identifier>();
        List<Identifier> sensorId = new ArrayList<Identifier>();
        for (int i = 2; i < commandText.length; i++)
        {
            switch(commandText[i])
            {
                // actuatorPrototype
                case "linear":

                    if (!commandText[4].equals("sensors") && !commandText[4].equals("acceleration"))
                    {
                        groups.add(Identifier.make(commandText[4]));
                        if (commandText[5].equals("sensors"))
                        {
                            int count = 0;
                            i = 6;
                            while (!commandText[i].equals("acceleration"))
                            {
                                count++;
                                i++;
                            }
                            for (i = 6; i< count+6; i++)
                            {
                                sensorId.add(Identifier.make(commandText[i]));
                            }
                            i = 6;

                        }
                    }
                    else if (commandText[4].equals("sensors"))
                    {
                        i = 5;
                        int count = 0;
                        while (!commandText[i].equals("acceleration"))
                        {
                            count++;
                            i++;
                        }
                        for (i = 5; i< count+5; i++)
                        {
                            sensorId.add(Identifier.make(commandText[i]));
                        }
                        i = 5;
                    }
                    ActuatorLinear linearActuator = new ActuatorLinear(id);
                    break;
                case "rotary":

                    if (!commandText[4].equals("sensor") && !commandText[4].equals("acceleration"))
                    {
                        groups.add(Identifier.make(commandText[4]));
                        if (commandText[5].equals("sensors"))
                        {
                            i = 6;
                            int count = 0;
                            while (!commandText[i].equals("acceleration"))
                            {
                                count++;
                                i++;
                            }
                            for (i = 6; i< count+6; i++)
                            {
                                sensorId.add(Identifier.make(commandText[i]));
                            }
                            i = 6;
                        }
                    }
                    else if (commandText[4].equals("sensor"))
                    {
                        i = 5;
                        int count = 0;
                        while (!commandText[i].equals("acceleration"))
                        {
                            count++;
                            i++;
                        }
                        for (i = 5; i< count+5; i++)
                        {
                            sensorId.add(Identifier.make(commandText[i]));
                        }
                        i = 5;
                    }
                    ActuatorRotary rotaryActuator = new ActuatorRotary(id);
                    break;
                case "acceleration":
                    switch (commandText[i + 1])
                    {
                        case "leadin":
                            accelerationLeadin = Double.valueOf(commandText[i + 2]);
                            accelerationLeadout = Double.valueOf(commandText[i + 4]);
                            break;
                        case "leadout":
                            accelerationLeadout = Double.valueOf(commandText[i + 2]);
                            accelerationLeadin = Double.valueOf(commandText[i + 4]);
                            break;
                    }
                    break;
                case "relax":
                    accelerationRelax = Double.valueOf(commandText[i + 1]);
                    break;
                case "velocity":
                    velocityLimit = Double.valueOf(commandText[i + 2]);
                    break;
                case "value":
                    switch (commandText[i + 1])
                    {
                        case "min":
                            valueMin = Double.valueOf(commandText[i + 2]);
                            valueMax = Double.valueOf(commandText[i + 4]);
                            break;
                        case "max":
                            valueMax = Double.valueOf(commandText[i + 2]);
                            valueMin = Double.valueOf(commandText[i + 4]);
                            break;
                    }
                   break;
                case "initial":
                    valueInitial = Double.valueOf(commandText[i + 1]);
                    break;
                case "jerk":
                    inflectionJerkThreshold = Double.valueOf(commandText[i + 2]);
                    break;
            }
        }


        List<A_Sensor> sensors =  parserHelper.getSymbolTableSensor().get(sensorId, true);
        actuatorPrototype = new ActuatorPrototype(id, groups, accelerationLeadin, accelerationLeadout, accelerationRelax, velocityLimit,
                valueInitial, valueMin, valueMax, inflectionJerkThreshold, sensors);
        actuator = actuatorPrototype;
        parserHelper.getSymbolTableActuator().add(id,actuator);

    }
}
