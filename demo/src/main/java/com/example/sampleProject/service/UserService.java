package com.example.sampleProject.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sampleProject.dao.UserRepository;
import com.example.sampleProject.entity.UserEntity;
import com.example.sampleProject.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserEntity userEntity;

	public String saveUser(User newUser) {

		try {

			SecureRandom secureRandom = new SecureRandom();

			// Password Hashing
			byte[] salt = secureRandom.generateSeed(12);

			PBEKeySpec pbeKeySpec = new PBEKeySpec(newUser.getPassword().toCharArray(), salt, 10, 512);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			byte[] hash = skf.generateSecret(pbeKeySpec).getEncoded();

			// converting to string to store into database
			String base64HashPassword = Base64.getMimeEncoder().encodeToString(hash);

			//System.out.println(base64HashPassword);

			userEntity.setAge(newUser.getAge());
			userEntity.setEmail(newUser.getEmail());
			userEntity.setPassword(base64HashPassword);

			UserEntity save = null;

			List<UserEntity> findAll = userRepo.findAll();
			for (UserEntity userEntity : findAll) {
				if (userEntity.getEmail().equals(newUser.getEmail())) {
					return "Duplicate Entry";
				}

			}
			save = userRepo.save(userEntity);
			return "success";

		} catch (Exception e) {

			return e.getMessage();

		}

	}

	public UserEntity updateUser(User updateuser) {

		userEntity.setEmail(updateuser.getEmail());
		userEntity.setPassword(updateuser.getPassword());

		UserEntity user = userRepo.findByEmail(userEntity.getEmail());

		if (user != null && user.getId() != 0) {
			userRepo.save(userEntity);
			return user;
		} else {
			return null;
		}

	}

	public String deleteUser(String email) {

		UserEntity user = userRepo.findByEmail(email);
		if (user != null && user.getId() != 0) {
			userRepo.delete(user);
			return "Delete";
		} else
			return "fail";

	}

	public List<UserEntity> getUserAgeBetween20And30() {

		List<UserEntity> userlist = userRepo.getUserAgeBetween20And30();

		return userlist;

	}

	public String userLogin(User userLogin) {
		try {

			userEntity.setEmail(userLogin.getEmail());
			// userEntity.setPassword(userLogin.getPassword());

			UserEntity user = userRepo.findByEmail(userEntity.getEmail());

			SecureRandom secureRandom = new SecureRandom();

			// Password Hashing
			byte[] salt = secureRandom.generateSeed(12);

			PBEKeySpec pbeKeySpec = new PBEKeySpec(userLogin.getPassword().toCharArray(), salt, 10, 512);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			byte[] hash = skf.generateSecret(pbeKeySpec).getEncoded();

			// converting to string to store into database
			String base64HashPassword = Base64.getMimeEncoder().encodeToString(hash);

			// System.out.println(base64HashPassword );

			if (user.getEmail().equals(userLogin.getEmail()) && base64HashPassword.equals(user.getPassword()))

			{
				return "Login Successfull";
			}

			

			else {
				return "login failed";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}
}
