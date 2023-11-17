package com.tacomall.apima.dto;

import lombok.Data;

@Data
public class MemberUpdateDto {
  
  private String type;

  private String nickname;

  private Integer inviteMemberId;

  private String avatar;

}
