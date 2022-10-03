package ua.ip.http.cli;

import ua.ip.http.entity.User;
import ua.ip.http.util.UserUtil;

public class UserState extends CliState {
    private UserUtil userUtil;

    public UserState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        System.out.println("Please, write command with list:");
        System.out.println("'create' for create new user");
        System.out.println("'find' for find user");
        System.out.println("'update' for update user with userName");
        System.out.println("'delete' for delete user with userName");
        System.out.println("'login' for login");
        System.out.println("'logout' for logout");

        userUtil = new UserUtil();

        String command = fsm.getScanner().nextLine();
        startUserCommandLoop(command);
    }

    private void startUserCommandLoop(String command) {
        boolean back = false;
        switch (command) {
            case "create":
                create();
                break;
            case "find":
                find();
                break;
            case "update":
                update();
                break;
            case "delete":
                delete();
                break;
            case "login":
                login();
                break;
            case "logout":
                logout();
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
        System.out.println("Please,write user id");
        int userId = fsm.writeDigit();
        System.out.println("Please,write username");
        String userName = fsm.getScanner().nextLine();
        System.out.println("Please,write user firstName");
        String firstName = fsm.getScanner().nextLine();
        System.out.println("Please,write user lastName");
        String lastName = fsm.getScanner().nextLine();
        System.out.println("Please,write user email");
        String email = fsm.getScanner().nextLine();
        System.out.println("Please,write user password");
        String password = fsm.getScanner().nextLine();
        System.out.println("Please,write user phone");
        String phone = fsm.getScanner().nextLine();
        System.out.println("Please,write user status");
        int status = fsm.writeDigit();

        User user = new User(userId, userName, firstName, lastName, email, password, phone, status);

        if (userUtil.createUser(user) / 100 == 2) {
            System.out.println("User add");
        } else {
            System.out.println("We have problem with add user,try again");
        }
    }

    @Override
    protected void find() {
        System.out.println("Please,write username");
        String userName = fsm.getScanner().nextLine();

        System.out.println(userUtil.findUser(userName));
    }

    @Override
    protected void update() {
        System.out.println("Please,write the userName of the user we will change");
        String oldUserName = fsm.getScanner().nextLine();

        System.out.println("Please,write user id");
        int userId = fsm.writeDigit();
        System.out.println("Please,write username");
        String newUserName = fsm.getScanner().nextLine();
        System.out.println("Please,write user firstName");
        String firstName = fsm.getScanner().nextLine();
        System.out.println("Please,write user lastName");
        String lastName = fsm.getScanner().nextLine();
        System.out.println("Please,write user email");
        String email = fsm.getScanner().nextLine();
        System.out.println("Please,write user password");
        String password = fsm.getScanner().nextLine();
        System.out.println("Please,write user phone");
        String phone = fsm.getScanner().nextLine();
        System.out.println("Please,write user status");
        int status = fsm.writeDigit();

        User user = new User(userId, newUserName, firstName, lastName, email, password, phone, status);

        if (userUtil.updateUser(oldUserName, user) / 100 == 2) {
            System.out.println("User update");
        } else {
            System.out.println("We have problem with update user,try again");
        }
    }

    @Override
    protected void delete() {
        System.out.println("Please,write username");
        String userName = fsm.getScanner().nextLine();

        if (userUtil.deleteUser(userName) / 100 == 2) {
            System.out.println("All fine.User delete");
        } else {
            System.out.println("We have problem with delete user,try again");
        }
    }

    private void login() {
        System.out.println("Please,write username");
        String userName = fsm.getScanner().nextLine();
        System.out.println("Please,write password");
        String userPassword = fsm.getScanner().nextLine();

        System.out.println(userUtil.loginUser(userName, userPassword));
    }

    private void logout() {
        System.out.println(userUtil.logoutUser());
    }

}
