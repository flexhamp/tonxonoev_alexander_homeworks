package ru.homeworks.reflection.proxy.beanUtils;



/**
 * Created by atonk on 12.10.2016.
 */
public class Receiver {
    private Object name;
    private Integer id;
    private boolean isUser;

    public Receiver(String name, int id, boolean isUser) {
        this.name = name;
        this.id = id;
        this.isUser=isUser;
    }

    public Object getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Receiver: \n id(Integer)="+this.id+
                "\nname(Object)="+this.name+"\n"+
                "isUser(boolean)="+this.isUser;
    }
}
