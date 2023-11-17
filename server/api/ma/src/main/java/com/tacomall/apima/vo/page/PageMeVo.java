package com.tacomall.apima.vo.page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tacomall.common.entity.member.MemberWallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageMeVo {

  private MemberWallet wallet;

}
