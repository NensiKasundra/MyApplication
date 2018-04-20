package example.com.myapplication;

/**
 * Created by kasundne on 4/4/2018.
 */

public class StateModel {
    private String stateId;
    private String StateName;

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    StateModel(String stateId, String stateName) {
        this.stateId = stateId;
        StateName = stateName;
    }
}
