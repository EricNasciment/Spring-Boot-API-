package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    @Column(name = "password", nullable = false, length = 60) //nome da coluna password com nullable false e length 60!)
    @NotBlank(groups = {CreateUser.class, UpdateUser.class})  //validação para não aceitar string vazia e nem nula!
    @Size(groups = {CreateUser.class,UpdateUser.class},min = 8, max = 60) //validação de tamanho minimo para 6 e máximo 60!
    private String password;


    // private List<task> tasks = arrayList<task>();


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

   



}
