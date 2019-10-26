package Model;

import java.util.Date;
import java.util.List;

public class State {

    private Long id;

    private String description;
    private Integer height;
    private Integer weight;
    private Integer glucose;
    private Date generatedDate;

    private Patient patient;

    public State( String description, Integer height, Integer weight, Integer glucose) {
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.glucose = glucose;
    }

    public State(String description, Integer height, Integer weight, Integer glucose, Date generatedDate) {
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.glucose = glucose;
        this.generatedDate = generatedDate;


    }

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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getGlucose() {
        return glucose;
    }

    public void setGlucose(Integer glucose) {
        this.glucose = glucose;
    }


    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", glucose=" + glucose +
                ", generatedDate='" + generatedDate + '\'' +
                '}';
    }
}
