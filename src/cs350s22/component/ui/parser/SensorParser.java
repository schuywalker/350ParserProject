package cs350s22.component.ui.parser;

import cs350s22.component.sensor.A_Sensor;
import cs350s22.support.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
I'm super confused as to where POSITION/ SPEED goes. seems like it'd be an
ID but then were does the name go (i.e. mySensor1). Does it just by default have
an id of sensor, all, position/speed, mySensor1, + watchdog and/or reporter and/or mapper?
I must be missing something.

-Schuyler
 */


public class SensorParser {




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

        Identifier id = Identifier.make(commandText[3]);

//        if (!parserHelper.getSymbolTableSensor().contains(id)){
//        // before we can add to symbol table, we need to get a Sensor object
//
//    }

        List<Identifier> groupBuilder = null;
        String groupsAsString = null;
        List<Identifier> finalList = null;
        // check if these ^^^ are null later for which sensor constructor overload to call below

        if (commandText.length >= 5 && commandText[4].equals("group")) {
            // make list/ group identifier
            int i = 4;
            groupBuilder = new ArrayList<>();
            while (i < commandText.length && !commandText[i].equals("reporters") && !commandText[i].equals("watchdog") && !commandText[i].equals("mapper")){
                groupBuilder.add(Identifier.make(commandText[i]));
                i++;
            }
            groupsAsString = Identifier.list(groupBuilder);
            finalList = Identifier.makeList(groupsAsString);



            // group gets stored on the component. default GROUP is all. also has a list
            // groups it belongs to called _groups.

        }

        int watchdog = -1;
        int mapper = -1;
        int reporter = -1;
        for (int i = 3; i < commandText.length; i++){
            if (commandText[i].equals("watchdog")){
                watchdog = i;
            }
            else if (commandText[i].equals("mapper")){
                mapper = i;
            }
            else if (commandText[i].equals("reporter")){
                reporter = i;
            }
        }

        if (watchdog > 0){}
        if (mapper > 0){}
        if (reporter > 0){}



//            A_Sensor newSensor = new A_Sensor()
//            parserHelper.getSymbolTableSensor().add(id, newSensor);

    }
}
