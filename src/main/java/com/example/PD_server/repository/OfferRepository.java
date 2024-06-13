package com.example.PD_server.repository;

import com.example.PD_server.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByOfferCode(String offerCode);
    List<Offer> findByUserUsername(String username);
}
