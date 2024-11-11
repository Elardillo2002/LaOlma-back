package com.example.ONG.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ONG.entities.EventoFuturo;
import com.example.ONG.entities.EventoPasado;
import com.example.ONG.repositories.EventoFuturoRepository;

@RestController
@RequestMapping("/api/eventosfuturos")
public class EventoFuturoController {
    
    @Autowired
    private EventoFuturoRepository eventoFuturoRepository;

    @GetMapping
    public Iterable<EventoFuturo> findAll(){
        return eventoFuturoRepository.findAll();
    }

    @GetMapping("/{id}")
    public EventoFuturo findById(@PathVariable Long id){
        return eventoFuturoRepository.findById(id).get();
    }

    @GetMapping("/proximos")
    public Iterable<EventoFuturo> findProximos(){
        Iterable<EventoFuturo> lista = eventoFuturoRepository.findAll();
        List<EventoFuturo> proximos = new ArrayList<>();
        lista.forEach(proximos::add);
        Comparator<EventoFuturo> comp = (EventoFuturo a, EventoFuturo b) -> {
            return a.getFecha().compareTo(b.getFecha());
        };
        Collections.sort(proximos, comp);
        List<EventoFuturo> ultimos2 = new ArrayList<>();
        for(int i=0; i< 3; i++){
            ultimos2.add(proximos.get(i));

        }
        return ultimos2;
        
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoFuturo crear(@RequestBody EventoFuturo eventoPasado){
        return eventoFuturoRepository.save(eventoPasado);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id){
        eventoFuturoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public EventoFuturo actualizar(@PathVariable Long id, @RequestBody EventoFuturo eventoPasado){
        return eventoFuturoRepository.save(eventoPasado);
    
    }
}
