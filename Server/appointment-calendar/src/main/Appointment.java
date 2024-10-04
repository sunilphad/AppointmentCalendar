package main;

import java.time.LocalDateTime;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String title;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}

