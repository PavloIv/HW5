package ua.ip.http.cli;

import ua.ip.http.entity.Pet;
import ua.ip.http.entity.pet.Category;
import ua.ip.http.entity.pet.Tags;
import ua.ip.http.util.PetUtil;

public class PetState extends CliState {
    private PetUtil petUtil;

    public PetState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void init() {
        System.out.println("Please, write command with list:");
        System.out.println("'create' for create new pet");
        System.out.println("'update' for update pet");
        System.out.println("'findId' for find pet with id ");
        System.out.println("'findStatus' for find pet with status ");
        System.out.println("'updateForm' for update pet with id");
        System.out.println("'delete' for delete pet with id");

        petUtil = new PetUtil();

        String command = fsm.getScanner().nextLine();
        startPetCommandLoop(command);
    }

    private void startPetCommandLoop(String command) {
        boolean back = false;
        switch (command) {
            case "create":
                create();
                break;
            case "update":
                update();
                break;
            case "findId":
                find();
                break;
            case "findStatus":
                findForStatus();
                break;
            case "updateForm":
                updateForm();
                break;
            case "delete":
                delete();
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
        System.out.println("Please,write pet id");
        int petId = fsm.writeDigit();
        System.out.println("Please,write category id");
        int categoryId = fsm.writeDigit();
        System.out.println("Please,write category name");
        String categoryName = fsm.getScanner().nextLine();
        System.out.println("Please,write pet name");
        String petName = fsm.getScanner().nextLine();
        System.out.println("Please,write photo url");
        String photoURL = fsm.getScanner().nextLine();
        String[] photoUrls = {photoURL};
        System.out.println("Please,write tags id");
        int tagsId = fsm.writeDigit();
        System.out.println("Please,write tags name");
        String tagsName = fsm.getScanner().nextLine();
        System.out.println("Please,write status with list:");
        System.out.println("available");
        System.out.println("pending");
        System.out.println("sold");
        String status = fsm.writeStatus();

        Pet pet = new Pet();
        Category category = new Category();
        Tags tag = new Tags();

        category.setId(categoryId);
        category.setName(categoryName);

        tag.setId(tagsId);
        tag.setName(tagsName);

        Tags[] tags = {tag};

        pet.setId(petId);
        pet.setCategory(category);
        pet.setName(petName);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus(status);

        if (petUtil.createPet(pet) / 100 == 2) {
            System.out.println("Add pet complete");
        } else {
            System.out.println("We have problem with add pet,try again");
        }
    }

    @Override
    protected void update() {
        System.out.println("Please,write pet id");
        int petId = fsm.writeDigit();
        System.out.println("Please,write category id");
        int categoryId = fsm.writeDigit();
        System.out.println("Please,write category name");
        String categoryName = fsm.getScanner().nextLine();
        System.out.println("Please,write user pet name");
        String petName = fsm.getScanner().nextLine();
        System.out.println("Please,write photo url");
        String photoURL = fsm.getScanner().nextLine();
        String[] photoUrls = {photoURL};
        System.out.println("Please,write tags id");
        int tagsId = fsm.writeDigit();
        System.out.println("Please,write tags name");
        String tagsName = fsm.getScanner().nextLine();
        System.out.println("Please,write status with list:");
        System.out.println("available");
        System.out.println("pending");
        System.out.println("sold");
        String status = fsm.writeStatus();

        Pet pet = new Pet();
        Category category = new Category();
        Tags tag = new Tags();

        category.setId(categoryId);
        category.setName(categoryName);

        tag.setId(tagsId);
        tag.setName(tagsName);

        Tags[] tags = {tag};

        pet.setId(petId);
        pet.setCategory(category);
        pet.setName(petName);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus(status);

        if (petUtil.updatePet(pet) / 100 == 2) {
            System.out.println("Update pet complete");
        } else {
            System.out.println("We have problem with update pet,try again");
        }
    }

    @Override
    protected void find() {
        System.out.println("Please,write pet id");
        int petId = fsm.writeDigit();

        System.out.println(petUtil.findPetForId(petId));
    }

    protected void findForStatus() {
        System.out.println("Please,write pet status");
        String petStatus = fsm.writeStatus();

        System.out.println(petUtil.findPetForStatus(petStatus));
    }

    private void updateForm(){
        System.out.println("Please,write pet id");
        int petId = fsm.writeDigit();
        System.out.println("Please,write pet name");
        String petName = fsm.getScanner().nextLine();
        System.out.println("Please,write pet status");
        String petStatus = fsm.writeStatus();

        System.out.println(petUtil.updateForm(petId,petName,petStatus));
    }

    @Override
    protected void delete() {
        System.out.println("Please,write pet id");
        int petId = fsm.writeDigit();

        if (petUtil.deletePet(petId) / 100 == 2) {
            System.out.println("All fine.Pet delete");
        } else {
            System.out.println("We have problem with delete pet,try again");
        }
    }
}
