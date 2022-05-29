package com.hekai.backend.entites;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Entity
@Table(name = "producttypes", schema = "mydatabase")
public class ProductTypes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "parent_id")
    private int parentId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Basic
    @Column(name = "status")
    private byte status;
    @Basic
    @Column(name = "level")
    private Integer level;
    @Basic
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "update")
    private Timestamp update;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdate() {
        return update;
    }

    public void setUpdate(Timestamp update) {
        this.update = update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTypes that = (ProductTypes) o;
        return id == that.id && parentId == that.parentId && status == that.status && Objects.equals(name, that.name) && Objects.equals(sortOrder, that.sortOrder) && Objects.equals(level, that.level) && Objects.equals(created, that.created) && Objects.equals(update, that.update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, sortOrder, status, level, created, update);
    }

    public List<ProductTypes> getChildren() {
        return children;
    }

    public void setChildren(List<ProductTypes> children) {
        this.children = children;
    }

    @Transient
    private List<ProductTypes> children;
}
