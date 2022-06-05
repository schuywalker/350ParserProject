package cs350s22.component.ui.parser;

import cs350s22.component.actuator.A_Actuator;

public class ParserHelper extends A_ParserHelper
{
    SymbolTable<A_Actuator> tempTable = new SymbolTable<>();
    public ParserHelper()
    {
        System.out.println("Welcome to your ParserHelper");
    }


    public  A_Actuator createActuatorHelper(){
        //            SymbolTable<A_Actuator>
        A_Actuator temp = actuatorHelper();

        tempTable.add(1, temp);

    }


}
