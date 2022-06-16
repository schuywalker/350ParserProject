package cs350s22.component.ui.parser;

import cs350s22.component.actuator.*;
import cs350s22.support.*;

import java.io.IOException;

public class Parser {

    String command;
    A_ParserHelper parserHelper;

    public Parser(A_ParserHelper parserHelper, String command) {
        this.command = command;
        this.parserHelper = parserHelper;
    }

    public void parse() throws IOException, ParseException {
        String[] splitCommands = command.toLowerCase().split(" ");

        boolean metaCommand = false;
        if (splitCommands[0].charAt(0) == '@') {
            metaCommand = true;
            splitCommands[0] = splitCommands[0].substring(1).toLowerCase();
            Meta.metaCommand(parserHelper, splitCommands);
        }
        else {
            String type = splitCommands[1].toUpperCase();
            switch (type) {
                case "ACTUATOR":
                    ActuatorParser.actuatorCommand(parserHelper, splitCommands);
                    break;
                case "CONTROLLER":
                    ControllerParser.controllerCommand(parserHelper, splitCommands);
                    break;
                case "DEPENDENCY":
                    DependencyParser.dependencyCommand(parserHelper, splitCommands);
                    break;
                case "MAPPER":
                    MapperParser.mapperCommand(parserHelper, splitCommands);
                    break;
                case "MESSAGE":
                    MessageParser.messageCommand(parserHelper,splitCommands);
                    break;
                case "NETWORK":
                    NetworkParser.networkCommand(parserHelper,splitCommands);
                    break;
                case "REPORTER":
                    ReporterParser.reporterCommand(parserHelper,splitCommands);
                    break;
                case "SENSOR":
                    SensorParser.sensorCommand(parserHelper, splitCommands);
                    break;
                case "WATCHDOG":
                    WatchdogParser.watchdogCommand(parserHelper,splitCommands);
                    break;
                default: System.out.println("Invalid command");
            }
        }
    }

}
