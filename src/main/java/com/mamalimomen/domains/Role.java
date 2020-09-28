package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.controller.utilities.InValidDataException;

import javax.persistence.*;

@Entity
@Table(name = "role",schema = "HW11_Two")
@NamedQueries({
        @NamedQuery(name = "Role.findAll", query = "select r from Role r"),
        @NamedQuery(name = "Role.findOneByTitle", query = "select r from Role r where r.title =?1")
})
public class Role extends BaseEntity<Long> implements Comparable<Role> {

    @Transient
    private static final long serialVersionUID = -1163425666831025092L;

    @Transient
    private static long count = 2;

    @Column(nullable = false, unique = true)
    private String title;

    public Role() {
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
        Role role = (Role) obj;
        return this.hashCode() == role.hashCode();
    }

    @Override
    public int compareTo(Role r) {
        return this.getTitle().compareTo(r.getTitle());
    }
}
