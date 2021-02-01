package app.test1;

/**
 * @author Elbrus Garayev on 2/1/2021
 */
public class User {

    private String name;
    private int salary;
    private Contact contact;

    public User(String name, int salary, Contact contact) {
        this.name = name;
        this.salary = salary;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
