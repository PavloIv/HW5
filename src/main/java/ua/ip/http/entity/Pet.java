package ua.ip.http.entity;


import lombok.Data;
import ua.ip.http.entity.pet.Category;
import ua.ip.http.entity.pet.Tags;

@Data
public class Pet {
    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tags[] tags;
    private String status;


    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=['" + photoUrls + ']' +'\'' +
                ", tags= [" + tags + ']' +
                ", status='" + status + '\'' +
                '}';
    }
}
