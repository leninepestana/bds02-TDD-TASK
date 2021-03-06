package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	/*
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() {
		
		// List<City> list = new ArrayList<>();
		// list.add(new City(1L, "Castelo Branco"));
		// list.add(new City(2L, "Vila Velha de Ródão"));
		
		List<CityDTO> list = cityService.findAll();
		return ResponseEntity.ok().body(list);
	}
	*/
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAllByName() {
		List<CityDTO> list = cityService.findAllByName();
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
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO cityDto) {
		cityDto = cityService.insert(cityDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cityDto.getId()).toUri();
		return ResponseEntity.created(uri).body(cityDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CityDTO> update(@PathVariable Long id, @RequestBody CityDTO cityDto) {
		cityDto = cityService.update(id, cityDto);
		return ResponseEntity.ok().body(cityDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CityDTO> delete(@PathVariable Long id) {
		cityService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
