package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Action;
import com.example.demo.dto.ActionDto;
import com.example.demo.repository.ActionRepository;
import com.example.demo.service.ActionService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class ActionServiceImpl implements ActionService {
	
	private final ActionRepository actionRepository;
	private final EntityManager entityManager;
	
	
	@Autowired
	public ActionServiceImpl(ActionRepository actionRepository, EntityManager entityManager) {
		this.actionRepository = actionRepository;
		this.entityManager = entityManager;
	}

	@Override
	public List<ActionDto> getAllResult() {
		Query query = entityManager.createQuery("Select new com.example.demo.dto.ActionDto(d) from Action d order by d.id desc ");
        return query.getResultList();
	}

	@Override
	public void saveAction(Action action) {
		this.actionRepository.save(action);
	}



}
