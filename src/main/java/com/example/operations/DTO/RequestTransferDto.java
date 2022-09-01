package com.example.operations.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTransferDto {
    @NotNull
    @Pattern(regexp = "\\d{20}")
    String fromNumberAccount;
    @NotNull
    @Pattern(regexp = "\\d{20}")
    String toNumberAccount;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=16, fraction=3)
    BigDecimal transferAmount;
}
