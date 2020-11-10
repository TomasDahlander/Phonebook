package Use_Serializable.Server;

import java.io.Serializable;

public class Response implements Serializable {
    private boolean containsFriend;
    private Friend friend;

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
}
