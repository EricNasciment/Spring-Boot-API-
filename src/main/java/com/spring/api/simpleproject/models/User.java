package com.spring.api.simpleproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;  

import java.util.List;
import java.util.ArrayList;



import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table (name = User.Table_Name)
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@EqualsAndHashCode
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //para não retornar as tarefas na resposta da api!
    private List<Task> tasks = new ArrayList<Task>();


   


}
