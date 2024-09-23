package com.example.apartment.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentRequest {


    private Long id;
    private String apartmentName;
    private String address;



}