package com.devsuperior.bds02.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.EntityNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = cityRepository.findAll();
		
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
				
		/*
		List<CityDTO> listDto = new ArrayList<>();
		for (City c : list) {
			listDto.add(new CityDTO(c));
		}
		
		return listDto;
		*/
	}

	@Transactional(readOnly = true)
	public CityDTO findById(Long id) {
		Optional<City> obj = cityRepository.findById(id);
		City entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CityDTO(entity);
	}
}
