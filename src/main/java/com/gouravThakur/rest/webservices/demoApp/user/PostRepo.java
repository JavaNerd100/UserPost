package com.gouravThakur.rest.webservices.demoApp.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
