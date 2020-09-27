package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.controller.utilities.InValidDataException;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@NamedQueries({
        @NamedQuery(name = "Tag.findAll", query = "select t from Tag t"),
        @NamedQuery(name = "Tag.findOneByTitle", query = "select t from Tag t where t.title =?1")
})
public class Tag extends BaseEntity<Long> implements Comparable<Tag> {

    @Transient
    private static final long serialVersionUID = -5915834205584682883L;

    @Transient
    private static long count = 4;

    @Column(nullable = false, unique = true)
    private String title;

    public Tag() {
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

    @Override
    public String toString() {
        return String.format("%s", getTitle());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tag tag = (Tag) obj;
        return this.hashCode() == tag.hashCode();
    }

    @Override
    public int compareTo(Tag t) {
        return this.getTitle().compareTo(t.getTitle());
    }
}
