package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TimelineImpl implements Timeline {
    /**
     * Factory to create Timeslot
     */
    private AbstractAPFactory factory;
    /**
     * maintains a 'record' of both the allocated time slots and the (remaining) free time slots.
     */
    private int allocatedTimeSlots;
    private int remainingFreeSlots;
    /**
     * Start and End Instants of Timeline
     */
    private Instant start;
    private Instant end;
    /**
     * Defaults
     */
    private Instant defaultStart = LocalDay.now().ofLocalTime(LocalTime.of(0, 0));
    private Instant defaultEnd = LocalDay.now().ofLocalTime(LocalTime.of(23, 59));
    /**
     * Doubly Linked List
     */

    private DoublyLinkedList<TimeSlot> timeLineAllocations;


    private int nrOfAppointments;

    public TimelineImpl(Instant start, Instant end) {
        this.factory = new APFactory();
        this.timeLineAllocations = new DoublyLinkedList<>();
        this.start = start;
        this.end = end;
    }

    /**
     * If Start and End are not specified -> use defaults
     */
    public TimelineImpl() {
        this.start = this.defaultStart;
        this.end = this.defaultEnd;
    }


    @Override
    public int getNrOfAppointments() {
        //get number of appointments from the LinkedListImpl with help of stream and lambda
        return 0;
    }

    @Override
    public Instant start() {
        return this.start;
    }

    @Override
    public Instant end() {
        return this.end;
    }

    /**
     * fields for timeSlot to ease usage
     */
    private Instant timeslotStartTime;
    private Instant timeslotEndTime;

    @Override
    public Optional<Appointment> addAppointment(LocalDay forDay, AppointmentData appointment, TimePreference timepreference) {
        //TODO: Add AppointmentData Priority check
        if (timepreference.equals(TimePreference.EARLIEST)) {
            if (this.timeLineAllocations.getSize() <= 1) {
                //TODO: probably buggy, test
                this.timeslotStartTime = this.start;
                this.timeslotEndTime = this.start.plusSeconds(appointment.getDuration().toSeconds());
                //TODO: Is start properly made?
                LocalTime timeslotStartLocalTime = LocalDay.now().timeOfInstant(this.timeslotStartTime);
                LocalTime timeslotEndLocalTime = LocalDay.now().timeOfInstant(this.timeslotEndTime);
                var timeslot = this.factory.between(forDay, timeslotStartLocalTime, timeslotEndLocalTime);
                //TODO: WHere
                //Add current timeslot to the Timeline
/*
                this.timeLineAllocations.addNode(timeslot);
*/
                //TODO: Add appointment and return...blbalbal
/*
                return Optional.of(timeslot);
*/
                return Optional.empty();
            }
        }
        return Optional.empty();

    }

    @Override
    public Optional<Appointment> addAppointment(LocalDay forDay, AppointmentData appointment, LocalTime startTime) {
        return Optional.empty();
    }

    @Override
    public Optional<Appointment> addAppointment(LocalDay forDay, AppointmentData appointment, LocalTime startTime, TimePreference fallback) {
        return Optional.empty();
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
