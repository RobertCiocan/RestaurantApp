package com.gastrosfera.ospatar.model;

import com.gastrosfera.shared.v1.model.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ospatar")
@Getter
@Setter
public class Ospatar extends BaseModel {

    @Id
    private String cnp;

    @Column
    private Long id;

    @Column
    private String nume;

    @Column
    private String prenume;

    @Column
    private String email;

    @Column
    private String externalUid;

}
