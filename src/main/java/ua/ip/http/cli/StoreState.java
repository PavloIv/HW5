package ua.ip.http.cli;

import ua.ip.http.entity.Store;
import ua.ip.http.util.StoreUtil;

public class StoreState extends CliState {
    private StoreUtil storeUtil;
    public StoreState(CliFSM fsm) {
        super(fsm);
    }
    @Override
    public void init() {
        System.out.println("Please, write command with list:");
        System.out.println("'order' for create order for a pet");
        System.out.println("'find' for find user");
        System.out.println("'delete' for delete user with userName");
        System.out.println("'inventories' for login");

        storeUtil = new StoreUtil();

        String command = fsm.getScanner().nextLine();
        startStoreCommandLoop(command);
    }

    private void startStoreCommandLoop(String command) {
        boolean back = false;
        switch (command) {
            case "order":
                create();
                break;
            case "find":
                find();
                break;
            case "delete":
                delete();
                break;
            case "inventories":
                inventories();
                break;

            case "back":
                back = true;
                break;
            default:
                System.out.println("Command not found");
                break;
        }
        if (back) {
            fsm.setState(new IdleState(fsm));
        } else {
            init();
        }

    }

    @Override
    protected void create() {
        System.out.println("Please,write id");
        int id = fsm.writeDigit();
        System.out.println("Please,write pet id");
        int petId = fsm.writeDigit();
        System.out.println("Please,write quantity");
        int quantity = fsm.writeDigit();
        System.out.println("Please,write shipDate in format 'yyyy-mm-dd'");
        String  shipDate = fsm.getScanner().nextLine();
        System.out.println("Please,write order status");
        String status = fsm.getScanner().nextLine();
        System.out.println("Please,write 1 if all true or 0 else");
        boolean complete;
        if (fsm.writeDigit() == 1){
            complete = true;
        }else {
            complete = false;
        }

        Store store = new Store(id,petId,quantity,shipDate,status,complete);

        if (storeUtil.purchasingPet(store) / 100 == 2) {
            System.out.println("Order complete");
        } else {
            System.out.println("We have problem with ordering pet,try again");
        }
    }

    @Override
    protected void find() {
        System.out.println("Please,write id");
        int id = fsm.writeDigit();

        System.out.println(storeUtil.findPunches(id));
    }

    @Override
    protected void delete() {
        System.out.println("Please,write id to delete");
        int id = fsm.writeDigit();

        if (storeUtil.deleteOrder(id)/100 == 2) {
            System.out.println("All fine.Order delete delete");
        } else {
            System.out.println("We have problem with delete order,try again");
        }
    }

    private void inventories() {
        System.out.println(storeUtil.inventories());
    }
}
