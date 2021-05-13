package com.osg.greetingsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osg.greetingsservice.entity.GreetingEntity;
import com.osg.greetingsservice.repository.GreetingsRepository;

@Service
public class GreetingsService {
	@Autowired
	private GreetingsRepository greetingsRepository;
	private static final String REQUEST_TOKEN = "http://localhost:3001/account/";
	private static final String SEND_EMAIL = "http://localhost:3003/mail/";

	public String requestGreetins(String token, GreetingEntity greetingEntityRequest) {

		RestTemplate restTemplate = new RestTemplate();

		String response = "";
		try {
			String responseFromURL = restTemplate.getForObject(REQUEST_TOKEN + token, String.class);
			System.out.println("reso" + responseFromURL);
			if (!responseFromURL.isEmpty() || !responseFromURL.isBlank()) {
				response = "success";
				GreetingEntity greetingEntity = new GreetingEntity();
				greetingEntity.setMobiles(greetingEntityRequest.getMobiles());
				greetingEntity.setMessage(greetingEntityRequest.getMessage());
				GreetingEntity sendEmail = restTemplate.postForObject(SEND_EMAIL, greetingEntityRequest,GreetingEntity.class);
				greetingsRepository.save(greetingEntity);
			} else {
				response = "fails";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public List<GreetingEntity> findByMobiles(int id) {
		return greetingsRepository.findAllByMobiles(id);
		
		 
	}
}
