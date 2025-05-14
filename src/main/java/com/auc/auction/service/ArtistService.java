package com.auc.auction.service;

import com.auc.auction.model.Artist;
import com.auc.auction.repository.ArtistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;
    public List<Artist> getAllArtist(){
        List<Artist> artistList = artistRepository.findAll();
        log.debug("getAllArtist found {} records", artistList.size());
        return artistList;
    }
    public Optional<Artist> getArtistById(Integer id){
        return artistRepository.findById(id);
    }
    public Optional<Artist> getArtistByName(String artistName){
        return artistRepository.findByArtistName(artistName);
    }
    public List<Artist> addArtists(List<Artist> artists ){
        try{
            return artistRepository.saveAll(artists);
        } catch (DataIntegrityViolationException e){
            log.error("One or more artists already exist.{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more artists already exist.");
        }
    }
    public Artist addArtist(Artist artist){
        if (artistRepository.existsByArtistName(artist.getArtist_name())) {
            log.error("Artist {} already exists", artist.getArtist_name());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artist " + artist.getArtist_name() + " already exists");
        }
        return artistRepository.save(artist);
    }
}
