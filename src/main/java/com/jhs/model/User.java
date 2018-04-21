package com.jhs.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator( name = "uuid2", strategy = "uuid2")
    @Column(length = 64)
    private String id;
    @NotEmpty
    @Column(length=64, unique=true)
    private String username;
    @NotEmpty
    @Column(length = 64)
    private String password;
    @NotNull
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
