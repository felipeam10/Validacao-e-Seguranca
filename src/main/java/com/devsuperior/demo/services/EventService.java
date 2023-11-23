package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.dto.RoleDTO;
import com.devsuperior.demo.dto.UserDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> page = repository.findAll(pageable);
        return page.map(x -> new EventDTO(x));
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event entity = new Event();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EventDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(new City(dto.getCityId(), null));

    }
}
