package com.gastrosfera.shared.v1.ospatar.dto;

import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.constraint.PutValidation;
import com.gastrosfera.shared.v1.model.Identifiable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OspatarDTO implements Identifiable<String> {

    @NotBlank(groups = {PostValidation.class, PutValidation.class})
    @Size(min = 13, max = 13, groups = {PostValidation.class, PutValidation.class})
    private String cnp;

    @Null(groups = {PostValidation.class, PutValidation.class})
    private Integer id;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String nume;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String prenume;

    // FIXME: email regex
    @Size(max = 70, groups = {PostValidation.class, PutValidation.class})
    @Pattern(regexp = "", groups = {PostValidation.class, PutValidation.class})
    private String email;

    private Boolean isActive;

    public String getIdentifier() {
        return cnp;
    }

}
