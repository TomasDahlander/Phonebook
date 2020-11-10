package Use_Serializable.Server;

public class Procotol {

    Database d = new Database();

    private static final int INITIATE = 0;
    private static final int WAITING = 1;

    private int currentState = INITIATE;

    public Response getResponse(String input){
        Response response = null;

        if(currentState == INITIATE){
            response = new Response();
            currentState = WAITING;
        }

        else if(currentState == WAITING){
            Object obj = d.getFriend(input);
            if(obj instanceof Friend){
                response = new Response((Friend)obj);
            }
            else{
                response = new Response(false);
            }
        }
        return response;
    }
}
