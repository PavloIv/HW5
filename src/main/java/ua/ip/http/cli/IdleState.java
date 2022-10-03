package ua.ip.http.cli;

public class IdleState extends CliState{
    public IdleState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        fsm.printedStartMessage();
    }

    @Override
    public void workingWithUser() {
        fsm.setState(new UserState(fsm));
    }

    @Override
    public void workingWithStore() {
        fsm.setState(new StoreState(fsm));
    }

    @Override
    public void workingWithPet() {
        fsm.setState(new PetState(fsm));
    }

    @Override
    protected void uknown(String cmd) {
        System.out.println("System doesn't know command " + cmd);
    }
}
