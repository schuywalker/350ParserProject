package cs350s22.component.ui.parser;

import cs350s22.component.sensor.A_Sensor;
import cs350s22.component.sensor.watchdog.A_Watchdog;
import cs350s22.support.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
I'm super confused as to where POSITION/ SPEED goes. seems like it'd be an
ID but then were does the name go (i.e. mySensor1). Does it just by default have
an id of sensor, all, position/speed, mySensor1, + watchdogs and/or reporters and/or mapper?
I must be missing something.

-Schuyler
 */


public class SensorParser {




    public static void sensorCommand(A_ParserHelper parserHelper, String[] commandText){
      /*
      The sensor command is responsible for creating a sensor with optional reporterss, optional watchdogss, and an optional mapper.
1. CREATE SENSOR (SPEED | POSITION) id1 [groups] [REPORTER[S] id2+] [WATCHDOG[S] id3+] [MAPPER id4] Creates a sensor that reports either the speed or position of the actuator it monitors. Position comes from the actuator.
Speed is based on change in position with respect to change in time.
An optional mapper maps the raw value of the sensor to its mapped value. If no mapper is provided, the mapped value is the same as the raw value.
Optional reporterss send messages at specified times with the mapped value of the sensor.
Optional watchdogss verify that the mapped value of the sensor is in compliance.
Example:
CREATE SENSOR POSITION mySensor8 GROUP myGroup1 REPORTERS myReporter1 MAPPER myMapper1
CREATE SENSOR POSITION mySensor16 GROUP myGroup1 REPORTERS myReporter1 WATCHDOGS myWatchdog1
  myWatchdog2 MAPPER myMapper1
Architecture Binding:
If reporterss, watchdogss, or a mapper are specified, get them from SymbolTable<A_Reporter>,
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
        List<Identifier> groupList = null;
        // check if these ^^^ are null later for which sensor constructor overload to call below

        if (commandText.length >= 5 && commandText[4].equals("group")) {
            // make list/ group identifier
            int i = 4;
            groupBuilder = new ArrayList<>();
            while (i < commandText.length && !commandText[i].equals("reporters") && !commandText[i].equals("watchdogs") && !commandText[i].equals("mapper")){
                groupBuilder.add(Identifier.make(commandText[i]));
                i++;
            }
            groupsAsString = Identifier.list(groupBuilder);
            groupList = Identifier.makeList(groupsAsString);



            // group gets stored on the component. default GROUP is all. also has a list
            // groups it belongs to called _groups.

        }

        int watchdogs = -1;
        int mapper = -1;
        int reporters = -1;
        for (int i = 3; i < commandText.length; i++){
            if (commandText[i].equals("watchdogs")){
                watchdogs = i;
            }
            else if (commandText[i].equals("mapper")){
                mapper = i;
            }
            else if (commandText[i].equals("reporters")){
                reporters = i;
            }
        }

        if (watchdogs > 0){
            ArrayList<Identifier> watchdogsBuilder = new ArrayList<>();
            while (watchdogs < commandText.length && !commandText[watchdogs].equals("reporters") && !commandText[watchdogs].equals("watchdogs") && !commandText[watchdogs].equals("mapper")){
                watchdogsBuilder.add(Identifier.make(commandText[watchdogs]));
                watchdogs++;
            }

            List<Identifier> watchdogsList = Identifier.makeList(Identifier.list(watchdogsBuilder));

        }

        if (reporters > 0) {
            ArrayList<Identifier> reportersBuilder = new ArrayList<>();
            while (reporters < commandText.length  && !commandText[reporters].equals("reporters") && !commandText[reporters].equals("watchdogs") && !commandText[reporters].equals("mapper")){
                reportersBuilder.add(Identifier.make(commandText[reporters]));
                reporters++;
            }

            List <Identifier> reportersList = Identifier.makeList(Identifier.list(reportersBuilder));
        }



        if (mapper > 0){}
        if (reporters > 0){
            
        }
        
        A_Sensor sensor = null;
        
        if (watchdogs > 0 && reporters > 0 && groupList == null) {
            //sensor = new A_Sensor(id, reporters, watchdogs);
        }



//            A_Sensor newSensor = new A_Sensor()
//            parserHelper.getSymbolTableSensor().add(id, newSensor);

    }
}
