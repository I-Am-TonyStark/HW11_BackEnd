package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.controller.utilities.InValidDataException;

import javax.persistence.*;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "select c from Category c"),
        @NamedQuery(name = "Category.findOneByTitle", query = "select c from Category c where c.title =?1")
})
public class Category extends BaseEntity<Long> implements Comparable<Category> {

    @Transient
    private static final long serialVersionUID = -6796340476955577977L;

    @Transient
    private static long count = 4;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    public Category() {
        this.setId(count);
        count++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InValidDataException {
        if (!title.matches("[a-zA-Z\\s.,&\\d\\(\\)]{3,}")) {
            throw new InValidDataException("Title");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InValidDataException {
        if (!description.matches("[a-zA-Z\\s.,&\\d\\(\\)]{10,}")) {
            throw new InValidDataException("Description");
        }
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s%nDescription: %s", getTitle(), getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return this.hashCode() == category.hashCode();
    }

    @Override
    public int compareTo(Category c) {
        return this.getTitle().compareTo(c.getTitle());
    }
}
