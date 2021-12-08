package com.example.subproject_04_front_end.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -8571058520647015220L;
    @Id
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date createAt = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date updateAt = new Date();

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Date getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
