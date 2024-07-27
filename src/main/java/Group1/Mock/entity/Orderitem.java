package Group1.Mock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name="order_item")
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="course_name")
    private String name;
    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public String toString() {
        return "Orderitem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", order=" + (order != null ? "Order{id=" + order.getId() + ", ...}" : "null") +
                '}';
    }
}
