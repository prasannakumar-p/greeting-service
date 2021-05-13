package com.osg.greetingsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osg.greetingsservice.entity.GreetingEntity;
@Repository
public interface GreetingsRepository extends JpaRepository<GreetingEntity, Integer>{

	List<GreetingEntity> findAllByMobiles(int id);

}
