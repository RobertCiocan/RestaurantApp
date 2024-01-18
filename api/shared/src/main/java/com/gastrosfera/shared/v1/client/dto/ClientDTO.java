package com.gastrosfera.shared.v1.client.dto;

import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.constraint.PutValidation;
import com.gastrosfera.shared.v1.model.Identifiable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Identifiable<Long> {

    @Null(groups = {PostValidation.class, PutValidation.class})
    private Long id;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String nume;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String prenume;

    @Size(max = 70, groups = {PostValidation.class, PutValidation.class})
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", groups = {PostValidation.class, PutValidation.class})
    private String email;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String phone_number;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String address;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String username;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String password;

    public Long getIdentifier() {
        return id;
    }

}