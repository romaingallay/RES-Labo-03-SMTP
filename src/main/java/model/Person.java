/**
 * Person class.
 * @author Romain Gallay
 * @author Labinot Rashiti
 */
package model;

public class Person {

    private String email;
    private String firstname;
    private String lastname;

    public Person(String email){

        this.email = email;

        // try to retrieve first and lastname from email assuming it has the format firstname.lastname@smthing
        try {
            firstname = email.substring(0, email.indexOf("."));
            lastname = email.substring(email.indexOf(".") + 1, email.indexOf("@"));
        } catch (IndexOutOfBoundsException e){
            firstname = lastname = "";
        }

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
