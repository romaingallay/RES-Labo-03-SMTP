package model;

import java.util.ArrayList;
import java.util.Collections;

public class Group {

    private ArrayList<Person> members = new ArrayList<>();;

    public Group(){
    }

    public Group(ArrayList<String> emails){

        for(String email : emails){
            members.add(new Person(email));
        }
    }

    public void setMembers(ArrayList<Person> members) {
        this.members = members;
    }

    public void addMember(Person person){
        members.add(person);

    }

    public ArrayList<Person> getMembers(){
        return new ArrayList<Person>(members);
    }

    public void shuffleMembers(){
        Collections.shuffle(members);
    }

    public Person removePerson(int index){
        return members.remove(index);
    }

    public int size(){
        return members.size();
    }

    public String[] membersEmail(){

        String[] membersEmail = new String[members.size()];

        for(int i = 0; i < members.size(); i++){
            membersEmail[i] = members.get(i).getEmail();
        }
        return membersEmail;
    }
}
