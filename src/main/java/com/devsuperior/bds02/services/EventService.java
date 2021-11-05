package com.devsuperior.bds02.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ControllerNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	
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
	
	@Transactional
	public EventDTO insert(EventDTO eventDto) {
		Event entity = new Event();
		copyDtoToEntity(eventDto, entity);
		entity = eventRepository.save(entity);
		return new EventDTO(entity);
	}

	@Transactional
	public EventDTO update(Long id, EventDTO eventDto) {
		try {
			Event entity = eventRepository.getOne(id);
			copyDtoToEntity(eventDto, entity);
			entity = eventRepository.save(entity);
			return new EventDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	private void copyDtoToEntity(EventDTO eventDto, Event entity) {
		entity.setName(eventDto.getName());
		entity.setDate(eventDto.getDate());
		entity.setUrl(eventDto.getUrl());
		entity.setCity(cityRepository.getOne(eventDto.getCityId()));
		
	}

}
