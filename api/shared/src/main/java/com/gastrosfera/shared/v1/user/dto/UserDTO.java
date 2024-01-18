package com.gastrosfera.shared.v1.user.dto;

import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.constraint.PutValidation;
import com.gastrosfera.shared.v1.model.Identifiable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Identifiable<Long> {

    @Null(groups = {PostValidation.class, PutValidation.class})
    private Long idUser;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String username;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 255, groups = {PostValidation.class, PutValidation.class})
    private String password;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String role;

    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getIdentifier() {
        return idUser;
    }
}
