package com.gouravThakur.rest.webservices.demoApp.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User,Integer> {
}
