package org.springboot;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springboot.exception.ResourceNotFoundException;
import org.springboot.model.RegistrationForm;
import org.springboot.repository.RegistrationRepository;

import org.springboot.service.RegistrationServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MumbaiDabbawalaApplicationTests {

	@InjectMocks
	private RegistrationServiceImpl registrationServiceImpl;
	
	@Mock
	private RegistrationRepository registrationRepository;
	
	//Add registration service layer check for valid input 
	
	@Test
	public void addRegistrationTest() {
		RegistrationForm register = new RegistrationForm();
		register.setUserId(1);
		register.setFirstname("Madhu");
		register.setLastname("Pavan");
		register.setEmail("madhu@gmail.com");
		register.setContactno("9182744118");
		register.setPassword("madhu123");
	Mockito.when(this.registrationRepository.save(register)).thenReturn(register);
		
		assertEquals(register, this.registrationServiceImpl.saveRegistrationForm(register));
	}
	
	// View all Records service layer check 
	@Test
	public void viewAllRecordsTest() {
		RegistrationForm registration = new RegistrationForm();
		registration.setUserId(1);
		registration.setFirstname("Madhu");
		registration.setLastname("pavan");
		registration.setEmail("madhu@gmail.com");
		registration.setContactno("9182744118");
		registration.setPassword("madhu123");
		
		RegistrationForm registration1 = new RegistrationForm();
		registration1.setUserId(2);
		registration1.setFirstname("Naga");
		registration1.setLastname("Venkata");
		registration1.setEmail("ngvkt@gmail.com");
		registration1.setContactno("9182744118");
		registration1.setPassword("venkata123");
		
		List<RegistrationForm> list = new ArrayList<RegistrationForm>();
		
		list.add(registration);
		list.add(registration1);
		
		Mockito.when(this.registrationRepository.findAll()).thenReturn(list);
		
		assertEquals(list, this.registrationServiceImpl.getRegistration());
	}
	
	// Update registration service layer check for valid input 
	@Test
	public void updateRegistrationFormTest() throws ResourceNotFoundException {
		RegistrationForm r = new RegistrationForm();
		r.setUserId(1);
		r.setFirstname("Madhu");
		r.setLastname("Pavan");
		r.setEmail("madhu@gmail.com");
		r.setContactno("9182744118");
		r.setPassword("madhu123");
		
		Mockito.when(this.registrationRepository.save(r)).thenReturn(r);
		
		Mockito.when(this.registrationRepository.existsById((long)1)).thenReturn(true);
		
		r.setFirstname("virat");
		
		RegistrationForm test = this.registrationServiceImpl.updateRegistration(r);
		
		assertEquals(r.getFirstname(),test.getFirstname());
	}
	
	// deleteRegistration layer check
	
	@Test
	public void deleteRegistrationTest() throws ResourceNotFoundException{
		RegistrationForm register = new RegistrationForm();
		register.setUserId(1);
		register.setFirstname("Madhu");
		register.setLastname("Pavan");
		register.setEmail("madhu@gmail.com");
		register.setContactno("9182744118");
		register.setPassword("madhu123");
		
		Mockito.when(this.registrationRepository.save(register)).thenReturn(register);
		
		Mockito.when(this.registrationRepository.existsById((long)1)).thenReturn(true);
		
		this.registrationServiceImpl.deleteRegistrationById(1);
		
		verify(this.registrationRepository,Mockito.atLeastOnce()).deleteById(1L);
	}
	
}
