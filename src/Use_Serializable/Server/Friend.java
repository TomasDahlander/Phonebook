package Use_Serializable.Server;

import java.io.Serializable;
import java.time.LocalDate;

public class Friend implements Serializable {

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
        return "Name:     " + name +
               "\nPhone nr: " + mobilePhoneNr +
               "\nE-mail:   " + email +
               "\nBirthday: " + birthday.toString();
    }
}
