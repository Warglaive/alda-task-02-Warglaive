package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static appointmentplanner.api.TimePreference.LATEST;

public class TimelineImpl implements Timeline {
    private Instant start;
    private Instant end;
    private DoublyLinkedList<TimeSlot> appointments;

    public TimelineImpl(Instant start, Instant end) {
        this(start, end, new DoublyLinkedList<TimeSlot>());
    }

    public TimelineImpl(Instant start, Instant end, DoublyLinkedList<TimeSlot> appointments) {
        this.start = start;
        this.end = end;
        this.appointments = appointments;

        if (this.appointments.getSize() == 0) {
            var factory = new APFactory();
            this.appointments.addFront(factory.between(start, end));
        }
    }

    @Override
    public int getNrOfAppointments() {
        return (int) appointmentStream().count();
    }

    @Override
    public Instant start() {
        return this.start;
    }

    @Override
    public Instant end() {
        return this.end;
    }

    @Override
    public Optional<Appointment> addAppointment(LocalDay forDay, AppointmentData appointment, TimePreference timepreference) {
        return addAppointment(forDay, appointment, null, timepreference);
    }

    @Override
    public Optional<Appointment> addAppointment(LocalDay forDay, AppointmentData appointment, LocalTime startTime) {
        return addAppointment(forDay, appointment, startTime, null);
    }

    @Override
    public Optional<Appointment> addAppointment(LocalDay forDay, AppointmentData appointment, LocalTime startTime, TimePreference fallback) {
        if (appointment == null) {
            throw new NullPointerException("appointment can NOT be null");
        }
        var appointmentDuration = appointment.getDuration();
        Map<String, Optional<TimeSlot>> timeSlotOptMap = new HashMap();

        if (startTime != null) {
            timeSlotOptMap = this.findPreferredTimeSlot(appointmentDuration, startTime, forDay, fallback);
        }
        if (fallback == null) {
            fallback = TimePreference.UNSPECIFIED;
        }

        if (startTime == null || timeSlotOptMap == null) {
            switch (fallback) {
                case LATEST:
                    timeSlotOptMap = findLastFittingTimeSlot(appointmentDuration);
                    break;
                default:
                    timeSlotOptMap = findFirstFittingTimeSlot(appointmentDuration);

            }
        }


        var timeSlotMap = optionalToTimeSlot(timeSlotOptMap);
        if (timeSlotOptMap.containsKey("AppointmentSlot")) {
            if (timeSlotOptMap.get("AppointmentSlot").isEmpty() == false) {

                timeSlotMap.replace("AppointmentSlot", buildAppointment(
                        appointmentData,
                        LocalTime.ofInstant(timeSlotMap.get("AppointmentSlot").getStart(), localDay.getZone()),
                        timePreference,
                        timeSlotOptMap.get("AppointmentSlot").get()).get());

                putAppointment(timeSlotMap);
                return Optional.of((Appointment) timeSlotMap.get("AppointmentSlot"));
            }
        }
        return Optional.empty();


    }

    private Map<String, Optional<TimeSlot>> findLastFittingTimeSlot(Duration appointmentDuration) {
        var gapsFitting = this.getGapsFittingReversed(appointmentDuration);
        Function<TimeSlot, TimeSlot> appointmentMapper = (timeSlot) ->
                new TimeslotImpl(timeSlot.getEnd().minusSeconds(appointmentDuration.toSeconds()), timeSlot.getEnd());

        Function<TimeSlot, TimeSlot> timeSlotMapper = (timeSlot) -> {
            var appointmentTimeSlot = new TimeslotImpl(timeSlot.getEnd().minusSeconds(appointmentDuration.toSeconds()), timeSlot.getEnd());
            return new TimeslotImpl(timeSlot.getStart(), appointmentTimeSlot.getStart());
        };

        return this.findFittingTimeSlot(gapsFitting, appointmentMapper, timeSlotMapper, true);
    }

    private Map<String, Optional<TimeSlot>> findFittingTimeSlot(List<TimeSlot> gapsFitting, Function<TimeSlot, TimeSlot> appointmentMapper, Function<TimeSlot, TimeSlot> timeSlotMapper, boolean b) {
    }

    private HashMap<String, Optional<TimeSlot>> findPreferredTimeSlot(Duration appointmentDuration, LocalTime startTime, TimePreference fallback, TimePreference fallback1) {
    }

    @Override
    public AppointmentRequest removeAppointment(Appointment appointment) {
        return null;
    }

    @Override
    public List<AppointmentRequest> removeAppointments(Predicate<Appointment> filter) {
        return null;
    }

    @Override
    public List<Appointment> findAppointments(Predicate<Appointment> filter) {
        return null;
    }

    @Override
    public Stream<Appointment> appointmentStream() {
        return null;
    }

    @Override
    public boolean contains(Appointment appointment) {
        return false;
    }

    @Override
    public List<TimeSlot> getGapsFitting(Duration duration) {
        return null;
    }

    @Override
    public boolean canAddAppointmentOfDuration(Duration duration) {
        return false;
    }

    @Override
    public List<TimeSlot> getGapsFittingReversed(Duration duration) {
        return null;
    }

    @Override
    public List<TimeSlot> getGapsFittingSmallestFirst(Duration duration) {
        return null;
    }

    @Override
    public List<TimeSlot> getGapsFittingLargestFirst(Duration duration) {
        return null;
    }

    @Override
    public List<TimeSlot> getMatchingFreeSlotsOfDuration(Duration minLength, List<Timeline> other) {
        return null;
    }
}