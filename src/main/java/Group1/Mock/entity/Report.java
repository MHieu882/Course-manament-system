package Group1.Mock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportId;

    private String reportReason;

    @CreationTimestamp
    private Date reportDate;

    @ManyToOne
    @JoinColumn(
            name = "user_id",referencedColumnName = "id"
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "course_id",referencedColumnName = "course_id"
    )
    private Course course;
}
