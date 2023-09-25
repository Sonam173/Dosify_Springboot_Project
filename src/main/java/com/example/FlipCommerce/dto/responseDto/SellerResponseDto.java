package com.example.FlipCommerce.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class SellerResponseDto {
    String name;
    String mobNo;
}
