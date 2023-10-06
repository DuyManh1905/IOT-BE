package com.example.demo.dto;

import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.example.demo.constant.SystemConstant;
import com.example.demo.domain.Action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActionDto {
    public Integer id;
    private String time;
    private String device;
    private String state;
    
    public ActionDto(Action action)
    {
        if(!ObjectUtils.isEmpty(action))
        {
            this.id = action.getId();
            this.time = SystemConstant.sdfNormal.format(new Date(action.getTime()));
            this.device = action.getDevice();
            this.state = action.getState();
        }
    }
}
