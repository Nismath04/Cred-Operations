package com.example.sampleProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sampleProject.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	public UserEntity findByEmail(String email);
	
	@Query(value="select * from user_entity a where a.user_age<= 30 AND a.user_age>= 20", nativeQuery=true)
    List<UserEntity> getUserAgeBetween20And30();
	
	

}
