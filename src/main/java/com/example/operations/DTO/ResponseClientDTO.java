package com.example.operations.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseClientDTO {

    String firstName;
    String lastName;
    String numberOfAccount;
    BigDecimal amountOfAccount;

}
