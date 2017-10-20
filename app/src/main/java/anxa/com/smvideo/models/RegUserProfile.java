package anxa.com.smvideo.models;

/**
 * Created by angelaanxa on 10/17/2017.
 */

public class RegUserProfile {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String gender;
    private String country;
    private int initialWeight = 0;
    private int targetWeight = 0;
    private String objective;
    private String motivation;
    private String minutesSpent;
    private String profession;
    private boolean receiveNewsLetter;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    private int sid;

    public boolean isReceiveNewsLetter() {
        return receiveNewsLetter;
    }

    public String getRegId() {
        return RegId;
    }

    public void setRegId(String regId) {
        RegId = regId;
    }

    public String getAJRegNo() {
        return AJRegNo;
    }

    public void setAJRegNo(String AJRegNo) {
        this.AJRegNo = AJRegNo;
    }

    private String RegId;
    private String AJRegNo;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean getReceiveNewsLetter() {
        return receiveNewsLetter;
    }

    public void setReceiveNewsLetter(boolean receiveNewsLetter) {
        this.receiveNewsLetter = receiveNewsLetter;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(int initialWeight) {
        this.initialWeight = initialWeight;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getMinutesSpent() {
        return minutesSpent;
    }

    public void setMinutesSpent(String minutesSpent) {
        this.minutesSpent = minutesSpent;
    }
}
