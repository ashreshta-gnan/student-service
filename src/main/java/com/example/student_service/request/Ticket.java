package com.example.student_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

	private long id;
	private String comments;
	private long userId;
	private String title;
	private String description;
	private String category;
	private String priority;
	private String status;
}
