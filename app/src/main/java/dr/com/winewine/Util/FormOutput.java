package dr.com.winewine.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by divyaraghavan on 1/22/15.
 */
public class FormOutput {
    private static FormOutput INSTANCE = null;
    public Map<String,Boolean> formOutput ;
    private FormOutput(){
        formOutput = new HashMap<String, Boolean>();
    }

    public static FormOutput getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FormOutput();

        }

        return INSTANCE;
    }


}
