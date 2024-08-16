package com.vivah.app.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class ProfileDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String maritalStatus;
    private String address;
    private String religion;
    private String email;
    private String mobileNumber;
    private String education;
    private String occupation;
    private String income;
    private String aboutMe;
    private int age;
    private String religionPref;
    private Long userId;


    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public String getReligion() {
        return religion;
    }

    public String getReligionPref() {
        return religionPref;
    }


    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEducation() {
        return education;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getIncome() {
        return income;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setReligionPref(String religionPref) {
        this.religionPref = religionPref;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    // Method to calculate age
    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }
}
