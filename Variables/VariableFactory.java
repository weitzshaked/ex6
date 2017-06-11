package oop.ex6.Variables;

import com.sun.org.apache.xpath.internal.operations.Variable;
import oop.ex6.codeBlocks.ConditionBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Variable Factory
 */
public class VariableFactory {

    public VariableFactory(String line){
        String type;
        Pattern declareVariable = Pattern.compile("\\s*(final\\s+)?(\\w*\\s+)(\\D\\w*(\\s*=\\s*\\w+)?\\s*,)*" +
                "(\\s*\\D\\w*(\\s*=\\s*\\w+)?\\s*;\\s*)");
        Matcher m = declareVariable.matcher(line);
        if(m.matches()){
            type = m.group(1);
            if(type.equals("final")){
                type = m.group(2);
            }
            switch (type){
                case "int":
                    break;
                case "String":
                    break;
                case "double":
                    break;
                case "boolean":
                    break;
                default:
                    break;
            }
        }
        else {
            //todo throw syntex exception
        }

    }

    public static void main(String[] args) {
        VariableFactory f = new VariableFactory("final int ab12=3, aa=  6;");
    }
}
