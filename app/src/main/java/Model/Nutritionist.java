package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Nutritionist {
    private Long id;
    private String name;
    private String lastName;
    private String birthdate;
    private String email;
    private String password;
    private String phone;
    private List<Patient> patients;

    public Nutritionist( String name, String lastName, String birthdate, String email, String password, String phone, Patient patient,  List<Patient> patients) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.patients = patients;
    }

    public Nutritionist( String name, String lastName, String birthdate, String email, String password, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Nutritionist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", patients=" + patients +
                '}';
    }
}
