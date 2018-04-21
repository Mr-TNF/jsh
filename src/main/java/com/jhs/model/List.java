package com.jhs.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author: TangNengFa
 * @descption: 订单表
 * @create: 2018-04-13-13-24
 **/
@XmlRootElement
@Entity
@Table(name = "t_list")
public class List {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator( name = "uuid2", strategy = "uuid2")
    @Column(length = 64)
    private String id;

    @NotEmpty
    @Column(length = 3)
    private int type;

    @NotEmpty
    @Column(length = 64)
    private String name;

    @NotEmpty
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne(cascade = CascadeType.ALL)                               //指定多对一关系
    @JoinColumn(name="userId")
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "List{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }
}
