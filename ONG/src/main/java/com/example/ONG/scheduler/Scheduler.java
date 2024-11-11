package com.example.ONG.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.ONG.services.EventoPasadoService;

@Service
public class  Scheduler {
    @Autowired
    private EventoPasadoService service;
    @Scheduled(cron= "0 0 0 * * ?")
    public void scheduleToDelete(){
        service.deleteExpiringEvent();
    }
}
