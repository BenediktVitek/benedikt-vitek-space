package com.benediktvitek.ben.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record  MovieDto(Long id, String title, Double vote_average) {
}
