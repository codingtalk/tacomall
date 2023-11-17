package com.tacomall.mqrabbitmq.strategy;

import com.baomidou.mybatisplus.extension.service.IService;

import com.tacomall.common.dto.MemberQueueTaskDto;
import com.tacomall.common.entity.member.Member;

public interface MemberQueueTaskStrategy extends IService<Member> {

    Object doTask(MemberQueueTaskDto memberQueueTaskDto);

}