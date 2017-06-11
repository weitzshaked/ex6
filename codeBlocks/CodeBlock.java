package oop.ex6.codeBlocks;

import oop.ex6.Variables.Variables;

/**
 * CodeBlocks abstract Class
 */
public abstract class CodeBlock {

    protected CodeBlock[] inerBlocks;
    protected Variables[] inerVariables;
    protected CodeBlock parent;
    protected String[] codeLines;

    public CodeBlock(CodeBlock parent, String[] codeLines){
        this.parent = parent;
        this.codeLines = codeLines;
    }


    protected void linesToBlock(){
        //todo parse text to blocks

    }

}
