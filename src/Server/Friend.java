package Server;

import java.time.LocalDate;

public class Friend {
    private String name;
    private String mobilePhoneNr;
    private String email;
    private LocalDate birthday;

    public Friend(String name,String mobilePhoneNr,String email, String birthday){
        this.name = name;
        this.mobilePhoneNr = mobilePhoneNr;
        this.email = email;
        this.birthday = LocalDate.parse(birthday);
    }

    public String getName(){
        return name;
    }

    public String getData(){
        return "Name: " + name + ", Phone nr: " + mobilePhoneNr +
                ", E-mail: " + email + ", Birthday: " + birthday.toString();
    }
}
