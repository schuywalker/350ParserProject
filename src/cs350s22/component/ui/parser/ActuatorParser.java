package cs350s22.component.ui.parser;

import cs350s22.component.actuator.A_Actuator;
import cs350s22.component.actuator.ActuatorLinear;
import cs350s22.component.actuator.ActuatorRotary;
import cs350s22.component.actuator.state.A_State;
import cs350s22.component.sensor.A_Sensor;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;

import javax.swing.*;

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

        for (int i = 2; i < commandText.length; i++)
        {
            switch(commandText[i])
            {
                // actuatorPrototype
                case "linear":

                    if (!commandText[4].equals("sensor") && !commandText[4].equals("acceleration"))
                    {
                        // GroupLayout.Group (add group)
                        //ActuatorLinear linearActuator = new ActuatorLinear(id, commandText[4]); (create linear actuator with group)
                        i = 4;
                    }
                    else if (commandText[4] == "sensor")
                    {
                        // A_Sensor sensor = new A_Sensor(); (creating a sensor)
                        i = 4;
                    }
                    else
                    {
                        i = 3;
                    }
                    ActuatorLinear linearActuator = new ActuatorLinear(id);
                    break;
                case "rotary":

                    if (!commandText[4].equals("sensor") && !commandText[4].equals("acceleration"))
                    {
                        // GroupLayout.Group (add group)
                        //ActuatorLinear linearActuator = new ActuatorLinear(id, commandText[4]); (create linear actuator with group)
                        i = 4;
                    }
                    else if (commandText[4] == "sensor")
                    {
                        // A_Sensor sensor = new A_Sensor(); (creating a sensor)
                        i = 4;
                    }
                    else
                    {
                        i = 3;
                    }
                    ActuatorRotary rotaryActuator = new ActuatorRotary(id);
                    break;
                case "acceleration":
                    switch (commandText[i + 1])
                    {
                        case "leadin":
                            accelerationLeadin = Double.valueOf(commandText[i + 2]);
                            accelerationLeadout = Double.valueOf(commandText[i + 4]);
                            i += 4;
                        case "leadout":
                            accelerationLeadout = Double.valueOf(commandText[i + 2]);
                            accelerationLeadin = Double.valueOf(commandText[i + 4]);
                            i += 4;
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
//            actuatorPrototype = new ActuatorPrototype(id, List<Identifier> groups, accelerationLeadin, accelerationLeadout, accelerationRelax, velocityLimit,
//                    valueInitial, valueMin, valueMax, inflectionJerkThreshold, List<A_Sensor> sensors);
//            actuator = actuatorPrototype;
        }
    }
}
