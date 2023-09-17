package com.example.demo.service;

import com.example.demo.domain.Data;
import com.example.demo.dto.DataDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataService {
    List<DataDto> get7LastestResult();
    List<DataDto> getAllResult();
    void saveData(Data data);
}
