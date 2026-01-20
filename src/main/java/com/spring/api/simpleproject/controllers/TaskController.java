package com.spring.api.simpleproject.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.api.simpleproject.models.Task;
import com.spring.api.simpleproject.models.User;
import com.spring.api.simpleproject.services.TaskService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/task")

public class TaskController {

@Autowired
    private TaskService taskService;

@GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        this.taskService.findById(id);
        return ResponseEntity.ok().build();
    }

 @PostMapping
    public ResponseEntity<Void> create(@RequestBody Task obj){
        this.taskService.createTask(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
 
 @PutMapping("/{id}")
 public ResponseEntity<Void> update(@RequestBody Task obj ,@PathVariable Long id){
       obj.setId(id);
       this.taskService.updateTask(obj);
       return ResponseEntity.noContent().build();

 }

 @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        this.taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
  
    

}
