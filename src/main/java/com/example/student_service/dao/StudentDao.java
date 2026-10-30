package com.example.student_service.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.student_service.request.Ticket;
import com.example.student_service.request.UserRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:dao/student/app/StudentDao.xml")
public class StudentDao {

	private final RowMapper<Ticket> ticketMapper = BeanPropertyRowMapper.newInstance(Ticket.class);
	private final RowMapper<UserRequest> userReqMapper = BeanPropertyRowMapper.newInstance(UserRequest.class);

	private final NamedParameterJdbcTemplate npjt;

	@Value("${registerUser}")
	private String registerUser;

	@Value("${createTicket}")
	private String createTicket;

	@Value("${updateTicket}")
	private String updateTicket;

	@Value("${getAllTickets}")
	private String getAllTickets;

	@Value("${getAllTicketsForUser}")
	private String getAllTicketsForUser;

	@Value("${getTicketById}")
	private String getTicketById;

	@Value("${login}")
	private String login;

	public void registerUser(UserRequest req) {
		npjt.update(registerUser, new BeanPropertySqlParameterSource(req));
	}

	public UserRequest login(UserRequest req) {
		try {
			return npjt.queryForObject(login, Map.of("email", req.getEmail(), "password", req.getPassword()),
					userReqMapper);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	public int createTicket(Ticket req) {
		return npjt.update(createTicket, new BeanPropertySqlParameterSource(req));
	}

	public int updateTicketStatus(Ticket req) {
		return npjt.update(updateTicket, new BeanPropertySqlParameterSource(req));
	}

	public List<Ticket> getAllTickets() {
		return Optional.ofNullable(npjt.query(getAllTickets, ticketMapper)).orElse(Collections.emptyList());
	}

	public List<Ticket> getAllTicketsForUser(long userId) {
		return Optional.ofNullable(npjt.query(getAllTicketsForUser, Map.of("userId", userId), ticketMapper))
				.orElse(Collections.emptyList());
	}

	public Ticket getTicketById(long id) {
		return npjt.queryForObject(getTicketById, Map.of("id", id), ticketMapper);
	}
}
