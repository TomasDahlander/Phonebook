package Standard.Server;

import java.util.*;

public class Database {
    private List<Friend> friends = new ArrayList<>();

    public Database(){
        friends.add(new Friend("Håkan Andersson","070-123 45 67",
                "h.andersson@gmail.com","1986-06-15"));
        friends.add(new Friend("Anna Håkansson","073-765 43 21",
                "a.hakansson@gmail.com","1964-03-05"));
        friends.add(new Friend("Johan Kutta","076-132 47 89",
                "j.kutta@gmail.com","1944-10-10"));
        friends.add(new Friend("Amish Oldberg","073-529 23 57",
                "a.oldberg@gmail.com","1974-11-25"));
        friends.add(new Friend("Kalle Rutsson","076-612 71 28",
                "k.rutsson@gmail.com","1964-03-22"));
        friends.add(new Friend("Linus Bråke","076-924 35 64",
                "l.brake@gmail.com","1975-04-09"));
        friends.add(new Friend("Sussan Underlig","070-522 12 82",
                "s.underlig@gmail.com","1995-12-04"));
        friends.add(new Friend("Nea Lolito","070-863 42 24",
                "n.lolito@gmail.com","1991-02-25"));
    }

    public String getFriendData(String name){
        for(int i = 0; i < friends.size(); i++){
            if(name.trim().equalsIgnoreCase(friends.get(i).getName().trim())){
                return friends.get(i).getData();
            }
        }
        return null;
    }
}
