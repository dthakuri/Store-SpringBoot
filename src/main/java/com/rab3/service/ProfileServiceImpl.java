package com.rab3.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.dao.ProfileDaoRepository;
import com.rab3.dao.entity.ProfileEntity;

@Service
public class ProfileServiceImpl  implements  ProfileService{
   
	@Autowired
	private ProfileDaoRepository profileDao;
	
	@Override
	public String deleteByusername(int aid){
		profileDao.deleteById(aid);
		return "success";
	}
	
	@Override
	public  ProfileDTO auth(String username, String password) {
		Optional<ProfileEntity> result=profileDao.findByUsernameAndPassword(username,password);
		ProfileDTO profileDTO=null;
		if(result.isPresent()) {
			 profileDTO=new ProfileDTO();
			BeanUtils.copyProperties(result.get(), profileDTO);	
		}
		return profileDTO;
	}
	
	
	@Override
	public byte[] findPhotoById(int aid) {
		Optional<ProfileEntity> result=profileDao.findById(aid);
		return result.get().getHphoto();
	}
	
	@Override
	public String findPassword(String email) {
		return profileDao.findByEmail(email).get().getPassword();
	}
	
	
	@Override
	public void updateImage(byte[] image) {
		ProfileEntity profileEntity = new ProfileEntity();
		profileDao.findByHphoto(image);
		profileEntity.setHphoto(image);
	}
	
	@Override
	public String persist(ProfileDTO profileDTO) {
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		try {
			profileEntity.setHphoto(profileDTO.getPhoto().getBytes());
			profileEntity.setRole("customer");
			profileEntity.setDoe(new Timestamp(new Date().getTime()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		profileDao.save(profileEntity);
		return "success";
	}
	
	@Override
	public ProfileDTO findProfileById(int aid) {
		ProfileEntity entity=profileDao.findById(aid).get();
		ProfileDTO profileDTO=new  ProfileDTO();
		BeanUtils.copyProperties(entity, profileDTO);
		return profileDTO;
	}
	
	@Override
public 	String updateProfila(ProfileDTO profileDTO) {		
		ProfileEntity profileEntity=new ProfileEntity();
		if(profileDTO.getPhoto()==null) {
		//BeanUtils.copyProperties(profileDTO, profileEntity, );
		}
		BeanUtils.copyProperties(profileDTO, profileEntity);
		try {
			profileEntity.setHphoto(profileDTO.getPhoto().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		profileDao.save(profileEntity);
		return "done";
	}
	
	@Override
	public long findCounts() {
		return profileDao.count();
	}


	@Override
	public List<ProfileDTO> findProfiles() {
		List<ProfileEntity> lista=profileDao.findAll();
		 List<ProfileDTO> profileDTOs=new ArrayList<>();
		 for(ProfileEntity entity:lista) {
			 ProfileDTO profileDTO=new  ProfileDTO();
				BeanUtils.copyProperties(entity, profileDTO);
				profileDTOs.add(profileDTO);
		 }
		 return profileDTOs;
	}

	@Override
	public ProfileDTO search(String email) {

		ProfileEntity profileEntity=profileDao.findByEmail(email).get();
		ProfileDTO profileDTO = new ProfileDTO();
		BeanUtils.copyProperties(profileEntity, profileDTO);
		return profileDTO;
		
	}



	
	

}