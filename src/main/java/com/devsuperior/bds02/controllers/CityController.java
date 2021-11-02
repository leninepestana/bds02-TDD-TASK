package com.devsuperior.bds02.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() {
		/*
		List<City> list = new ArrayList<>();
		list.add(new City(1L, "Castelo Branco"));
		list.add(new City(2L, "Vila Velha de Ródão"));
		*/
		List<CityDTO> list = cityService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CityDTO> findById(@PathVariable Long id) {

		CityDTO cityDto = cityService.findById(id);
		/*
		List<City> list = new ArrayList<>();
		list.add(new City(1L, "Castelo Branco"));
		list.add(new City(2L, "Vila Velha de Ródão"));
		*/		
		return ResponseEntity.ok().body(cityDto);
	}
}