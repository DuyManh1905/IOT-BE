package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float temperature;

    private Float humidity;

    private Float light;

//    @Column(name = "led_1")
//    private Integer led1;
//
//    @Column(name = "led_2")
//    private Integer led2;
//
//    @Column(name = "led_3")
//    private Integer led3;
    
    private Long time;
    
//    private Float dobui;
    
    private Float gas;
}
