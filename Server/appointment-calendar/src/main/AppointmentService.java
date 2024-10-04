package main;

import java.util.List;

@Service

public class AppointmentService {

  @Autowired

  private AppointmentRepository repository;



  public List<Appointment> getAllAppointments() {

    return repository.findAll();

  }



  public Appointment createAppointment(Appointment appointment) {

    return repository.saveAll(appointment);

  }



  public void deleteAppointment(Long id) {

    repository.deleteById(id);

  }



  public Appointment updateAppointment(Long id, Appointment updatedAppointment) {

    Appointment appointment = repository.findById(id).orElseThrow();

    appointment.setStartTime(updatedAppointment.getStartTime());

    appointment.setEndTime(updatedAppointment.getEndTime());

    return repository.saveAll(appointment);

  }

}