package Model;

public class NutrFact {

    private Long id;
    private Integer carbohydrates;
    private Integer energeticValue;
    private Integer protein;
    private Integer salt;
    private Integer saturatedFats;
    private Integer sugars;
    private Integer totalFat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getEnergeticValue() {
        return energeticValue;
    }

    public void setEnergeticValue(Integer energeticValue) {
        this.energeticValue = energeticValue;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public Integer getSaturatedFats() {
        return saturatedFats;
    }

    public void setSaturatedFats(Integer saturatedFats) {
        this.saturatedFats = saturatedFats;
    }

    public Integer getSugars() {
        return sugars;
    }

    public void setSugars(Integer sugars) {
        this.sugars = sugars;
    }

    public Integer getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Integer totalFat) {
        this.totalFat = totalFat;
    }

    @Override
    public String toString() {
        return "NutrFact{" +
                "id=" + id +
                ", carbohydrates=" + carbohydrates +
                ", energeticValue=" + energeticValue +
                ", protein=" + protein +
                ", salt=" + salt +
                ", saturatedFats=" + saturatedFats +
                ", sugars=" + sugars +
                ", totalFat=" + totalFat +
                '}';
    }
}
