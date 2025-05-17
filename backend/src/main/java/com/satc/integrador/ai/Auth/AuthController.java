package com.satc.integrador.ai.Auth;

import com.satc.integrador.ai.Usuario.UsuarioGetDto;
import com.satc.integrador.ai.Usuario.UsuarioPostDto;
import com.satc.integrador.ai.Usuario.UsuarioRepo;
import com.satc.integrador.ai.Usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {
    @Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtTokenService jwtTokenService;
	@Autowired private UsuarioRepo userRepo;
    @Autowired private UsuarioService userService;
	@Autowired private SecurityConfiguration securityConfiguration;

    @PostMapping("auth/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto dto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        RecoveryJwtTokenDto token = new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("auth/signin")
    public ResponseEntity<UsuarioGetDto> signin(@RequestBody UsuarioPostDto dto) {
        return new ResponseEntity<>(userService.postLogin(dto), HttpStatus.OK);
    }

    @GetMapping("test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        return "Servidor OK";
    }

	@GetMapping("test/login")
    @ResponseStatus(HttpStatus.OK)
    public String testLogin() {
        return "Login OK";
    }


    @GetMapping("test/normal")
    @ResponseStatus(HttpStatus.OK)
    public String testNormal() {
        return "Normal OK";
    }

    @GetMapping("test/pago")
    @ResponseStatus(HttpStatus.OK)
    public String testPago() {
        return "Pago OK";
    }

    @GetMapping("test/gratis")
    @ResponseStatus(HttpStatus.OK)
    public String testGratis() {
        return "Gratis OK";
    }

}