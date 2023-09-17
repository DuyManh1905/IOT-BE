package com.example.demo.dto;

import com.example.demo.constant.SystemConstant;
import com.example.demo.domain.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataDto {
    private Long id;
    private Float temperature;
    private Float humidity;
    private Float light;
    private Integer led1;
    private Integer led2;
    private String time;
//    private Float dobui;

    public DataDto(Data data)
    {
        if(!ObjectUtils.isEmpty(data))
        {
            this.id = data.getId();
            this.temperature = data.getTemperature();
            this.humidity = data.getHumidity();
            this.light = data.getLight();
            this.led1 = data.getLed1();
            this.led2 = data.getLed2();
            this.time = SystemConstant.sdfNormal.format(new Date(data.getTime()));
//            this.dobui = data.getDobui();
        }
    }
}
