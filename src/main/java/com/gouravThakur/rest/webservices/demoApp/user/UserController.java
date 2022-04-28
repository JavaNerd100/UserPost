package com.gouravThakur.rest.webservices.demoApp.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDbService userDbService;

    @GetMapping("/users")
    public List<User> findAllUser(){
        return userDbService.findAllUser();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findOneUser(@PathVariable int id){
        User users = userDbService.findOneUser(id);
        if(users==null) {
            throw new UserNotFoundException("id -" + id);
        }

        EntityModel<User> resource = EntityModel.of(users);

        WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).findAllUser());

        resource.add(linkTo.withRel("all-users"));
        return resource;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser( @RequestBody User user){
        User savedUser = UserDbService.saveUser(user);

     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User users = userDbService.deleteById(id);
        if(users==null) {
            throw new UserNotFoundException("id -" + id);
        }
    }

}
