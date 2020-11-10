package Use_Serializable.Server;

import java.io.Serializable;

public class Response implements Serializable {
    private boolean containsFriend;
    private Friend friend;
    private Intro intro;


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



    public boolean getContainsFriend(){
        return containsFriend;
    }
    public void setContainsFriend(boolean containsFriend){
        this.containsFriend = containsFriend;
    }

    public Friend getFriend(){
        return friend;
    }
    public void setFriend(Friend friend){
        this.friend = friend;
    }

    public Intro getIntro(){
        return intro;
    }
}
