package com.devsuperior.bds02.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ControllerNotFoundException;
import com.devsuperior.bds02.services.exceptions.DatabaseException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	/*
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = cityRepository.findAll();
		
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
						
		// List<CityDTO> listDto = new ArrayList<>();
		// for (City c : list) {
		// 	listDto.add(new CityDTO(c));
		// }
		
		// return listDto;
		
	}
	*/
	
	public List<CityDTO> findAllByName() {
		List<City> list = cityRepository.findAll(Sort.by("name"));
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CityDTO findById(Long id) {
		Optional<City> obj = cityRepository.findById(id);
		City entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entity not found"));
		return new CityDTO(entity);
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		entity.setName(dto.getName());
		entity = cityRepository.save(entity);
		return new CityDTO(entity);
	}

	@Transactional
	public CityDTO update(Long id, CityDTO cityDto) {
		try {
			City entity = cityRepository.getOne(id);
			entity.setName(cityDto.getName());
			entity = cityRepository.save(entity);
			return new CityDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);			
		}
		catch(EmptyResultDataAccessException e) {
			throw new ControllerNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	
	
}
