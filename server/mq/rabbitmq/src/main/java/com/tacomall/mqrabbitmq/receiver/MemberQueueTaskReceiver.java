package com.tacomall.mqrabbitmq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tacomall.common.dto.MemberQueueTaskDto;
import com.tacomall.mqrabbitmq.factory.MemberQueueTaskFactory;

@Component
@RabbitListener(queues = "MemberQueueTaskDirectQueue")
public class MemberQueueTaskReceiver {

        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private MemberQueueTaskFactory memberQueueTaskFactory;

        @RabbitHandler
        public void process(MemberQueueTaskDto memberQueueTaskDto) {
                memberQueueTaskFactory.getStrategy(String.valueOf(memberQueueTaskDto.getType()))
                                .doTask(memberQueueTaskDto);
        }

}
