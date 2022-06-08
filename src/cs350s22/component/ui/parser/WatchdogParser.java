package cs350s22.component.ui.parser;

import cs350s22.component.sensor.watchdog.A_Watchdog;
import cs350s22.component.sensor.watchdog.mode.A_WatchdogMode;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeAverage;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeInstantaneous;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeStandardDeviation;
import cs350s22.support.Identifier;

public class WatchdogParser {
    // parse

    // command




    public static void watchdogCommand(A_ParserHelper parserHelper, String[] commandText){

        A_WatchdogMode mode;

            Identifier id = Identifier.make(commandText[4]);

            switch (commandText[2]) {
                case "average":
                    mode = new WatchdogModeAverage();
                    for (int i = 2; i < commandText.length; i++){
                        if (commandText[i].equals("average")) {
                            Double a = Double.parseDouble(commandText[i+1]);
                            mode.recordValue(a);
                        }
                    }
                case "instantaneous":
                    mode = new WatchdogModeInstantaneous();
                case "standard deviation":
                    mode = new WatchdogModeStandardDeviation();
                    for (int i = 2; i < commandText.length; i++){
                        if (commandText[i].equals("standard deviation")) {
                            Double d = Double.parseDouble(commandText[i+1]);
                            mode.recordValue(d);
                        }
                    }
            }



    }
}
