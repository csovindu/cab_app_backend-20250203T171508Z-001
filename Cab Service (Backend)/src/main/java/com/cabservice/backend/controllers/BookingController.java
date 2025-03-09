package com.cabservice.backend.controllers;

import com.cabservice.backend.models.Booking;
import com.cabservice.backend.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rental")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping("/create")
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    // Get all bookings
    @GetMapping("/all")
    public List<Booking> getBookings() {
        return bookingService.getBookings();
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable String id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/user/{userid}")
    public List<Booking> getBookingsByUserId(@PathVariable String userid) {
        return bookingService.getBookingsByUserId(userid);
    }

    // Update bookingstatus == 1
    @PutMapping("/update/{id}/status1")
    public Booking updateBookingStatus1(@PathVariable String id) {
        return bookingService.updateBookingStatus(id, 1);
    }

    // Update bookingstatus == 2
    @PutMapping("/update/{id}/status2")
    public Booking updateBookingStatus2(@PathVariable String id) {
        return bookingService.updateBookingStatus(id, 2);
    }

    @PutMapping("/update/{id}/totalfee")
    public ResponseEntity<?> updateTotalFee(@PathVariable String id, @RequestBody Map<String, Double> request) {
        if (!request.containsKey("totalfee")) {
            return ResponseEntity.badRequest().body("Missing 'totalfee' field in request body");
        }
        double totalfee = request.get("totalfee");
        Booking updatedBooking = bookingService.updateTotalFee(id, totalfee);
        if (updatedBooking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking ID not found");
        }
        return ResponseEntity.ok(updatedBooking);
    }

    // Get bookings by Driver ID
    @GetMapping("/driver/{driverid}")
    public List<Booking> getBookingsByDriverId(@PathVariable String driverid) {
        return bookingService.getBookingsByDriverId(driverid);
    }

    // Get bookings by Status
    @GetMapping("/status/{bookstatus}")
    public List<Booking> getBookingsByStatus(@PathVariable int bookstatus) {
        return bookingService.getBookingsByStatus(bookstatus);
    }

    @PutMapping("/update/{id}/driver")
    public Booking assignDriver(@PathVariable String id, @RequestBody Booking booking) {
        return bookingService.assignDriver(id, booking.getDriverid());
    }

    @PutMapping("/update/{id}/paymentstatus")
    public ResponseEntity<Booking> updatePaymentStatus(@PathVariable String id) {
        try {
            Booking updatedBooking = bookingService.updatePaymentStatus(id, 1);
            return ResponseEntity.ok(updatedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{id}/traveldistance")
    public ResponseEntity<?> updateTravelDistance(@PathVariable String id, @RequestBody Map<String, Integer> request) {
        if (!request.containsKey("travelDistance")) {
            return ResponseEntity.badRequest().body("Missing 'travelDistance' field in request body");
        }

        int travelDistance = request.get("travelDistance");
        Booking updatedBooking = bookingService.updateTravelDistance(id, travelDistance);

        return ResponseEntity.ok(updatedBooking);
    }

    // Delete a booking
    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable String id) {
        return bookingService.deleteBooking(id);
    }
}




