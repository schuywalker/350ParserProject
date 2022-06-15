package cs350s22.component.ui.parser;

import cs350s22.component.A_Component;
import cs350s22.component.controller.A_Controller;
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

        Identifier id = Identifier.make(commandText[3]);

        if (commandText[0].equalsIgnoreCase("set")){
            A_Sensor s = parserHelper.getSymbolTableSensor().get(Identifier.make(commandText[2]));
            double value = Double.parseDouble((commandText[4]));
//            parserHelper.getSymbolTableController().get(id);
//            if (parserHelper.getSymbolTableController().contains(Identifier.make(commandText[2]))){
//
//            }
            s.registerController(parserHelper.getNetwork().getMasterController());
            s.setValue(value);
            return;
        }
        else if (commandText[0].equalsIgnoreCase("get")){
            A_Sensor s = parserHelper.getSymbolTableSensor().get(Identifier.make(commandText[2]));
            System.out.println("sensor values"+ s.getValue());

            return;
        }

        List<Identifier> groupBuilder = null;
        String groupsAsString = null;
        List<Identifier> groupList = null;

        if (commandText.length >= 5 && commandText[4].equals("group")) {
            int i = 4;
            groupBuilder = new ArrayList<>();
            while (i < commandText.length && !commandText[i+1].equals("reporters") && !commandText[i+1].equals("watchdogs") && !commandText[i+1].equals("mapper")){
                i++;
                groupBuilder.add(Identifier.make(commandText[i]));
            }
            groupsAsString = Identifier.list(groupBuilder);
            groupList = Identifier.makeList(groupsAsString);
        }

        int watchdog = -1;
        int mapper = -1;
        int reporter = -1;
        List<Identifier> watchdogList = null;
        List <Identifier> reporterList = null;
        Identifier mapperID = null;
        A_Mapper mapper3 = null;
        A_Mapper mapper4 = null;

        for (int i = 3; i < commandText.length; i++){
            if (commandText[i].equals("watchdogs")){
                watchdog = i+1;
            }
            else if (commandText[i].equals("mapper")){
                mapper = i+1;
            }
            else if (commandText[i].equals("reporters")){
                reporter = i+1;
            }
        }

        if (watchdog > 0){
            ArrayList<Identifier> watchdogBuilder = new ArrayList<>();
            while (watchdog < commandText.length && !commandText[watchdog].contains("reporters") && !commandText[watchdog].contains("watchdogs") && !commandText[watchdog].equals("mapper")){
                watchdogBuilder.add(Identifier.make(commandText[watchdog]));
                watchdog++;
            }
            watchdogList = Identifier.makeList(Identifier.list(watchdogBuilder));
        }

        if (reporter > 0) {
            ArrayList<Identifier> reporterBuilder = new ArrayList<>();
            while (reporter < commandText.length  && !commandText[reporter].contains("reporters") && !commandText[reporter].contains("watchdogs") && !commandText[reporter].equals("mapper")){
                reporterBuilder.add(Identifier.make(commandText[reporter]));
                reporter++;
            }
            reporterList = Identifier.makeList(Identifier.list(reporterBuilder));
        }

        if (mapper > 0){

            mapperID = Identifier.make(commandText[mapper]);

              mapper4 = parserHelper.getSymbolTableMapper().get(Identifier.make(commandText[mapper]));
        }

        
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
