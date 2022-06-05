package cs350s22.component.ui.parser;

import java.util.Locale;

public class SensorParser {

    public enum Type {
            SPEED,
            POSITION
    }


    protected Type type;
    protected String id;


    public static void sensorCommand(A_ParserHelper parserHelper, String[] commandText){
      /*
      The sensor command is responsible for creating a sensor with optional reporters, optional watchdogs, and an optional mapper.
1. CREATE SENSOR (SPEED | POSITION) id1 [groups] [REPORTER[S] id2+] [WATCHDOG[S] id3+] [MAPPER id4] Creates a sensor that reports either the speed or position of the actuator it monitors. Position comes from the actuator.
Speed is based on change in position with respect to change in time.
An optional mapper maps the raw value of the sensor to its mapped value. If no mapper is provided, the mapped value is the same as the raw value.
Optional reporters send messages at specified times with the mapped value of the sensor.
Optional watchdogs verify that the mapped value of the sensor is in compliance.
Example:
CREATE SENSOR POSITION mySensor8 GROUP myGroup1 REPORTERS myReporter1 MAPPER myMapper1
CREATE SENSOR POSITION mySensor16 GROUP myGroup1 REPORTERS myReporter1 WATCHDOGS myWatchdog1
  myWatchdog2 MAPPER myMapper1
Architecture Binding:
If reporters, watchdogs, or a mapper are specified, get them from SymbolTable<A_Reporter>,
SymbolTable<A_Watchdog>, or SymbolTable<A_Mapper>, respectively.
Create a new MySensor object with the arguments provided and add it to
SymbolTable<A_Sensor>.
       */


        if (commandText[2].toLowerCase().equals("speed")){
            this.type = Type.SPEED;
        }
        else if (commandText[2].toLowerCase().equals("position")){
            this.type = Type.POSITION;
        }
        else {
            throw new RuntimeException("bad arg H1 speed/ position");
        }
        this.id = commandText[3];

        // parses and executes CREATE SENSOR

    }
}
