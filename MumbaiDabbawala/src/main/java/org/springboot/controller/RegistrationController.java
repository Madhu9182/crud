package org.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springboot.exception.ResourceNotFoundException;
import org.springboot.model.RegistrationForm;
import org.springboot.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin( origins = "http://localhost:3000/")
@RestController // It is used to build rest API
@RequestMapping("/api") // It is used to map web request
public class RegistrationController {
	//Call my HTTP methods -- get, post, put, delete
	@Autowired//used for dependency injection
	private RegistrationRepository registrationRepository;

	//get registration ORM information(view)
	@GetMapping("/registrationform")
	public List<RegistrationForm> getRegistrationForm(){
		return this.registrationRepository.findAll();  // To Fetch all the details
	}

	//get information with the help of Id(retrieve)
	@GetMapping("/registrationform/{id}")
	public ResponseEntity<RegistrationForm> getRegistrationById(@PathVariable(value="id") Long userId) throws ResourceNotFoundException{
		RegistrationForm registration = registrationRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("Registration not found for this id :: " + userId));
		return ResponseEntity.ok().body(registration);
	}

	//Save registration form
	@PostMapping("registrationform")
	public RegistrationForm createRegistrationForm(@RequestBody RegistrationForm registrationform) {
		return this.registrationRepository.save(registrationform); 
	}

	//update the registration information
	@PutMapping("/registrationform/{id}")
	public ResponseEntity<RegistrationForm> getRegistrationById
	(@PathVariable(value="id")Long userId, @Validated @RequestBody RegistrationForm registrationDetails) throws ResourceNotFoundException{
		RegistrationForm registration = registrationRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("Registration not found for this id :: " + userId));

		//set and get values by using getter and setter methods
	//	registration.setUserId(registrationDetails.getUserId());
		registration.setFirstname(registrationDetails.getFirstname());
		registration.setLastname(registrationDetails.getLastname());
		registration.setEmail(registrationDetails.getEmail());
		registration.setContactno(registrationDetails.getContactno());
		registration.setPassword(registrationDetails.getPassword());

		return ResponseEntity.ok(this.registrationRepository.save(registration));
	}

	//delete the information
	@DeleteMapping("/registrationform/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRegistration(@PathVariable(value="id")Long userId)throws ResourceNotFoundException{
		RegistrationForm registration = registrationRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("Registration is not found by that id :: " + userId));

		this.registrationRepository.delete(registration);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);   // To delete the particular data 
	}
}
