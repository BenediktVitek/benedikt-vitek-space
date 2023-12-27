package com.benediktvitek.ben.dtos.requests;

import com.benediktvitek.ben.dtos.responses.MovieDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieListDTO(List<MovieDTO> results) {
}
