package com.example.sweater.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
	@Autowired
	private MessageRepo messageRepo;
	
	@GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
	
	@GetMapping("/main") 
	public String main(@RequestParam(required = false) String filter, Model  model )  {
		Iterable<Message> messages =  messageRepo.findAll(); 
	
		if(filter != null && !filter.isEmpty()) {
			messages = messageRepo.findByTag(filter);
		} else {
			messages = messageRepo.findAll();
		}
		
		model.addAttribute("messages", messages );
		model.addAttribute("filter", filter);
		
		return "main";
	}  
	
	@PostMapping("/main") 
	public String add(
			@AuthenticationPrincipal User user, 
			@RequestParam String text, 
			@RequestParam String tag, Map<String, Object> model
	) {
		Message message = new Message(text, tag, user);
		// здесь каждое новое добаленое сообщение будет отображать User
		
		messageRepo.save(message);
		
		Iterable<Message> messages =  messageRepo.findAll(); 
		
		model.put("messages", messages );
		
		return "main";
	}
	  
	/*@PostMapping("filter") 				// найти по переданному тэгу все переданные сообшщения*/
}