package com.codekopf.api.application.users;

import com.codekopf.api.domain.users.UserRepository;
import com.codekopf.api.domain.users.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Transactional
@RestController
@RequestMapping(UserController.USER_API_PATH)
public class UserController {

    public final static String USER_API_PATH = "/api/v1/users";

    private final UserService userService;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userService = new UserService(userRepository);
    }

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(AuthorityHelper.AUTHORIZED_TO_DO_ANYTHING)
    public ResponseEntity<OutgoingUserDTO> createNewUser(@RequestBody final IncomingUserDTO incomingUserDTO) {
        val user = incomingUserDTO.toDomainObject();
        this.userService.createUser(user);
        return ResponseEntity.ok(new OutgoingUserDTO(this.userService.findById(user.getId())));
    }

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.GET,
            value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(AuthorityHelper.AUTHORIZED_TO_DO_ANYTHING)
    public ResponseEntity<OutgoingUserDTO> getUser(@PathVariable("id") final UUID id) {
        val user = this.userService.findById(id);
        return ResponseEntity.ok(new OutgoingUserDTO(user));
    }

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<OutgoingUserDTO>> getUsers() {
        val users = this.userService.findAll();
        return ResponseEntity.ok(users.stream()
                                      .map(OutgoingUserDTO::new)
                                      .collect(toList()));
    }

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.PUT,
            value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(AuthorityHelper.AUTHORIZED_TO_DO_ANYTHING)
    public ResponseEntity<OutgoingUserDTO> updateUser(@PathVariable("id") final UUID id,
                                                      @RequestBody final IncomingUserDTO incomingUserDTO) {
        val newUser = incomingUserDTO.toDomainObject();
        val oldUser = this.userService.findById(id);
        val updatedUser = oldUser.update(newUser);
        this.userService.updateUser(updatedUser);
        return ResponseEntity.ok(new OutgoingUserDTO(this.userService.findById(id)));
    }


    @ResponseBody
    @RequestMapping(
            method = RequestMethod.DELETE,
            value="/{id}"
    )
    @PreAuthorize(AuthorityHelper.AUTHORIZED_TO_DO_ANYTHING)
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") final UUID id) {
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
