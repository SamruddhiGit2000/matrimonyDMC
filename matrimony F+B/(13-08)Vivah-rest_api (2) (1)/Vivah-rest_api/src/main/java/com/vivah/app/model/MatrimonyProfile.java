package com.vivah.app.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="MatrimonyProfile")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatrimonyProfile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Transient
    @Column(name = "Age")
    private int age;
    @Column(name = "Address")
    private String address;
    @Column(name = "Mobile")
    private String mobileNumber;
    @Column(name = "Email")
    private String email;
    @Column(name = "Religion")
    private String religion = "Islam";
    @Column(name = "Education")
    private String education;
    @Column(name = "Occupation")
    private String occupation;
    @Column(name = "Income")
    private String income;
    @Column(name = "Marital_Status")
    private String maritalStatus;
    @Column(name = "About_Me")
    private String aboutMe;
    @Column(name = "religion_pref")
    private String religionPref;

//    @Column(name = "Profile_Photo")
//    @Lob // Indicates that the field should be treated as large object
//    private byte[] profilePhoto;

    @Column(name = "user_id")
    private Long userId;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }


//    public byte[] getProfilePhoto() {
//        return profilePhoto;
//    }
//
//    public void setProfilePhoto(byte[] profilePhoto) {
//        this.profilePhoto = profilePhoto;
//    }

    public int getAge() {
        calculateAge();
        return age;
    }

    private void calculateAge() {
        if (dateOfBirth != null) {
            // Convert Date to String in the format "yyyy-dd-MM"
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
            String formattedDate = formatter.format(dateOfBirth);

            // Convert the formatted date string to LocalDate
            LocalDate birthDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-dd-MM"));
            LocalDate currentDate = LocalDate.now();

            Period period = Period.between(birthDate, currentDate);
            int years = period.getYears();
            int months = period.getMonths();
            int days = period.getDays();

            age = years ;
        } else {
            age = 0;
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getReligionPref() {
        return religionPref;
    }

    public void setReligionPref(String religionPref) {
        this.religionPref = religionPref;
    }

}
