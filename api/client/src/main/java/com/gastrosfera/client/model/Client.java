package com.gastrosfera.client.model;

import com.gastrosfera.shared.v1.model.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nume;

    @Column
    private String prenume;

    @Column
    private String email;

    @Column
    private String phone_number;

    @Column
    private String address;

    @Column
    private String username;

    @Column
    private String password;

    public Client(String nume,String prenume, String email,String phone_number, String address, String username,String password)
    {
        this.nume=nume;
        this.prenume=prenume;
        this.email=email;
        this.phone_number=phone_number;
        this.address=address;
        this.username=username;
        this.password=password;
    }
}
