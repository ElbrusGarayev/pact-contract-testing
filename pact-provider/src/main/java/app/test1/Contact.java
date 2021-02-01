package app.test1;

/**
 * @author Elbrus Garayev on 2/1/2021
 */
public class Contact {

    private String email;
    private String phone_number;

    public Contact(String email, String phone_number) {
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
