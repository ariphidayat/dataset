package id.arip.dataset.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;
    private String task;
    @Column(name = "booked_by")
    private String bookedBy;
    private Integer deleted;
}
