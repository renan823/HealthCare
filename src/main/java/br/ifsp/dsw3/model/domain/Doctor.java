package br.ifsp.dsw3.model.domain;

public class Doctor {
    private String name;
    private String CRM;
    private String specialization;
    
    public Doctor(String CRM, String name, String specialization) {
        this.CRM = CRM;
        this.name = name;
        this.specialization = specialization;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCRM() {
        return CRM;
    }
    public void setCRM(String CRM) {
        this.CRM = CRM;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor [name=" + name + ", CRM=" + this.CRM + ", specialization=" + specialization + "]";
    }
}

