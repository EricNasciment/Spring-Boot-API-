package com.spring.api.simpleproject.repositorys;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.api.simpleproject.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    

    List<Task> findByUser_Id(Long id);

    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")     // os dois ":" se refere ao parametro da função
    //List<Task> findByUser_Id(@Param ("id")Long id);

    //@Query(value = "SELECT t FROM Task t WHERE t.user_id = :id")
    //List<Task> findByUser_Id(@Param ("id") Long id);
}
