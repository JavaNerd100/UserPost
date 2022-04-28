package com.gouravThakur.rest.webservices.demoApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
@RequestMapping("jpa/")
public class UserControllerWithJpa{

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/users")
    public List<User> findAllUser(){
        return usersRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findOneUser(@PathVariable int id){
        Optional<User> users = usersRepo.findById(id);
        if(!users.isPresent()){
            throw new UserNotFoundException("id -" + id);
        }
        EntityModel<User> resource = EntityModel.of(users.get());

        WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).findAllUser());

        resource.add(linkTo.withRel("all-users"));
        return resource;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        User savedUser =usersRepo.save(user);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        usersRepo.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public Optional<User> findAllUser(@PathVariable int id){
        Optional<User> user = usersRepo.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id -" + id);
        }
        user.get().getPosts();

        return user;
    }


    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> savePost(@PathVariable int id,@RequestBody Post post){
        Optional<User> userOptional = usersRepo.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id -" + id);
        }
       User user = userOptional.get();
        post.setUser(user);
        postRepo.save(post);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}


