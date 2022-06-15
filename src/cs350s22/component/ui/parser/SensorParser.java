package cs350s22.component.ui.parser;

import cs350s22.component.A_Component;
import cs350s22.component.sensor.A_Sensor;
import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.reporter.A_Reporter;
import cs350s22.component.sensor.watchdog.A_Watchdog;
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
        List<Identifier> groupList = null;
        // check if these ^^^ are null later for which sensor constructor overload to call below

        if (commandText.length >= 5 && commandText[4].equals("group")) {
            // make list/ group identifier
            int i = 4;
            groupBuilder = new ArrayList<>();
            while (i < commandText.length && !commandText[i].equals("reporter") && !commandText[i].equals("watchdog") && !commandText[i].equals("mapper")){
                groupBuilder.add(Identifier.make(commandText[i]));
                i++;
            }
            groupsAsString = Identifier.list(groupBuilder);
            groupList = Identifier.makeList(groupsAsString);



            // group gets stored on the component. default GROUP is all. also has a list
            // groups it belongs to called _groups.

        }



        // -1 if not present. array position + 1 if present
        int watchdog = -1;
        int mapper = -1;
        int reporter = -1;
        List<Identifier> watchdogList = null;
        List <Identifier> reporterList = null;
//        List<Identifier> mapperList = null;
        Identifier mapperID = null;
        A_Mapper mapper3 = null;
        A_Mapper mapper4 = null;

        for (int i = 3; i < commandText.length; i++){
            if (commandText[i].equals("watchdog")){
                watchdog = i+1;
            }
            else if (commandText[i].equals("mapper")){
                mapper = i+1;
            }
            else if (commandText[i].equals("reporter")){
                reporter = i+1;
            }
        }

        if (watchdog > 0){
            ArrayList<Identifier> watchdogBuilder = new ArrayList<>();
            while (watchdog < commandText.length && !commandText[watchdog].contains("reporter") && !commandText[watchdog].contains("watchdog") && !commandText[watchdog].equals("mapper")){
                watchdogBuilder.add(Identifier.make(commandText[watchdog]));
                watchdog++;
            }


            watchdogList = Identifier.makeList(Identifier.list(watchdogBuilder));

        }

        if (reporter > 0) {
            ArrayList<Identifier> reporterBuilder = new ArrayList<>();
            while (reporter < commandText.length  && !commandText[reporter].contains("reporter") && !commandText[reporter].contains("watchdog") && !commandText[reporter].equals("mapper")){
                reporterBuilder.add(Identifier.make(commandText[reporter]));
                reporter++;
            }

            reporterList = Identifier.makeList(Identifier.list(reporterBuilder));
        }



        if (mapper > 0){
//            ArrayList<Identifier> mappersBuilder = new ArrayList<>();
//            while (mapper < commandText.length && !commandText[watchdog].contains("reporter") && !commandText[watchdog].contains("watchdog") && !commandText[watchdog].equals("mapper")){
//                mappersBuilder.add(Identifier.make(commandText[mapper]));
//                mapper++;
//            }
            mapperID = Identifier.make(commandText[mapper]);

//            mapper3 = MapperParser.mapperCommand(parserHelper,
              mapper4 = parserHelper.getSymbolTableMapper().get(Identifier.make(commandText[mapper]));
        }

        /*
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor2 GROUP myGroup1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor3 GROUP myGroup1 myGroup2");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor4 MAPPER myMapper1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor5 GROUP myGroup1 REPORTERS myReporter1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor6 GROUP myGroup1 REPORTERS myReporter1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor7 GROUP myGroup1 myGroup2 REPORTERS myReporter1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor8 GROUP myGroup1 REPORTERS myReporter1 MAPPER myMapper1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor9 GROUP myGroup1 WATCHDOG myWatchdog1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor10 GROUP myGroup1 WATCHDOG myWatchdog1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor11 GROUP myGroup1 myGroup2 WATCHDOGS myWatchdog1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor12 GROUP myGroup1 WATCHDOGS myWatchdog1 myWatchdog2 MAPPER myMapper1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor13 GROUP myGroup1 REPORTERS myReporter1 WATCHDOG myWatchdog1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor14 GROUP myGroup1 REPORTERS myReporter1 WATCHDOG myWatchdog1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor15 GROUP myGroup1 myGroup2 REPORTERS myReporter1 WATCHDOGS myWatchdog1");
        this.parse(parserHelper, "CREATE SENSOR POSITION mySensor16 GROUP myGroup1 REPORTERS myReporter1 WATCHDOGS myWatchdog1 myWatchdog2 MAPPER myMapper1");
         */

        
        A_Sensor sensor = null;


        if (groupList == null) {

            if (mapper < 0 && watchdog >= 0 && reporter >= 0) {

                sensor = new MySensor(
                        id,
                        parserHelper.getSymbolTableReporter().get(reporterList, true),
                        parserHelper.getSymbolTableWatchdog().get(watchdogList, true)
                );
            }
            else if (mapper >= 0 && watchdog >= 0 && reporter >= 0){
                sensor = new MySensor(
                        id,
                        parserHelper.getSymbolTableReporter().get(reporterList, true),
                        parserHelper.getSymbolTableWatchdog().get(watchdogList, true),
                        mapper4
                );
            }

        }
        else {

            if (mapper < 0 && watchdog >= 0 && reporter >= 0) {

                sensor = new MySensor(
                        id,
                        parserHelper.getSymbolTableReporter().get(reporterList, true),
                        parserHelper.getSymbolTableWatchdog().get(watchdogList, true),
                        groupList
                );
            }
            else if (mapper >= 0 && watchdog >= 0 && reporter >= 0){
                sensor = new MySensor(
                        id,
                        parserHelper.getSymbolTableReporter().get(reporterList, true),
                        parserHelper.getSymbolTableWatchdog().get(watchdogList, true),
                        groupList,
                        mapper4
                );
            }
            
        }
        
        parserHelper.getSymbolTableSensor().add(id, sensor);

    }
}
