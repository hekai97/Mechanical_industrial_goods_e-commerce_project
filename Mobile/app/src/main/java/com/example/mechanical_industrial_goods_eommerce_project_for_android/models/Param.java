package com.example.mechanical_industrial_goods_eommerce_project_for_android.models;

import java.util.List;

public class Param {

    private int id;
    private int parent_id;
    private String name;
    private boolean status;
    private int sort_order;
    private int level;
    private String created;
    private String updated;

    private boolean pressed = false;

    public Param() {
    }

    public Param(int id, int parent_id, String name, boolean status, int sort_order, int level, String created, String updated) {
        this.id = id;
        this.parent_id = parent_id;
        this.name = name;
        this.status = status;
        this.sort_order = sort_order;
        this.level = level;
        this.created = created;
        this.updated = updated;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
