package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
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

    private void putAppointment(Map<String, TimeSlot> timeSlotMap) {
        var currentNode = this.appointments.searchItemNode(timeSlotMap.get("OriginalTimeSlot"));
        var nextTimeSlot = timeSlotMap.get("NextTimeSlot");
        TimeSlot previousTimeSlot = null;
        if (timeSlotMap.containsKey("PreviousTimeSlot")) {
            previousTimeSlot = timeSlotMap.get("PreviousTimeSlot");
        }
        var appointment = timeSlotMap.get("AppointmentSlot");
        if (appointment != null) {
            if (nextTimeSlot == null) {
                if (previousTimeSlot != null) {
                    currentNode.setItem(previousTimeSlot);
                    this.appointments.addAfter((Appointment) appointment, previousTimeSlot);
                    if (previousTimeSlot.getStart().equals(previousTimeSlot.getEnd())) {
                        var appointmentNode = appointments.searchItemNode(appointment);
                        appointments.mergeNodesPrevious(appointmentNode, currentNode, appointment);
                    }
                } else {
                    currentNode.setItem(appointment);
                }
            } else {
                currentNode.setItem(nextTimeSlot);
                this.appointments.addBefore(appointment, nextTimeSlot);
                if (previousTimeSlot != null) {
                    this.appointments.addBefore(previousTimeSlot, appointment);
                }
                var appointmentNode = appointments.searchItemNode(appointment);
                if (nextTimeSlot.getStart().equals(nextTimeSlot.getEnd())) {
                    appointments.mergeNodesNext(appointmentNode, currentNode, appointment);
                }
            }
        }
    }

    private Map<String, Optional<TimeSlot>> findFirstFittingTimeSlot(Duration appointmentDuration) {
        var gapsFitting = this.getGapsFitting(appointmentDuration);

        Function<TimeSlot, TimeSlot> appointmentMapper = (timeSlot) ->
                new TimeslotImpl(timeSlot.getStart(), timeSlot.getStart()
                        .plusSeconds(appointmentDuration.toSeconds()));

        Function<TimeSlot, TimeSlot> timeSlotMapper = (timeSlot) -> {
            var appointmentTimeSlot = new TimeslotImpl(timeSlot.getStart(), timeSlot.getStart()
                    .plusSeconds(appointmentDuration
                            .toSeconds()));
            return new TimeslotImpl(appointmentTimeSlot.getEnd(), timeSlot.getEnd());
        };

        return this.findFittingTimeSlot(gapsFitting, appointmentMapper, timeSlotMapper, false);
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

    private Map<String, Optional<TimeSlot>> findFittingTimeSlot(List<TimeSlot> gapsFitting, Function<TimeSlot, TimeSlot> appointmentMapper, Function<TimeSlot, TimeSlot> timeSlotMapper, boolean last) {

        var returnMap = new HashMap();

        var appointmentSlot = gapsFitting.stream()
                .findFirst()
                .stream()
                .map(appointmentMapper)
                .map(TimeSlot.class::cast)
                .findFirst();

        returnMap.put("AppointmentSlot", appointmentSlot);

        var newTimeSlot = gapsFitting.stream()
                .findFirst()
                .stream()
                .map(timeSlotMapper)
                .map(TimeSlot.class::cast)
                .findFirst();

        if (last) {
            returnMap.put("PreviousTimeSlot", newTimeSlot);
        } else {
            returnMap.put("NextTimeSlot", newTimeSlot);
        }

        Optional<TimeSlot> originalTimeSlot = gapsFitting.stream()
                .findFirst();

        returnMap.put("OriginalTimeSlot", originalTimeSlot);

        return returnMap;
    }


    private HashMap<String, Optional<TimeSlot>> findPreferredTimeSlot(Duration appointmentDuration, LocalTime preferredTime, LocalDay localDay, TimePreference timePreference) {
        var gapsFittingList = new ArrayList<TimeSlot>();

        this.getGapsFitting(appointmentDuration).stream()
                .filter(timeSlot -> timeSlot.fits(appointmentDuration))
                .forEach(gapsFittingList::add);

        var preferredSlot = gapsFittingList.stream()
                .filter(timeSlot -> {
                    var startTime = timeSlot.getStartTime(localDay);
                    var endTime = timeSlot.getEndTime(localDay);
                    var endTimeAppointment = preferredTime.plusMinutes(appointmentDuration.toMinutes());
                    return (startTime.isBefore(preferredTime) || startTime.equals(preferredTime)) &&
                            (endTime.isAfter(endTimeAppointment) || endTime.equals(endTimeAppointment));
                })
                .map(timeSlot -> new TimeslotImpl(localDay.ofLocalTime(preferredTime), localDay.ofLocalTime(preferredTime.plusMinutes(appointmentDuration.toMinutes()))))
                .map(TimeSlot.class::cast)
                .findAny();

        if (preferredSlot.isEmpty()) {
            if (timePreference == TimePreference.EARLIEST_AFTER) {
                preferredSlot = gapsFittingList.stream()
                        .filter(timeSlot ->
                                timeSlot.getEndTime(localDay).minusMinutes(appointmentDuration.toMinutes()).isAfter(preferredTime)
                        )
                        .map(timeSlot -> new TimeslotImpl(timeSlot.getStart(), timeSlot.getStart().plusSeconds(appointmentDuration.toSeconds())))
                        .map(TimeSlot.class::cast)
                        .findFirst();

            } else if (timePreference == TimePreference.LATEST_BEFORE) {
                preferredSlot = gapsFittingList.stream()
                        .filter(timeSlot ->
                                timeSlot.getStartTime(localDay).plusMinutes(appointmentDuration.toMinutes()).isBefore(preferredTime)
                        )
                        .map(timeSlot -> new TimeslotImpl(timeSlot.getEnd().minusSeconds(appointmentDuration.toSeconds()), timeSlot.getEnd()))
                        .map(TimeSlot.class::cast)
                        .reduce((val1, val2) -> val2);

            }
        }
        if (!preferredSlot.isEmpty()) {
            var appointmentSlot = preferredSlot;
            var returnMap = new HashMap<String, Optional<TimeSlot>>();
            returnMap.put("AppointmentSlot", preferredSlot);
            returnMap.put("OriginalTimeSlot", gapsFittingList.stream()
                    .filter(timeSlot -> (timeSlot.fits(appointmentSlot.get())))
                    .findAny());

            var nextStart = preferredSlot.get().getEnd();
            var nextEnd = returnMap.get("OriginalTimeSlot").get().getEnd();
            if (!(nextStart.isAfter(nextEnd) || nextStart.equals(nextEnd))) {
                returnMap.put("NextTimeSlot", Optional.of(new TimeslotImpl(nextStart, nextEnd)));
            }

            var previousStart = returnMap.get("OriginalTimeSlot").get().getStart();
            var previousEnd = preferredSlot.get().getStart();
            if (!(previousStart.isAfter(previousEnd) || previousStart.equals(previousEnd))) {
                returnMap.put("PreviousTimeSlot", Optional.of(new TimeslotImpl(previousStart, previousEnd)));
            }

            return returnMap;
        }
        return null;
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