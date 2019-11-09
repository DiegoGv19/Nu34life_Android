package Model;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    private Long id;
    private String day;
    private Long patientId;
    private Long recipeId;
    private String turn;

    public Plan(String day, Long patientId, Long recipeId, String turn) {
        this.day = day;
        this.patientId = patientId;
        this.recipeId = recipeId;
        this.turn = turn;
    }

    public Plan(Long id, String day, Long patientId, Long recipeId, String turn) {
        this.id = id;
        this.day = day;
        this.patientId = patientId;
        this.recipeId = recipeId;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", patientId=" + patientId +
                ", recipeId=" + recipeId +
                ", turn='" + turn + '\'' +
                '}';
    }
}
