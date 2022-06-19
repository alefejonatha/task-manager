package desafio.taskmanager.user.controller;

import desafio.taskmanager.user.dto.JwtRequest;
import desafio.taskmanager.user.dto.JwtResponse;
import desafio.taskmanager.user.dto.UserDTO;
import desafio.taskmanager.user.dto.UserPutDTO;
import desafio.taskmanager.user.service.AuthenticationService;
import desafio.taskmanager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements  UserControllerDocs{

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PutMapping
    public void update(@RequestBody @Valid UserPutDTO userPutDTO) {
        userService.update(userPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping(value = "/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody @Valid JwtRequest jwtRequest) {
        return authenticationService.createAuthenticationToken(jwtRequest);
    }

}
