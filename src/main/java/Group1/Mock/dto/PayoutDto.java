package Group1.Mock.dto;

import Group1.Mock.entity.Payout;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayoutDto {
    private Long id;
    private Long userId;
    private Double amount;
    private Date createdAt;
    private PayoutStatus status;

    public enum PayoutStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
}
