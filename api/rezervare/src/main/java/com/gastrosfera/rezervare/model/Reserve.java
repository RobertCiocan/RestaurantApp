package com.gastrosfera.rezervare.model;

import com.gastrosfera.shared.v1.model.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rezervare")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserve extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    @Column
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
