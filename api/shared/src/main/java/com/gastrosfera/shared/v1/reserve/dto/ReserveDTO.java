package com.gastrosfera.shared.v1.reserve.dto;

import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.constraint.PutValidation;
import com.gastrosfera.shared.v1.model.Identifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDTO implements Identifiable<String> {
    @NotBlank(groups = {PostValidation.class, PutValidation.class})
    @Size(min = 1, max = 13, groups = {PostValidation.class, PutValidation.class})
    private String masa;

    @NotBlank(groups = {PostValidation.class})
    @Size(max = 50, groups = {PostValidation.class, PutValidation.class})
    private String name;
    @NotBlank(groups = {PostValidation.class})
    @Pattern(regexp = "^07\\d{8}$", message = "Phone number must start with '07' and have a total of 10 digits", groups = {PostValidation.class, PutValidation.class})
    private String phone;

    @NotNull(groups = {PostValidation.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;


    @NotNull(groups = {PostValidation.class})
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time time;

    @NotNull(groups = {PostValidation.class})
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time time_end;

    @Positive(groups = {PostValidation.class})
    @Max(value = 8, groups = {PostValidation.class, PutValidation.class})
    private Integer guests;
    @Size(max = 255, groups = {PostValidation.class, PutValidation.class})
    private String specialRequests;
    @Override
    public String getIdentifier() {
        return masa;
    }



}
