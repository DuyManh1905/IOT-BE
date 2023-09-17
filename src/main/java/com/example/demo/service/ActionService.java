package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Action;
import com.example.demo.dto.ActionDto;


@Service
public interface ActionService {
	List<ActionDto> getAllResult();
    void saveAction(Action action);
}
