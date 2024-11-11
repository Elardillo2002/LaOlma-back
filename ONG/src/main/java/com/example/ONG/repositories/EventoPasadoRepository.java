package com.example.ONG.repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ONG.entities.EventoPasado;

import jakarta.transaction.Transactional;

public interface EventoPasadoRepository extends CrudRepository<EventoPasado, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM EventoPasado e WHERE e.fecha < CURRENT_DATE")
    void deleteEventsWithExpiredDate();



}
