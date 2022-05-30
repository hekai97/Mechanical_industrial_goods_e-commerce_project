package com.example.mechanical_industrial_goods_eommerce_project_for_android.models;

import java.util.List;

public class Param {

    /**
     * id
     */
    private int id;
    /**
     * parent_id
     */
    private int parent_id;
    /**
     * name
     */
    private String name;
    /**
     * status
     */
    private boolean status;
    /**
     * sort_order
     */
    private int sort_order;
    /**
     * level
     */
    private int level;
    /**
     * created
     */
    private long created;
    /**
     * updated
     */
    private long updated;
    /**
     * children
     */
    private List<?> children;

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

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
