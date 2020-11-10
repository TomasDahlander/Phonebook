package Use_Serializable.Server;


import java.io.Serializable;

public class Response implements Serializable {
    private boolean containsFriend;
    private Friend friend;

    public boolean getContainsFriend(){
        return containsFriend;
    }
    public Friend getFriend(){
        return friend;
    }
}
