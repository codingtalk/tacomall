package com.tacomall.apima.dto;

import lombok.Data;

@Data
public class AddressUpdateDto {

  private Integer id;
  
  private String province;

  private String city;

  private String district;

  private String detail;

  private String mobile;

}
