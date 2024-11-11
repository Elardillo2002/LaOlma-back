package com.example.ONG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ONG.repositories.EventoPasadoRepository;

@Service
public class EventoPasadoService {

    @Autowired
    private EventoPasadoRepository eventoPasadoRepository;
    public void deleteExpiringEvent(){
        eventoPasadoRepository.deleteEventsWithExpiredDate();
    }
}
