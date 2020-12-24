package com.framework.example.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xyliang.mqtt.utils.MqttUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xingyl
 * @since 2019-11-22
 */
@Log4j2
@RestController
public class MqttController {
	
	/**
	 * 自动注入规则：${channelName}MqttChannelAdapter
	 */
	@Autowired
	private MqttPahoMessageDrivenChannelAdapter channel1MqttChannelAdapter;


	@GetMapping(value = { "/sendMessage" })
	public String sendMessage(String topic) {
		String messageContent = "测试----" + UUID.randomUUID().toString() + "----"
				+ LocalDateTime.now();
		log.info("测试信息 {} ", messageContent);
		MqttUtils.sendMessage(topic, messageContent);
		return messageContent;
	}
	
	@GetMapping(value = { "/sendMessageByChannel" })
	public String sendMessageByChannel(String topic, String channelName) {
		String messageContent = "测试----" + UUID.randomUUID().toString() + "----"
				+ LocalDateTime.now();
		log.info("测试信息 {} ", messageContent);
		MqttUtils.sendMessage(topic, messageContent, channelName);
		return messageContent;
	}
	
	@GetMapping(value = { "/addTopic" })
	public String sendMessageByChannel(String topic, Integer qos) {
		log.info("主题 ：{} qos：{}", topic, qos);
		channel1MqttChannelAdapter.addTopic(topic, qos);
		return "操作成功";
	}
}
