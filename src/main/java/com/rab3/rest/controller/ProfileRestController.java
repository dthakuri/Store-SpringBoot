package com.rab3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rab3.controller.dto.ApplicationVO;
import com.rab3.controller.dto.ProfileDTO;
import com.rab3.service.ProfileService;

@RestController
@RequestMapping("/api2")
public class ProfileRestController {

	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/profiles")
	public ApplicationVO register(@RequestBody ProfileDTO profileDTO) {
		profileService.persist(profileDTO);
		ApplicationVO applicationVO = new ApplicationVO();
		applicationVO.setCode("202");
		applicationVO.setMessage("Prfile is created");
		return applicationVO;
		
	}
	
	@GetMapping("/profiles")
	public List<ProfileDTO> showProfiles() {
		List<ProfileDTO> profileDTOs = profileService.findProfiles();
		return profileDTOs;
	}
	
	@PutMapping("/profiles")
	public ApplicationVO update(@RequestBody ProfileDTO profileDTO) {
		profileService.updateProfila(profileDTO);
		ApplicationVO applicationVO = new ApplicationVO();
		applicationVO.setCode("202");
		applicationVO.setMessage("Prfile is updated");
		return applicationVO;
	}
	
	@GetMapping("/profiles/{pid}")
	public ProfileDTO getByID(@PathVariable int pid) {
		ProfileDTO dto= profileService.findProfileById(pid);
		return dto;
	}
	
	@DeleteMapping("/profiles/{pid}")
	public ApplicationVO delete(@PathVariable int pid) {
		profileService.deleteById(pid);
		ApplicationVO applicationVO = new ApplicationVO();
		applicationVO.setCode("202");
		applicationVO.setMessage("Prfile is deleted");
		return applicationVO;
	}
	
}
