package com.mamalimomen.domains;

import com.mamalimomen.base.controller.utilities.InValidDataException;
import com.mamalimomen.base.domains.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "creditCard" ,uniqueConstraints = {@UniqueConstraint(name = "unique_accountNumber_ownerID", columnNames = {"accountNumber", "ownerID"})})
@NamedQueries({
        @NamedQuery(name = "CreditCard.findAll", query = "select cc from CreditCard cc"),
        @NamedQuery(name = "CreditCard.findByOwner", query = "select cc from CreditCard cc where cc.ownerID =?1")

})
public class CreditCard extends BaseEntity<Long> implements Comparable<CreditCard> {

    @Transient
    private static long count = 4;

    @Column(nullable = false, updatable = false)
    private String accountNumber;

    @Column(nullable = false, updatable = false)
    private Long ownerID;

    public CreditCard() {
        this.setId(count);
        count++;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) throws InValidDataException {
        if (!accountNumber.matches("\\d{10}")) {
            throw new InValidDataException("Account Number");
        }
        this.accountNumber = accountNumber;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CreditCard creditCard = (CreditCard) obj;
        return this.hashCode() == creditCard.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", getAccountNumber());
    }

    @Override
    public int compareTo(CreditCard cc) {
        return this.getAccountNumber().compareTo(cc.getAccountNumber());
    }
}
