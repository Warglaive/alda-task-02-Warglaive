package appointmentplanner;

/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University ofLength Applied Science Venlo
 */

import appointmentplanner.api.*;

import java.time.*;

/**
 * Abstract factory to separate student implementations from teachers tests. The
 * instance created by this factory will be black-box tested by the teachers
 * tests.
 *
 * Richard van den Ham {@code r.vandenham@fontys.nl} Pieter van den Hombergh
 * {@code p.vandenhombergh@fontys.nl}
 */
public class APFactory implements AbstractAPFactory {

    @Override
    public LocalDayPlan createLocalDayPlan( ZoneId zone, LocalDate date, Timeline timeline ) {
        return new LocalDayPlanImpl(zone, date, timeline);
    }

    @Override
    public LocalDayPlan createLocalDayPlan( LocalDay day, Instant start, Instant end ) {
        return new LocalDayPlanImpl(day, start, end);
    }

    public APFactory() {
    }

    @Override
    public AppointmentData createAppointmentData( String description, Duration duration ) {
        return this.createAppointmentData(description, duration, Priority.LOW);
    }

    @Override
    public AppointmentData createAppointmentData( String description, Duration duration, Priority priority ) {
        return new AppointmentDataImpl(
                duration,
                description,
                priority
        );
    }

    @Override
    public AppointmentRequest createAppointmentRequest( AppointmentData appData, LocalTime prefStart, TimePreference fallBack ) {
        return new AppointmentRequestImpl(
                prefStart, appData, fallBack
        );
    }

    @Override
    public TimeSlot between( Instant start, Instant end ) {
        return new TimeslotImpl(start, end);
    }
}
