package Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private Long id;

    private String name;

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

    public List<RecipeDetail> getRecipesdetails() {
        return recipesdetails;
    }

    public void setRecipesdetails(List<RecipeDetail> recipesdetails) {
        this.recipesdetails = recipesdetails;
    }

    public List<PlanRecipe> getPlanrecipe() {
        return planrecipe;
    }

    public void setPlanrecipe(List<PlanRecipe> planrecipe) {
        this.planrecipe = planrecipe;
    }

    private List<RecipeDetail> recipesdetails = new ArrayList<>();

    private List<PlanRecipe> planrecipe = new ArrayList<PlanRecipe>();

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
