

/**
 * Created by Denver on 11/8/2016.
 */
public class ActionEvent extends Event{

    private Action action;
    
    //This constructor was missing from our design
    public ActionEvent(Action a){
    	action = a;
    }

    public Action getAction(){
        // return action
    	return action;
    }
}
