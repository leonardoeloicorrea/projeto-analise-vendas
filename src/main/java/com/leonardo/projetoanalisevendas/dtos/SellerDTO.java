package com.leonardo.projetoanalisevendas.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardo.projetoanalisevendas.enums.Gender;
import com.leonardo.projetoanalisevendas.models.Seller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @NotBlank
    private String name;

    @Positive(message = "Age must be greater than 0")
    private Integer age;

    @NotBlank(message = "Required field")
    private String gender;

    @Size(min = 11, max = 11, message = "The cpf must have 11 characters")
    @NotNull(message = "Required field")
    private String cpf;

    @Size(min = 10, max = 20, message = "Mobile number must be between 10 and 20 characters")
    @NotBlank(message = "Required field")
    private String phoneNumber;

    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    @NotBlank(message = "Required field")
    @Email(message = "Invalid email")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Required field")
    private LocalDate dateOfBirth;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Required field")
    private LocalDate hiringDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime lastUpdate;
    
    public SellerDTO(Seller entity) {
        id = entity.getId();
        name = entity.getName();
        dateOfBirth = entity.getDateOfBirth();
        LocalDate currentDate = LocalDate.now();
        age = LocalDate.now().getYear() - entity.getDateOfBirth().getYear();
        if(dateOfBirth.getMonthValue() > currentDate.getMonthValue()  || (dateOfBirth.getMonthValue() == currentDate.getMonthValue() && dateOfBirth.getDayOfMonth() > currentDate.getDayOfMonth())){
            age -= 1;
        }
        gender = entity.getGender().toString();
        cpf = entity.getCpf();
        phoneNumber = entity.getPhoneNumber();
        email = entity.getEmail();
        hiringDate = entity.getHiringDate();
        lastUpdate = entity.getLastUpdate();
    }

    public Seller convertDtoToEntity (SellerDTO sellerDTO) {
        Seller entity = new Seller();
        entity.setName(sellerDTO.getName());
        entity.setGender(sellerDTO.gender.equalsIgnoreCase("MASCULINE") ? Gender.MASCULINE : Gender.FEMININE);
        entity.setCpf(sellerDTO.getCpf());
        entity.setPhoneNumber(sellerDTO.getPhoneNumber());
        entity.setEmail(sellerDTO.getEmail());
        entity.setDateOfBirth(sellerDTO.getDateOfBirth());
        entity.setHiringDate(sellerDTO.getHiringDate());

        return entity;
    }

    public Seller updateEntity (Seller entity, SellerDTO sellerDTO) {
        entity.setName(sellerDTO.getName());
        entity.setGender(sellerDTO.gender.equalsIgnoreCase("MASCULINE") ? Gender.MASCULINE : Gender.FEMININE);
        entity.setCpf(sellerDTO.getCpf());
        entity.setPhoneNumber(sellerDTO.getPhoneNumber());
        entity.setEmail(sellerDTO.getEmail());
        entity.setDateOfBirth(sellerDTO.getDateOfBirth());
        entity.setHiringDate(sellerDTO.getHiringDate());

        return entity;
    }

}
