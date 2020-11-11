package Multiuser.Server;

import java.io.Serializable;

public class Intro implements Serializable {

    private final String intro = "Upprättat uppkoppling mot servern.";

    public String getMessage(){
        return intro;
    }
}
