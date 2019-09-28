package Model;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    private Long id;
    private String description;
    private List<PlanRecipe> planrecipe = new ArrayList<PlanRecipe>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlanRecipe> getPlanrecipe() {
        return planrecipe;
    }

    public void setPlanrecipe(List<PlanRecipe> planrecipe) {
        this.planrecipe = planrecipe;
    }
}
