package com.example.apartment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequestDTO {

  private String apartmentName;  // 아파트 이름
  private String address;        // 아파트 주소

}