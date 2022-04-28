package com.gouravThakur.rest.webservices.demoApp.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "all details about user")

//@JsonIgnoreProperties(value = "id")
@Entity
public class User {

    @Id
    @GeneratedValue
    public Integer id;

    @Size(min = 2,message = "name should be in atleast 2 characters")
    @ApiModelProperty(notes = "name should be in atleast 2 character")
    public String name;

    @Past
    @ApiModelProperty(notes = "birthdate should not be in past")
    public Date birthDate;


    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    protected User(){

    }

    public User(Integer id, String name,Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
