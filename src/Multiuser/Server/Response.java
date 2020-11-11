package Multiuser.Server;

import java.io.Serializable;

public class Response implements Serializable {
    private boolean containsFriend;
    private Friend friend;
    private Intro intro;

    // Konstruktorer
    public Response(){
        intro = new Intro();
    }

    public Response(boolean isFriendFound){
        this.containsFriend = false;
    }

    public Response(Friend friend){
        this.friend = friend;
        this.containsFriend = true;
    }

    // Metoder
    public boolean getContainsFriend(){
        return containsFriend;
    }

    public Friend getFriend(){
        return friend;
    }

    public Intro getIntro(){
        return intro;
    }
}
