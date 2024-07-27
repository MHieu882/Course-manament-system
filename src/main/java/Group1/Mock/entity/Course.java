package Group1.Mock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;


    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "course_level")
    private String courseLevel;

    @Column(name = "language")
    private String language;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_category",
            joinColumns = { @JoinColumn(name = "course_id", referencedColumnName = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName = "category_id") }
    )
    private Set<Category> category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="course_instructor",
            joinColumns = { @JoinColumn(name = "course_id", referencedColumnName = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id",referencedColumnName = "id")}
    )
    private Set<User> instructor;

    @OneToMany(mappedBy = "courses", orphanRemoval = true)
    private Set<Review> reviews;
}
