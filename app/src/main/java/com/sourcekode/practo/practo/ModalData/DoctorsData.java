package com.sourcekode.practo.practo.ModalData;

public class DoctorsData {
    private int profile_pic ;
    private String doctor_name ;
    private String practise ;
    private String street;
    private String locality;
    private String rating;
    private String fees;

    public DoctorsData() {
    }

    public DoctorsData(int profile_pic, String doctor_name, String practise, String street, String locality, String rating, String fees) {
        this.profile_pic = profile_pic;
        this.doctor_name = doctor_name;
        this.practise = practise;
        this.street = street;
        this.locality = locality;
        this.rating = rating;
        this.fees = fees;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPractise() {
        return practise;
    }

    public void setPractise(String practise) {
        this.practise = practise;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }
}
