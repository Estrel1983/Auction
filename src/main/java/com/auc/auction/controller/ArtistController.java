package com.auc.auction.controller;

import com.auc.auction.model.Artist;
import com.auc.auction.service.ArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping
    public List<Artist> getArtists() {
        log.info("Get artists call");
        return artistService.getAllArtist();
    }
    @GetMapping
    @RequestMapping("/{id}")
    public Artist getArtistById(@PathVariable Integer id){
        return artistService.getArtistById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Artist with id - " + id + " not found"));
    }
    @GetMapping
    @RequestMapping("/{name}")
    public Artist getArtistById(@PathVariable String artistName){
        return artistService.getArtistByName(artistName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Artist with name - " + artistName + " not found"));
    };
    @PostMapping
    @RequestMapping("/batch")
    public ResponseEntity<List<Artist>> addArtistsList(@RequestBody List<Artist> artists){
        log.info("Post request to add {}", artists);
        List<Artist>savedArtists = artistService.addArtists(artists);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArtists);
    }
    @PostMapping
    public ResponseEntity<Void> addArtist(@RequestBody Artist artist, UriComponentsBuilder ucb){
        log.info("Post request to add {}", artist.getArtistName());
        Artist artistSaved = artistService.addArtist(artist);
        URI locationOfArtist = ucb.path("/artist/{id}").buildAndExpand(artistSaved.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
