package com.auc.auction.repository;

import com.auc.auction.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    boolean existsByArtistName(String artistName);
    Optional<Artist> findByArtistName(String artistName);
}
