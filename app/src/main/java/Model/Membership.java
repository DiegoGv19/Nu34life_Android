package Model;

import java.util.ArrayList;
import java.util.List;

public class Membership {

    private Long id;
    private String name;
    private String description;
    private Double amount;
    private Integer duration;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Suscription> getSuscriptions() {
        return suscriptions;
    }

    public void setSuscriptions(List<Suscription> suscriptions) {
        this.suscriptions = suscriptions;
    }

    private List<Suscription> suscriptions = new ArrayList<>();

}
