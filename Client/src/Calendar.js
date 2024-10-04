import React, { useState, useEffect } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import axios from "axios";

const Calendar = () => {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    // Fetch appointments from the backend when the component mounts
    axios.get("/api/appointments")
      .then(response => setEvents(response.data))
      .catch(error => console.error("Error fetching appointments:", error));
  }, []);

  const handleEventAdd = (event) => {
    // Add new appointment via the backend API
    axios.post("/api/appointments", event)
      .then(() => setEvents([...events, event]))
      .catch(error => console.error("Error adding appointment:", error));
  };

  const handleEventDelete = (eventId) => {
    // Delete an appointment via the backend API
    axios.delete(`/api/appointments/${eventId}`)
      .then(() => setEvents(events.filter(event => event.id !== eventId)))
      .catch(error => console.error("Error deleting appointment:", error));
  };

  return (
    <FullCalendar
      plugins={[dayGridPlugin]}
      initialView="dayGridWeek"
      events={events}
      editable={true}  // Make events draggable
      eventDrop={(info) => {
        // Update the event in the database when dropped
        const updatedEvent = { ...info.event, start: info.event.startStr };
        axios.put(`/api/appointments/${info.event.id}`, updatedEvent)
          .then(() => {
            setEvents(events.map(event => event.id === info.event.id ? updatedEvent : event));
          })
          .catch(error => console.error("Error updating appointment:", error));
      }}
      eventClick={(info) => handleEventDelete(info.event.id)} // Delete on click (you can replace this with a delete button)
    />
  );
};

export default Calendar;