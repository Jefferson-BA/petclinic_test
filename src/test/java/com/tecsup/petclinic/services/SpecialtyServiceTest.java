package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

	@Autowired
	private SpecialtyService specialtyService; // <-- Asegúrate de crear esta clase primero

	/**
	 * Prueba de Creación (Create)
	 * Sigue la misma lógica de PetServiceTest.testCreatePet
	 */
	@Test
	public void testCreateSpecialty() {

		String SPEC_NAME = "Ophthalmology"; // Oftalmología
		String SPEC_OFFICE = "Room 101";
		int SPEC_H_OPEN = 9;
		int SPEC_H_CLOSE = 17;

		SpecialtyDTO specialtyDTO = SpecialtyDTO.builder() // <-- Asegúrate de crear SpecialtyDTO primero
				.name(SPEC_NAME)
				.office(SPEC_OFFICE)
				.hOpen(SPEC_H_OPEN)
				.hClose(SPEC_H_CLOSE)
				.build();

		SpecialtyDTO newSpecialtyDTO = this.specialtyService.create(specialtyDTO);

		log.info("Specialty CREATED :" + newSpecialtyDTO.toString()); // Mismo estilo de log

		assertNotNull(newSpecialtyDTO.getId());
		assertEquals(SPEC_NAME, newSpecialtyDTO.getName());
		assertEquals(SPEC_OFFICE, newSpecialtyDTO.getOffice());
		assertEquals(SPEC_H_OPEN, newSpecialtyDTO.getHOpen());
		assertEquals(SPEC_H_CLOSE, newSpecialtyDTO.getHClose());
	}

	/**
	 * Prueba de Actualización (Update)
	 * Sigue la misma lógica de PetServiceTest.testUpdatePet
	 */
	@Test
	public void testUpdateSpecialty() {


		String SPEC_NAME = "Dermatology";
		String SPEC_OFFICE = "Room 202";
		int SPEC_H_OPEN = 8;
		int SPEC_H_CLOSE = 15;


		String UP_SPEC_NAME = "Updated Dermatology";
		String UP_SPEC_OFFICE = "Updated Room 202";
		int UP_SPEC_H_OPEN = 10;
		int UP_SPEC_H_CLOSE = 18;

		SpecialtyDTO specialtyDTO = SpecialtyDTO.builder()
				.name(SPEC_NAME)
				.office(SPEC_OFFICE)
				.hOpen(SPEC_H_OPEN)
				.hClose(SPEC_H_CLOSE)
				.build();


		log.info(">" + specialtyDTO);
		SpecialtyDTO specDTOCreated = this.specialtyService.create(specialtyDTO);
		log.info(">>" + specDTOCreated);




		specDTOCreated.setName(UP_SPEC_NAME);
		specDTOCreated.setOffice(UP_SPEC_OFFICE);
		specDTOCreated.setHOpen(UP_SPEC_H_OPEN);
		specDTOCreated.setHClose(UP_SPEC_H_CLOSE);


		SpecialtyDTO upgradeSpecDTO = this.specialtyService.update(specDTOCreated);
		log.info(">>>>" + upgradeSpecDTO);


		assertEquals(UP_SPEC_NAME, upgradeSpecDTO.getName());
		assertEquals(UP_SPEC_OFFICE, upgradeSpecDTO.getOffice());
		assertEquals(UP_SPEC_H_OPEN, upgradeSpecDTO.getHOpen());
		assertEquals(UP_SPEC_H_CLOSE, upgradeSpecDTO.getHClose());
	}

}