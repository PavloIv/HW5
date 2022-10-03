package ua.ip.http.entity.pet;

import lombok.Data;

@Data
public class Tags {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
