package poc.microservices.instance.two.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import poc.microservices.instance.two.dto.User;

@RestController
public class InstanceTwoController {

	private Map<String, Integer> totalRequestCountMap;

	public InstanceTwoController() {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		totalRequestCountMap = instance.getMap("requestCount");
	}

	
	
	@RequestMapping("/getUser")
	public User test(
			@RequestParam(value = "name", defaultValue = "Instance2") String name) {
		System.out.println("22222222222222222222222222222");
		Integer count = totalRequestCountMap.get("user");
		if (count == null) {
			totalRequestCountMap.put("user", 0);
		} else {
			totalRequestCountMap.put("user", count + 1);
		}
//		System.out.println("Total nos. of rwquest :"
//				+ totalRequestCountMap.get("user"));
		return new User(22222, name);
	}
}
