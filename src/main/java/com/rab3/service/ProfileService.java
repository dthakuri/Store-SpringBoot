package com.rab3.service;

import java.util.List;

import com.rab3.controller.dto.ProfileDTO;

public interface ProfileService {
	public abstract String findPassword(String email);

	String persist(ProfileDTO profileDTO);

	ProfileDTO findProfileById(int aid);

	String updateProfila(ProfileDTO profileDTO);

	byte[] findPhotoById(int aid);

	ProfileDTO auth(String username, String password);

	long findCounts();

	List<ProfileDTO> findProfiles();


	ProfileDTO search(String email);

	 void updateImage(byte[] image);

	String deleteById(int aid);

	

}
