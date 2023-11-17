package com.tacomall.common.bo.member;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberChainIdsBo {
    private Integer memberId;
    private LocalDateTime updateTime;
}