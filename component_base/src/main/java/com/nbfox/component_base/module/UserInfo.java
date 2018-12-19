package com.nbfox.component_base.module;

public class UserInfo extends BaseBean {
    /**
     * id : 1
     * username : admin
     * telephone : 13697479764
     * photo : null
     * sex : 0
     * age : null
     * position : 0
     * profession : null
     * deviceType : 1
     * deviceNo : 87878787
     * token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTUyNDYyMjQwNH0.cEe29W56CL1cMbxKFny4fVF0ermJz79xRW-b5nu6tZg
     */

    private String id;
    private String username;
    private String telephone;
    private String photo;
    private String sex;
    private String age;
    private String position;
    private String profession;
    private String deviceType;
    private String deviceNo;
    private String token;
    private String tokenMeetting;
    private String pushtoken;
    private String campanyName;

    private String fullName;
    private String auditPermission;
    private String managePermission;

    private String isOnline;
    private String washPermission;
    private String washCarPermission;
    private String orgName;
    private String airAuditPermission;
    private int IsNeedFree;

    public int getIsNeedFree() {
        return IsNeedFree;
    }

    public void setIsNeedFree(int isNeedFree) {
        IsNeedFree = isNeedFree;
    }

    public String getAirAuditPermission() {
        return airAuditPermission;
    }

    public void setAirAuditPermission(String airAuditPermission) {
        this.airAuditPermission = airAuditPermission;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getWashPermission() {
        return washPermission;
    }

    public void setWashPermission(String washPermission) {
        this.washPermission = washPermission;
    }

    public String getWashCarPermission() {
        return washCarPermission;
    }

    public void setWashCarPermission(String washCarPermission) {
        this.washCarPermission = washCarPermission;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getCampanyName() {
        return campanyName;
    }

    public void setCampanyName(String campanyName) {
        this.campanyName = campanyName;
    }

    public String getTokenMeetting() {
        return tokenMeetting;
    }

    public void setTokenMeetting(String tokenMeetting) {
        this.tokenMeetting = tokenMeetting;
    }



    public String getAuditPermission() {
        return auditPermission;
    }

    public void setAuditPermission(String auditPermission) {
        this.auditPermission = auditPermission;
    }

    public String getManagePermission() {
        return managePermission;
    }

    public void setManagePermission(String managePermission) {
        this.managePermission = managePermission;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPushtoken() {
        return pushtoken;
    }

    public void setPushtoken(String pushtoken) {
        this.pushtoken = pushtoken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
