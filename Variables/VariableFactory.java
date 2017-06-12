package oop.ex6.Variables;

import oop.ex6.Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Variable Factory
 */
public class VariableFactory {

    public static Variables variableFactory(String type, boolean isFinal, String nameAndVal) throws SyntaxException {
        Pattern pattern = Pattern.compile("/s*(/w+)/s*=*/s*(/w+)*");
        Matcher matcher = pattern.matcher(nameAndVal);
        String val;
        if(matcher.groupCount()>1) {
            val = matcher.group(2);
            switch (type) {
                case "int":
                    return new Variables<Integer>(type, Integer.parseInt(val),matcher.group(1),isFinal);
                case "String":
                    return new Variables<String>(type, val,matcher.group(1),isFinal);
                case "double":
                    return new Variables<Double>(type, Double.parseDouble(val),matcher.group(1),isFinal);
                case "boolean":
                    break;
                default:
                    break;
            }
        }
            //todo throw syntex exception
            throw new SyntaxException("");
    }

}
