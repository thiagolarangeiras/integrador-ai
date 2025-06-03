package com.satc.integrador.ai.auth;

import com.satc.integrador.ai.auth.dto.LoginUserDto;
import com.satc.integrador.ai.auth.dto.RecoveryJwtTokenDto;
import com.satc.integrador.ai.studyplan.OpenAiExample;
import com.satc.integrador.ai.user.dto.CreatedLoggedUserDto;
import com.satc.integrador.ai.user.dto.UsuarioPostDto;
import com.satc.integrador.ai.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {
    @Autowired private OpenAiExample gpt;

    @Autowired
    private UsuarioService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("auth/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto dto) {
        RecoveryJwtTokenDto recoveryJwtTokenDto = authService.authenticateUser(dto.username(), dto.password());
        return new ResponseEntity<>(recoveryJwtTokenDto, HttpStatus.OK);
    }

    @PostMapping("auth/signin")
    public ResponseEntity<CreatedLoggedUserDto> signin(@RequestBody UsuarioPostDto dto) {
        return new ResponseEntity<>(userService.post(dto), HttpStatus.OK);
    }

    @GetMapping("test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        OpenAiExample.chat();
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