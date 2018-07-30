package com.sunat.sunasis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "version")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long number;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "complete")
    private boolean complete;

    public Version() {
    }

    public Version(long number, Date date, boolean complete) {
        this.number = number;
        this.date = date;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
