package ua.ip.http.cli;


public class CliState {
    protected CliFSM fsm;

    public CliState(CliFSM fsm) {
        this.fsm = fsm;
    }

    public void init() {
    }

    public void workingWithUser() {
    }

    public void workingWithStore() {
    }

    public void workingWithPet() {
    }

    protected void create() {
    }

    protected void find() {
    }

    protected void update() {
    }

    protected void delete() {
    }

    protected void uknown(String cmd){
    }


}
