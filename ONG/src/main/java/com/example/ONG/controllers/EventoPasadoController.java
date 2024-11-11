package com.example.ONG.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import com.example.ONG.entities.EventoPasado;
import com.example.ONG.repositories.EventoPasadoRepository;
import com.example.ONG.utils.ImageUtil;

@RestController
@RequestMapping("/api/eventospasados")
public class EventoPasadoController {
    @Autowired
    private EventoPasadoRepository eventoPasadoRepository;

    @GetMapping
    public Iterable<EventoPasado> findAll(){
        return eventoPasadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public EventoPasado findById(@PathVariable Long id){
        return eventoPasadoRepository.findById(id).get();
    }

    @GetMapping("/ultimos")
    public Iterable<EventoPasado> findUltimos(){
        Iterable<EventoPasado> lista = eventoPasadoRepository.findAll();
        List<EventoPasado> ultimos = new ArrayList<>();
        lista.forEach(ultimos::add);
        Comparator<EventoPasado> comp = (EventoPasado a, EventoPasado b) -> {
            return b.getFecha().compareTo(a.getFecha());
        };
        Collections.sort(ultimos, comp);
        List<EventoPasado> ultimos2 = new ArrayList<>();
        for(int i=0; i< 3; i++){
            ultimos2.add(ultimos.get(i));

        }
        return ultimos2;
        
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoPasado crear(
        @RequestParam("titulo") String titulo,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("participantes") String participantes,
        @RequestParam("lugar") String lugar,
        @RequestParam("fecha") LocalDate fecha, 
        @RequestParam("imagen") MultipartFile imagen
    ){
        EventoPasado event=null;
        try {
			event=new EventoPasado(
                null,
                titulo,
                descripcion,
                participantes,
                lugar,
                fecha, 
                ImageUtil.compressImage(imagen.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return eventoPasadoRepository.save(event);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id){
        eventoPasadoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public EventoPasado actualizar(@PathVariable Long id, @RequestBody EventoPasado eventoPasado){
        return eventoPasadoRepository.save(eventoPasado);
    
    }
}
