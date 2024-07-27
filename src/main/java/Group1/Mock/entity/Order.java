package Group1.Mock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//one to one
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Orderitem> orderitems;
    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User student;

    private Double total;

    private String status;

}
