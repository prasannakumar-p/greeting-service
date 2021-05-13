package com.osg.greetingsservice.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GreetingEntity {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String message;
	@Column
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> mobiles;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Integer> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<Integer> mobiles) {
		this.mobiles = mobiles;
	}
	
}
