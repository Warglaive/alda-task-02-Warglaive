package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TimelineImpl implements Timeline {

    /**
     * Start and End Instants of Timeline
     */
    private Instant start;
    private Instant end;
    /**
     * Defaults
     */
    private Instant defaultStart = LocalDay.now().ofLocalTime(LocalTime.of(8, 30));
    private Instant defaultEnd = LocalDay.now().ofLocalTime(LocalTime.of(17, 30));
    /**
     * Doubly Linked List
     */

    private DoublyLinkedList<TimeSlot> timeLineAllocations;


    private int nrOfAppointments;

    public TimelineImpl(Instant start, Instant end) {
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

    /**
     * get very first Appointment's start time
     *
     * @return
     */
    @Override
    public Instant start() {
        return this.defaultStart;
    }

    /**
     * get very first Appointment's end time
     *
     * @return
     */
    @Override
    public Instant end() {
        return this.defaultEnd;
    }

    @Override
    public Optional<Appointment> addAppointment(LocalDay forDay, AppointmentData appointment, TimePreference timepreference) {
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
