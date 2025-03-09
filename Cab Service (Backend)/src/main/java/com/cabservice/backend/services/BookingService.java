package com.cabservice.backend.services;

import com.cabservice.backend.models.Booking;
import com.cabservice.backend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Add a new booking
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Get all bookings
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Optional<Booking> getBookingById(String id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByUserId(String userid) {
        return bookingRepository.findByUserid(userid);
    }

    // Get bookings by Driver ID
    public List<Booking> getBookingsByDriverId(String driverId) {
        return bookingRepository.findByDriverid(driverId);
    }

    // Get bookings by Status
    public List<Booking> getBookingsByStatus(int bookStatus) {
        return bookingRepository.findByBookstatus(bookStatus);
    }

    // Delete a booking
    public String deleteBooking(String id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return "Booking deleted successfully!";
        }
        return "Booking not found!";
    }

    public Booking assignDriver(String id, String driverId) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setDriverid(driverId);
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    public Booking updateBookingStatus(String id, int status) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setBookstatus(status);
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    public Booking updateTotalFee(String id, double totalFee) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setTotalfee(totalFee);
            return bookingRepository.save(booking);
        }).orElse(null);
    }

    // Update payment status
    public Booking updatePaymentStatus(String id, int paymentStatus) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setPaymentstatus(paymentStatus);
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    public Booking updateTravelDistance(String id, int distance) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setTravelDistance(distance);
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }
}
