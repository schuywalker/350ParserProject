package cs350s22.component.ui.parser;

import java.util.*;

import cs350s22.network.Network;
import cs350s22.support.Identifier;
import cs350s22.component.controller.*;
import cs350s22.component.actuator.*;
import cs350s22.component.sensor.*;
public class NetworkParser
{
    public static void networkCommand(A_ParserHelper parserHelper, String[] commandText)
    {
      String [] ids = Arrays.copyOfRange(commandText, 4, commandText.length);
      List <Identifier> idList = Identifier.makeList(ids);
      List <A_Actuator> actuators;
      List <A_Sensor> sensors;
      List <A_Controller> controllers;
      SymbolTable <A_Actuator> actuatorSymbolTable = parserHelper.getSymbolTableActuator();
      SymbolTable <A_Sensor> sensorSymbolTable = parserHelper.getSymbolTableSensor();
      SymbolTable <A_Controller> controllerSymbolTable = parserHelper.getSymbolTableController();
      actuators = actuatorSymbolTable.get(idList, true);
      sensors = sensorSymbolTable.get(idList, true);
      controllers = controllerSymbolTable.get(idList, true);
      
      A_ControllerForwarding masterController = parserHelper.getControllerMaster();
      
      masterController.addComponents(actuators);
      masterController.addComponents(sensors);
      masterController.addComponents(controllers);
      Network net = parserHelper.getNetwork();
      net.writeOutput();
    }
}
