package com.tacomall.apiweb.vo.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.tacomall.common.entity.member.Member;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoVo {

    private Member member;

}