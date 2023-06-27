package br.ifsp.dsw3.model.domain;

public class Patient{
    private String name;
    private String CPF;
    private String sex;
    private String phone;

    public Patient(String name, String CPF, String sex, String phone) {
        this.name = name;
        this.CPF = CPF;
        this.sex = sex;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCPF() {
        return this.CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Patient [name=" + this.name + ", CPF=" + this.CPF + ", sex=" + this.sex + ", phone=" + this.phone + "]";
    }
}