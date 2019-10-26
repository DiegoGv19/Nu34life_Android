package Model;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private Long id;

    private String name;
    private String description;
    private Integer carbohydrate;
    private Integer fat;
    private Integer protein;
    private Integer cholesterol;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Integer carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Integer getPotasium() {
        return potasium;
    }

    public void setPotasium(Integer potasium) {
        this.potasium = potasium;
    }

    public List<RecipeDetail> getRecipesdetails() {
        return recipesdetails;
    }

    public void setRecipesdetails(List<RecipeDetail> recipesdetails) {
        this.recipesdetails = recipesdetails;
    }

    private Integer sodium;
    private Integer potasium;
    private List<RecipeDetail> recipesdetails = new ArrayList<RecipeDetail>();

}
