package com.satitulasi.taskmanagementsystem.dto;

import com.satitulasi.taskmanagementsystem.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
}