package cs350s22.component.ui.parser;

import cs350s22.component.actuator.A_Actuator;
import cs350s22.component.actuator.ActuatorLinear;
import cs350s22.component.actuator.ActuatorRotary;
import cs350s22.component.actuator.state.A_State;
import cs350s22.component.sensor.A_Sensor;
import cs350s22.support.Identifier;

import javax.swing.*;

public class ActuatorParser {
    public static void actuatorCommand(A_ParserHelper parserHelper, String[] commandText) {
        A_Actuator actuator;
        Identifier id;

        for (int i = 2; i < commandText.length; i++)
        {
            switch(commandText[i])
            {
                case "linear":
                    Identifier linearId = Identifier.make(commandText[3]);

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
                    ActuatorLinear linearActuator = new ActuatorLinear(linearId);
                    break;
                case "rotary":
                    Identifier rotaryId = Identifier.make(commandText[3]);

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
                    ActuatorRotary rotaryActuator = new ActuatorRotary(rotaryId);
                    break;
                case "acceleration":
                    switch (commandText[i + 1])
                    {
                        case "leadin":
                            // add lead in value as i + 2
                            // add leadout value as i + 4
                            i += 4;
                        case "leadout":
                            // add leadout value as i + 2
                            // add leadin value as i + 4
                            i += 4;
                    }
                    break;
                case "relax":
                    // add relax value
                    break;
                case "velocity":
                    // add velocity limit
                    break;
                case "value":

                   break;
                case "initial":
                    // add initial value
                    break;
                case "jerk":
                    // add jerk limit value
                    break;
            }
        }
    }
}
