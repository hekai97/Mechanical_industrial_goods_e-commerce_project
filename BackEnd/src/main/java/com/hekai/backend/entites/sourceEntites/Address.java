package com.hekai.backend.entites.sourceEntites;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Entity
@Table(name = "address", schema = "mydatabase")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "mobile")
    private String mobile;
    @Basic
    @Column(name = "province")
    private String province;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "district")
    private String district;
    @Basic
    @Column(name = "addr")
    private String addr;
    @Basic
    @Column(name = "zip")
    private String zip;
    @Basic
    @Column(name = "dfault")
    private Byte dfault;
    @Basic
    @Column(name = "isDel")
    private byte isDel;
    @Basic
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "updated")
    private Timestamp updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Byte getDfault() {
        return dfault;
    }

    public void setDfault(Byte dfault) {
        this.dfault = dfault;
    }

    public byte getIsDel() {
        return isDel;
    }

    public void setIsDel(byte isDel) {
        this.isDel = isDel;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return id == that.id && userId == that.userId && isDel == that.isDel && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(mobile, that.mobile) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(district, that.district) && Objects.equals(addr, that.addr) && Objects.equals(zip, that.zip) && Objects.equals(dfault, that.dfault) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, phone, mobile, province, city, district, addr, zip, dfault, isDel, created, updated);
    }
}
