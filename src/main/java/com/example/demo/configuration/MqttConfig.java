package com.example.demo.configuration;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.example.demo.domain.Data;
import com.example.demo.service.DataService;
import com.google.gson.Gson;

@Configuration
public class MqttConfig {
	@Bean
	public MqttPahoClientFactory mqttPahoClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
//        options.setServerURIs(new String[]{"tcp://10.20.197.46"});
		String serverUri;
		try {
			serverUri = String.format("tcp://%s:1883", Inet4Address.getLocalHost().getHostAddress());
			options.setServerURIs(new String[] { serverUri });
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		options.setUserName("duymanh");
		String password = "20162017a";
		options.setPassword(password.toCharArray());
		options.setCleanSession(true);

		factory.setConnectionOptions(options);
		return factory;
	}

	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageProducer inbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn",
				mqttPahoClientFactory(), "#");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {
			@Autowired
			DataService dataService;

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
				System.out.println("topic :" + topic);
				if (topic.equals("dataSensorOutput")) {
					System.out.println("nhận dữ liệu thành công");
					Gson gson = new Gson();
					Data data = gson.fromJson(message.getPayload().toString(), Data.class);
					data.setTime(new Date().getTime());
					this.dataService.saveData(data);

				}
				if (topic.equals("outTopic")) {
					System.out.println("client gui du lieu");
				}
				System.out.println(message.getPayload());
			}
		};
	}

	@Bean
	public MessageChannel mqttOutBoundChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutBoundChannel")
	public MessageHandler mqttOutBound() {
		System.out.println("line 105 mqttconfig");
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttPahoClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("outTopic");
		return messageHandler;
	}
}
