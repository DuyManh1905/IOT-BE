package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Action;

public interface ActionRepository extends JpaRepository<Action, Integer>{

}
