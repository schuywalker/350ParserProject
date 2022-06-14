package cs350s22.component.ui.parser;

import cs350s22.component.sensor.watchdog.*;
import cs350s22.component.sensor.watchdog.mode.A_WatchdogMode;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeAverage;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeInstantaneous;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeStandardDeviation;
import cs350s22.support.Identifier;

public class WatchdogParser {

    public static void watchdogCommand(A_ParserHelper parserHelper, String[] commandText){
        A_Watchdog watchdog = null;
        A_WatchdogMode watchdogMode = null;
        Identifier watchdogId = Identifier.make(commandText[3]);
        double thresholdLow = 0.0;
        double thresholdHigh = 0.0;
        double threshold = 0.0;
        int failureThreshold = 0;
        boolean hasGrace = false;

        for (int i = 4; i < commandText.length; i++)
        {
            switch (commandText[i])
            {
                case "mode":
                    switch (commandText[i + 1])
                    {
                        case "instantaneous":
                            watchdogMode = new WatchdogModeInstantaneous();
                            break;
                        case "average":
                            if (!commandText[i + 2].equals("threshold"))
                            {
                                watchdogMode = new WatchdogModeAverage(Integer.valueOf(commandText[i + 2]));
                            }
                            else
                            {
                                watchdogMode = new WatchdogModeAverage();
                            }

                            break;
                        case "standard":
                            if (!commandText[i + 3].equals("threshold"))
                            {
                                watchdogMode = new WatchdogModeStandardDeviation(Integer.valueOf(commandText[i + 3]));
                            }
                            else
                            {
                                watchdogMode = new WatchdogModeStandardDeviation();
                            }
                            break;
                    }
                    break;
                case "threshold":
                    switch (commandText[i + 1])
                    {
                        case "low":
                            thresholdLow = Double.valueOf(commandText[i + 2]);
                            thresholdHigh = Double.valueOf(commandText[i + 4]);
                            break;
                        case "high":
                            thresholdHigh = Double.valueOf(commandText[i + 2]);
                            thresholdLow = Double.valueOf(commandText[i + 4]);
                            break;
                    }
                    break;
                case "grace":
                    hasGrace = true;
                    failureThreshold = Integer.valueOf(commandText[i + 1]);
                    break;
            }

            switch (commandText[2])
            {
                case "acceleration":
                    if(hasGrace) {
                        watchdog = new WatchdogAcceleration(thresholdLow, thresholdHigh, watchdogMode, failureThreshold);
                    }
                    else {
                        watchdog = new WatchdogAcceleration(thresholdLow,thresholdHigh, watchdogMode);
                    }
                    break;

                case "band":
                    if(hasGrace) {
                        watchdog = new WatchdogBand(thresholdLow, thresholdHigh, watchdogMode, failureThreshold);
                    }
                    else {
                        watchdog = new WatchdogBand(thresholdLow,thresholdHigh, watchdogMode);
                    }
                    break;

                case "notch":
                    if(hasGrace) {
                        watchdog = new WatchdogNotch(thresholdLow, thresholdHigh, watchdogMode, failureThreshold);
                    }
                    else {
                        watchdog = new WatchdogNotch(thresholdLow,thresholdHigh, watchdogMode);
                    }
                    break;

                case "low":
                    if(hasGrace) {
                        watchdog = new WatchdogLow(threshold, watchdogMode, failureThreshold);
                    }
                    else {
                        watchdog = new WatchdogLow(threshold, watchdogMode);
                    }
                    break;

                case "high":
                    if(hasGrace) {
                        watchdog = new WatchdogHigh(threshold, watchdogMode, failureThreshold);
                    }
                    else {
                        watchdog = new WatchdogHigh(threshold, watchdogMode);
                    }
                    break;
            }
        }
        parserHelper.getSymbolTableWatchdog().add(watchdogId, watchdog);
    }
}
