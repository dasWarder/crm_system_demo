package com.example.repository;

import com.example.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    Page<Task> getTasksByStartFromAfter(LocalDateTime startFrom, Pageable pageable);

    Page<Task> getTasksByDeadlineBefore(LocalDateTime deadline, Pageable pageable);

    @Override
    Page<Task> findAll(Pageable pageable);

    Page<Task> getTasksByDeadlineAfter(LocalDateTime date, Pageable pageable);

    Page<Task> getTasksByStartFromAfterAndDeadlineBefore(LocalDateTime startTime, LocalDateTime deadline, Pageable pageable);
}
