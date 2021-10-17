package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TimelineImpl implements Timeline {

    /**
     * Doubly Linked List
     */

    private Instant defaultStart = LocalDay.now().ofLocalTime(LocalTime.of(8, 30));
    private Instant defaultEnd = LocalDay.now().ofLocalTime(LocalTime.of(17, 30));

    private DoublyLinkedList<TimeSlot> timeLine;
    private Appointment appointment;


    private int nrOfAppointments;

    /**
     * Appoint Appointment on TimeLine
     */
    public TimelineImpl() {
        this.timeLine = new DoublyLinkedList<>();
    }


    @Override
    public int getNrOfAppointments() {
        //get number of appointments from the LinkedListImpl with help of stream and lambda
        return 0;
    }

    @Override
    public Instant start() {
        return this.;
    }

    @Override
    public Instant end() {
        return null;
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
