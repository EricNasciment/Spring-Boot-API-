package com.spring.api.simpleproject.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.api.simpleproject.models.Task;
import com.spring.api.simpleproject.models.User;
import com.spring.api.simpleproject.repositorys.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;
   
   public Task findById(Long id) {

        Optional<Task> task = this.taskRepository.findById(id);
             return task.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada! id:" +id + "Tipo: " + Task.class.getName()
             ));    
    }

    @Transactional
    public Task CreateTask(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        return obj = this.taskRepository.save(obj);
        
    }

    @Transactional
    public Task updateTask(Task obj){
        Task newobj = findById(obj.getId());
        newobj.setDescription(obj.getDescription());
        return this.taskRepository.save(newobj);
    }

    public void deleteTask(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
           throw new RuntimeException("Não é possível excluir a tarefa pois há entidades relacionadas a ela.");
        }
    }


}
