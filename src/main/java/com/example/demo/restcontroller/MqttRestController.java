package com.example.demo.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mqtt.MqttGateway;

@RestController
@RequestMapping("/mqtt/api")
@CrossOrigin(origins = "*")
public class MqttRestController
{
    private final MqttGateway mqttGateway;;

    @Autowired
    public MqttRestController(MqttGateway mqttGateway)
    {
        this.mqttGateway = mqttGateway;
    }

    @PostMapping("/led")
    public ResponseEntity<Object> publish(@RequestBody String ledControllMessage)
    {
//        JsonObject jsonObject = new Gson().fromJson(ledControllMessage, JsonObject.class);
        this.mqttGateway.sendToMqtt(ledControllMessage, "ledControlInput");
        return ResponseEntity.ok("ok");
    }
}
