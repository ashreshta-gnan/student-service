package com.example.student_service.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.student_service.dao.StudentDao;
import com.example.student_service.request.Ticket;
import com.example.student_service.request.UserRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentDao dao;

	public void registerUSer(UserRequest req) {
		dao.registerUser(req);
	}

	public boolean login(UserRequest req) {
		return dao.login(req) == null ? false : true;
	}

	public int createTicket(Ticket req) {
		return dao.createTicket(req);
//		return 1;
	}

	public int updateTicket(Ticket req) {
		return dao.updateTicketStatus(req);
	}

	public List<Ticket> getAllTickets() {
		return dao.getAllTickets();
	}

	public List<Ticket> getAllTicketsForUser(long userId) {
		return dao.getAllTicketsForUser(userId);
	}

	public Ticket getTicketById(long id) {
		return dao.getTicketById(id);
	}

}
