package com.benediktvitek.ben.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieListDTO(List<MovieDto> results) {
}
