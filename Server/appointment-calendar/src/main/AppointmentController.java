package main;

@RestController

@RequestMapping("/api/appointments")

public class AppointmentController {



  @Autowired

  private AppointmentService service;



  @GetMapping

  public List<Appointment> getAllAppointments() {

    return service.getAllAppointments();

  }



  @PostMapping

  public Appointment createAppointment(@RequestBody Appointment appointment) {

    return service.createAppointment(appointment);

  }



  @DeleteMapping("/{id}")

  public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {

    service.deleteAppointment(id);

    return ResponseEntity.noContent().build();

  }



  @PutMapping("/{id}")

  public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {

    return service.updateAppointment(id, appointment);

  }

}


