package com.spring.api.simpleproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;  

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table (name = User.Table_Name)
public class User {

    public interface CreateUser{}
    public interface UpdateUser{}

    public static final String Table_Name = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrementação!
    @Column(name = "id", unique = true) //nome da coluna id com unique true!
    private Long id;

    @Column(name = "userName", nullable = false, length = 100) //nome da coluna userName com nullable false e length 100!
    @NotBlank(groups = {CreateUser.class})  //validação para não aceitar string vazia e nem nula!   
    @Size(groups = {CreateUser.class} ,min = 3, max = 100)//validação tamanho minimo 3 e máximo 100!
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//para não retornar a senha na resposta da api!
    @Column(name = "password", nullable = false, length = 60) //nome da coluna password com nullable false e length 60!)
    @NotBlank(groups = {CreateUser.class, UpdateUser.class})  //validação para não aceitar string vazia e nem nula!
    @Size(groups = {CreateUser.class,UpdateUser.class},min = 8, max = 60) //validação de tamanho minimo para 6 e máximo 60!
    private String password;

    @OneToMany(mappedBy = "user") //um para muitos com a entidade task!)
    private List<Task> tasks = new ArrayList<Task>();


    public User(){}

    public User( Long id,String username,String password){
         this.id = id;
         this.userName = username;
         this.password = password;
    }



    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setuserName(String username){
        this.userName = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override   
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof User))
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.userName, other.userName) 
               && Objects.equals(this.password, other.password);
    }


}
