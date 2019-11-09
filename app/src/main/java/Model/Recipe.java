package Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private Long id;

    private String name;

    private Integer servings;

    public Recipe(Long id, String name, Integer servings) {
        this.id = id;
        this.name = name;
        this.servings = servings;
    }

    public Recipe() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", servings=" + servings +
                '}';
    }
}
