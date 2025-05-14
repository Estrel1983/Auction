package com.auc.auction.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name="artist", uniqueConstraints = @UniqueConstraint(columnNames = "artist_name"))
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, name = "artist_name")
    String artistName;
}
