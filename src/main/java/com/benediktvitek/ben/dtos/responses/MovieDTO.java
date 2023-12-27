package com.benediktvitek.ben.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDTO(Long id, String title, Double vote_average) {

}
