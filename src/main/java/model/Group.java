package model;

import java.util.ArrayList;

public class Group {

    private ArrayList<Person> members;

    public Group(){

    }

    public void addMember(Person person){
        members.add(person);
    }

    public ArrayList<Person> getMembers(){
        return new ArrayList<Person>(members);
    }
}
