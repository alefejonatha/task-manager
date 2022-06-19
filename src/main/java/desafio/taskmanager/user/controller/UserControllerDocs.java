package desafio.taskmanager.user.controller;

import desafio.taskmanager.user.dto.JwtRequest;
import desafio.taskmanager.user.dto.JwtResponse;
import desafio.taskmanager.user.dto.UserDTO;
import desafio.taskmanager.user.dto.UserPutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface UserControllerDocs {

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Missing mandatory fields or incorrect field range value"),
            @ApiResponse(responseCode = "404", description = "The User has already been registered in the system")
    })
    void save(UserDTO userDTO);

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Missing mandatory fields, incorrect field range value")
    })
    void update(UserPutDTO userPutDTO);

    @Operation(summary = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    void delete(Long id);

    @Operation(summary = "User authentication operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success User authenticated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    JwtResponse createAuthenticationToken(JwtRequest jwtRequest);
}
