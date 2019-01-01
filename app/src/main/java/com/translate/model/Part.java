package com.translate.model;

import java.io.Serializable;

public class Part implements Serializable {
    private int Id;
    private String name;
    private int IdClass;

    public Part(int id, String name, int idClass) {
        Id = id;
        this.name = name;
        IdClass = idClass;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdClass() {
        return IdClass;
    }

    public void setIdClass(int idClass) {
        IdClass = idClass;
    }

    @Override
    public String toString() {
        return "Part{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", IdClass=" + IdClass +
                '}';
    }
}
