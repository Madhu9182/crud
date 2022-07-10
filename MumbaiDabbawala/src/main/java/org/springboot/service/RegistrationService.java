package org.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springboot.exception.ResourceNotFoundException;
import org.springboot.model.RegistrationForm;

public interface RegistrationService {

	RegistrationForm saveRegistrationForm(RegistrationForm registrationForm);
	
	List<RegistrationForm> getRegistration();
	
	Optional<RegistrationForm> findRegistrationById(long userId)  throws ResourceNotFoundException;
	
//	RegistrationForm getRegistrationById(long userId);
	
	RegistrationForm updateRegistration(RegistrationForm registrationForm) throws ResourceNotFoundException; 
	
	RegistrationForm deleteRegistrationById(long UserId) throws ResourceNotFoundException;
}
