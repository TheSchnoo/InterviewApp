package mycompany.myinterview;

/**
 * Created by Wesb on 5/10/15.
 */
public class InternetException extends  Exception{

    private String locationThrown;

    public InternetException(String locationThrown){
        this.locationThrown = locationThrown;
    }

}
