package com.osg.greetingsservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.osg.greetingsservice.entity.GreetingEntity;
import com.osg.greetingsservice.service.GreetingsService;

@RestController
public class GreetingsController {
	@Autowired
	private GreetingsService greetingsService;

	@PostMapping(value = "/greeting/send", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> requestGreetins(@RequestHeader String token,
			@RequestBody GreetingEntity greetingEntity) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String response = greetingsService.requestGreetins(token, greetingEntity);
		if (response.equals("success")) {
			respMap.put("status", "ok");
			respMap.put("message", "Message Request Queued");
			respMap.put("ids", greetingEntity.getMobiles());
		} else {
			respMap.put("status", "error");
			respMap.put("message", "Message Request Failed");
		}

		return respMap;

	}
	@GetMapping(value = "/greeting/{id}")
	public HttpEntity<List<GreetingEntity>> findByMobolesId(@PathVariable(value = "id") String id) {
		Integer dd=Integer.valueOf(id);
		List<GreetingEntity> response = greetingsService.findByMobiles(dd);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
