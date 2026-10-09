package com.angrodrigco.dto;

public record LoginResponseDTO (
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresIn
) {
}
