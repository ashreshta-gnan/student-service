package com.example.student_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_service.request.Ticket;
import com.example.student_service.request.UserRequest;
import com.example.student_service.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService ser;

	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody UserRequest req) {
		ser.registerUSer(req);
		return new ResponseEntity<String>("Registerd SuccessFully", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserRequest req) {

		boolean data = ser.login(req);
		if (data) {
			return new ResponseEntity<String>("Login SuccessFully", HttpStatus.OK);

		}
		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/ticket")
	public ResponseEntity<String> createTicket(@RequestBody Ticket req) {

		int data = ser.createTicket(req);
//		if (data > 1) {
		return new ResponseEntity<String>("Ticket Created", HttpStatus.OK);

//		}
//		return new ResponseEntity<String>("Invalid Input", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/ticket")
	public ResponseEntity<String> updateTicketStatus(@RequestBody Ticket req) {

		int data = ser.updateTicket(req);
		return new ResponseEntity<String>("Ticket Created", HttpStatus.OK);

	}

	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getAllTickets() {

		List<Ticket> data = ser.getAllTickets();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/tickets/{userId}")
	public ResponseEntity<List<Ticket>> getAllTicketsForUser(long userId) {

		List<Ticket> data = ser.getAllTicketsForUser(userId);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/tickets{id}")
	public ResponseEntity<Ticket> getTicketById(long id) {

		Ticket data = ser.getTicketById(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
