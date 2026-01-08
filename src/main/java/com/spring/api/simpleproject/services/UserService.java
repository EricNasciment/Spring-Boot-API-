package com.spring.api.simpleproject.services;

import com.spring.api.simpleproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import com.spring.api.simpleproject.repositorys.TaskRepository;
import com.spring.api.simpleproject.repositorys.UserRepository;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    

    public User findById(Long id) {

        Optional<User> user = this.userRepository.findById(id);
             return user.orElseThrow(() -> new RuntimeException(
                "usuário não encontrado! id:" +id + "Tipo: " + User.class.getName()
             ));    
    }  

    @Transactional
    public User CreateUser(User obj){

        obj = this.userRepository.save(obj);
        return obj;

    }

    @Transactional
    public User UpdatePassword(User obj){
        User newobj = findById(obj.getId());
        newobj.setPassword(obj.getPassword());
        return this.userRepository.save(newobj);
    }

    public void deleteUser(Long id){
        findById(id);
        try {
           this.userRepository.deleteById(id); 
        } catch (Exception e) {
            throw new RuntimeException  ("Não é possível excluir o usuário pois há entidades relacionadas a ele.");
        }
    }

}
