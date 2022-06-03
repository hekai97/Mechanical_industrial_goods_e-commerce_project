package com.example.mechanical_industrial_goods_eommerce_project_for_android.models;

import java.util.List;

public class Param {

    /**
     * id
     */
    private Integer id;
    /**
     * parent_id
     */
    private Integer parent_id;
    /**
     * name
     */
    private String name;
    /**
     * status
     */
    private int status;
    /**
     * sort_order
     */
    private Integer sort_order;
    /**
     * level
     */
    private Integer level;
    /**
     * created
     */
    private String created;
    /**
     * updated
     */
    private String updated;
    /**
     * children
     */
    private List<?> children;

    private boolean pressed = false;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParent_id() {
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

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public Integer getLevel() {
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

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
