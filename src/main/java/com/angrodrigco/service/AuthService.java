package com.angrodrigco.service;

import com.angrodrigco.dto.LoginRequestDTO;
import com.angrodrigco.dto.LoginResponseDTO;
import com.angrodrigco.dto.UsuarioResponseDTO;
import com.angrodrigco.dto.RegistroRequestDTO;
import com.angrodrigco.model.Usuario;
import com.angrodrigco.model.Rol;
import com.angrodrigco.security.JwtService;
import com.angrodrigco.repository.UsuarioRepository;
import com.angrodrigco.repository.RolRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(
                token,
                null,
                "Bearer",
                jwtService.getExpirationMs() / 1000
        );
    }

    public UsuarioResponseDTO registrar(RegistroRequestDTO request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
        }

        Rol rolUser = rolRepository.findByNombre("USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRoles(Set.of(rolUser));

        Usuario guardado = usuarioRepository.save(usuario);

        Set<String> roles = guardado.getRoles().stream().map(Rol::getNombre).collect(Collectors.toSet());

        return new UsuarioResponseDTO(guardado.getId(), guardado.getUsername(), roles);
    }
}
