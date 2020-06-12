package com.rab3.dao;

import com.rab3.dao.entity.ProfileEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProfileDaoRepository extends JpaRepository<ProfileEntity, Integer> {
	
	public  Optional<ProfileEntity>   findByUsernameAndPassword(String username,String password);
	public  Optional<ProfileEntity>   findByEmail(String Email);
	public  ProfileEntity findByUsername(String username);

}
