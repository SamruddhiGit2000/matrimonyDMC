package com.vivah.app.model;



import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
        name = "user_interests",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "interested_in_user_id"})
)
public class UserInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "interested_in_user_id", nullable = false)
    private Long interestedInUserId;

    @Column(name = "interest_date")
    private Timestamp interestDate;

    public UserInterests() {}

    public UserInterests(Long userId, Long interestedInUserId) {
        this.userId = userId;
        this.interestedInUserId = interestedInUserId;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getInterestedInUserId() {
        return interestedInUserId;
    }

    public Timestamp getInterestDate() {
        return interestDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setInterestedInUserId(Long interestedInUserId) {
        this.interestedInUserId = interestedInUserId;
    }


    public void setInterestDate(Timestamp interestDate) {
        this.interestDate = interestDate;
    }
}
