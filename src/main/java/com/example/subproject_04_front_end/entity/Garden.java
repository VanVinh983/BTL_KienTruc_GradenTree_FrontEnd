package com.example.subproject_04_front_end.entity;

import javax.validation.constraints.Size;

public class Garden extends BaseEntity {
    private static final long serialVersionUID = -8254094383736912662L;
    @Size(min = 1,message = "Vui lòng nhập tên vườn")
    private String name;
    @Size(min = 1,message = "Vui lòng nhập địa chỉ vườn")
    private String address;
    @Size(min = 1,message = "Vui lòng nhập hình ảnh vườn")
    private String image;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Garden(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public Garden() {
    }

    @Override
    public String toString() {
        return "Garden{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
