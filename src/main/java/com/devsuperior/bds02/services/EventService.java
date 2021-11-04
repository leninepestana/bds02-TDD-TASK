package com.devsuperior.bds02.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ControllerNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Transactional(readOnly = true)
	public List<EventDTO> findAll() { 
 		List<Event>  list = eventRepository.findAll();
 		return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public EventDTO findById(Long id) {
		Optional<Event> obj = eventRepository.findById(id);
		Event entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entity not found"));
		return new EventDTO(entity);
	}

}
