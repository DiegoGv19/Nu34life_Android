package Model;

public class Steps {

    private Long id;
    private String instruction;
    private Integer stepNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public String toString() {
        return "Steps{" +
                "id=" + id +
                ", instruction='" + instruction + '\'' +
                ", stepNumber=" + stepNumber +
                '}';
    }
}
