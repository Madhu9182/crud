package org.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springboot.exception.ResourceNotFoundException;
import org.springboot.model.RegistrationForm;
import org.springboot.repository.RegistrationRepository;


@Service
public class RegistrationServiceImpl implements RegistrationService{

	private RegistrationRepository registrationRepository;
	
	public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
		super();
		this.registrationRepository = registrationRepository;
	}
	
	@Override
	public RegistrationForm saveRegistrationForm(RegistrationForm registrationForm) {
		return this.registrationRepository.save(registrationForm);
	}
	
	@Override
	public List<RegistrationForm> getRegistration() {
		// TODO Auto-generated method stub
		return registrationRepository.findAll();
	}
	
/*
	@Override
	public RegistrationForm getRegistrationById(long userId) {
		// TODO Auto-generated method stub
		Optional <RegistrationForm> optional = registrationRepository.findById(userId);
		RegistrationForm registrationForm = null;
		if(optional.isPresent()) {
			registrationForm = optional.get();
		}else {
			throw new RuntimeException("Registration not found for id :: " + userId);
		}
		return registrationForm;
	}
*/
	@Override
	public Optional<RegistrationForm> findRegistrationById(long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		if(!this.registrationRepository.existsById(userId)) {
			throw new ResourceNotFoundException("No record found by that id :: " + userId);
		}else {
			return this.registrationRepository.findById(userId);
		}
	}

	@Override
	public RegistrationForm updateRegistration(RegistrationForm registrationForm) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		if(!this.registrationRepository.existsById(registrationForm.getUserId())) {
			throw new ResourceNotFoundException("No record found by that id");
		}else {
			return this.registrationRepository.save(registrationForm);
		}
	}

	@Override
	public RegistrationForm deleteRegistrationById(long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
	    if (!this.registrationRepository.existsById(userId)) {
	    	throw new ResourceNotFoundException("Oops! No Student found for given Id");
	    } else {
	      this.registrationRepository.deleteById(userId);
	      return this.deleteRegistrationById(userId);
	    }
	}

/*
	@Override
	public void deleteRegistrationById(long id) {
		// TODO Auto-generated method stub
		this.registrationRepository.deleteById(id);
	}
*/
	

}
