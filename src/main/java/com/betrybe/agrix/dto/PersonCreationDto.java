package com.betrybe.agrix.dto;

import javax.management.relation.Role;

/**
 * Person creation DTO.
 */
public record PersonCreationDto(Role role, String username, String password, Long id) {
}
