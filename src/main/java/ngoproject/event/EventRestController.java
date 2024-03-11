package ngoproject.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/event")
class EventRestController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/getAllEvents")
	List<Event> getEvents() {
		return eventService.getAllEvents();
	}
	
	@PostMapping("/register")
	String registerEvent(@RequestBody Event event) {
		eventService.saveEvent(event);
		return "Event saved Succesfully";
	}
	
	@PutMapping("/updateEvent/{id}")
	ResponseEntity<String> updateEvent(@PathVariable Integer id, @RequestBody Event uEvent) {
		uEvent.setId(id);
		String updateMessage = eventService.updateEvent(uEvent);
		return ResponseEntity.ok(updateMessage);
	}
	
	@DeleteMapping("/deleteEvent/{id}")
	void deleteEvent(@PathVariable Integer id) {
		eventService.delete(id);
	}
}
