package oop.ex6.Variables;

/**
 * Variable Class
 */
public class Variables<T> {

    private T data;
    private String name, type;
    private boolean isFinal;

    public Variables(T data, String name, String type, boolean isFinal){
        this.data = data;
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
    }


}
