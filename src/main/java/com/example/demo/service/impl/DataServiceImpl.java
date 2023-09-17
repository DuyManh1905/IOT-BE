package com.example.demo.service.impl;

import com.example.demo.domain.Data;
import com.example.demo.dto.DataDto;
import com.example.demo.repository.DataRepository;
import com.example.demo.service.DataService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    private final DataRepository dataRepository;
    private final EntityManager entityManager;

    @Autowired
    public DataServiceImpl(EntityManager entityManager,
                           DataRepository dataRepository)
    {
        this.dataRepository = dataRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<DataDto> get7LastestResult()
    {
        //Đếm số lượng bản ghi
        long numberOfRecord = this.dataRepository.count();

        Query query = entityManager.createQuery("Select new com.example.demo.dto.DataDto(d) from Data d ").setFirstResult((int)numberOfRecord - 7).setMaxResults(7);
        return query.getResultList();
    }

    @Override
    public List<DataDto> getAllResult()
    {
        Query query = entityManager.createQuery("Select new com.example.demo.dto.DataDto(d) from Data d order by d.id desc ");
        return query.getResultList();
    }

    @Override
    public void saveData(Data data)
    {
        this.dataRepository.save(data);
    }
}
