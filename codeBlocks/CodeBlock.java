package oop.ex6.codeBlocks;

import oop.ex6.variables.VariableFactory;
import oop.ex6.variables.Variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CodeBlocks abstract Class
 */
public class CodeBlock {

    protected List<CodeBlock> inerBlocks;
    protected List<Variables> inerVariables;

    protected CodeBlock parent;
    protected String[] codeLines;

    private Matcher matcher;
    private Pattern pattern;

    public static final String IGNOREPATTERN = "^///.+|/s+";
    //public static final String SEMICOLOMPATTERN = ".*?;/s*";
    public static final String VARIABLEPATTERN = "\\s*(final\\s+)?(\\w*\\s+)(\\D\\w*(\\s*=\\s*\\w+)?\\s*,)*" +
            "(\\s*\\D\\w*(\\s*=\\s*\\w+)?\\s*;\\s*)";
    public static final String OPENMETHOD = ".*?{/s*";
    public static final String CLOSEDMETHOD = "/s*}/s*";

    public CodeBlock(CodeBlock parent, String[] codeLines) {
        this.parent = parent;
        this.codeLines = codeLines;
        this.inerVariables = new ArrayList<>();
        this.inerBlocks = new ArrayList<>();
        linesToBlocks();
    }

    protected void linesToBlocks() {
        //todo parse text to blocks
        int i = 0;
        while (i < codeLines.length) {
            if (checkOneLiner(codeLines[i], IGNOREPATTERN)) {
                i++;
            } else if (checkOneLiner(codeLines[i], VARIABLEPATTERN)) {
                parseVariableLine();
                i++;
            } else {
                if (checkOneLiner(codeLines[i], OPENMETHOD)) {
                    int firstline = i;
                    int openCounter = 1, closedCounter = 0;
                    i++;
                    while (openCounter != closedCounter && i < codeLines.length) {
                        if (checkOneLiner(codeLines[i], OPENMETHOD)) {
                            openCounter++;
                        } else if (checkOneLiner(codeLines[i], CLOSEDMETHOD)) {
                            closedCounter++;
                        }
                        i++;
                    }
                    String[] methodLines = Arrays.copyOfRange(codeLines, firstline, i - 1);
                    inerBlocks.add(BlockFactory.blockFactory(this, codeLines[firstline], methodLines));
                }

            }
        }
    }

    /**
     * @param line         to check
     * @param checkPattern to match
     * @return true if line matches pattern
     */
    private boolean checkOneLiner(String line, String checkPattern) {
        pattern = Pattern.compile(checkPattern);
        matcher = pattern.matcher(line);
        return matcher.matches();
    }


    private boolean checkMethod(String line) {
        //todo
        return true;
    }

    private void parseVariableLine() {
        boolean isFinal = false;
        int i = 1;
        if (matcher.group(i).equals("final")) {
            isFinal = true;
            i++;
        }
        String type = matcher.group(i);
        i++;
        while (i < matcher.groupCount()) {
            try {
                inerVariables.add(VariableFactory.variableFactory(type, isFinal, matcher.group(i)));
                i++;
            }
            catch (Exception e){

            }
        }
    }
}

