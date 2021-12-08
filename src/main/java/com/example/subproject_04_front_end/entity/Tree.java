package com.example.subproject_04_front_end.entity;

import javax.validation.constraints.Size;

public class Tree extends BaseEntity{
    private static final long serialVersionUID = 8153367163701743891L;
    @Size(min = 1,message = "Vui lòng nhập tên cây")
    private String name;
    @Size(min = 1,message = "Vui lòng nhập link ảnh")
    private String image;
    @Size(min = 1,message = "Vui lòng nhập loại cây")
    private String type;
    @Size(min = 1,message = "Vui lòng chọn vườn")
    private String idGarden;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIdGarden() {
        return idGarden;
    }
    public void setIdGarden(String idGarden) {
        this.idGarden = idGarden;
    }

    public Tree() {
    }

    public Tree(String name, String image, String type, String idGarden) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.idGarden = idGarden;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", idGarden='" + idGarden + '\'' +
                '}';
    }
}
