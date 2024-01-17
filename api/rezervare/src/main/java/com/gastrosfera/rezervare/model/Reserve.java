package com.gastrosfera.rezervare.model;

import com.gastrosfera.shared.v1.model.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rezervare")
@Getter
@Setter
public class Reserve extends BaseModel {
    @Id
    private String masa;
    @Column
    private String name;
    @Column
    private String phone;

    @Column
    private Date data;

    @Column
    private Time time;

    @Column
    private Time time_end;
    @Column
    private Integer guests;

    @Column
    private String specialRequests;

}
