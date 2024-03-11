package ngoproject.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	List<Event> getAllEvents() {
		return eventRepository.findAll();
	}
	
	String saveEvent(Event event) {
		eventRepository.save(event);
		return "Event Saved Successfully";
	}
	
	String updateEvent(Event uEvent) {
		Optional<Event> optional = eventRepository.findById(uEvent.getId());
		
		if(optional.isPresent()) {
			Event event = optional.get();
			// update event object
			event.setActive(!event.isActive());
			
			eventRepository.save(event);
			
			// updateMessage. Reason to use ResponseEntity. Extra information can be passed
			return "Event updated successfully";
		} else {
			throw new RuntimeException("BE Update Event Fail: " + uEvent.getId());
		}
		
	}

	public void delete(Integer id) {
		eventRepository.deleteById(id);
	}
}
