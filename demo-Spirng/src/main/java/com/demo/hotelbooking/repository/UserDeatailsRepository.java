package com.demo.hotelbooking.repository;

import org.springframework.stereotype.Repository;

import com.demo.hotelbooking.entity.UserDetailsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserDeatailsRepository extends JpaRepository<UserDetailsEntity,String>{

	
}
