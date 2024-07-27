package Group1.Mock.dto;

import Group1.Mock.enums.ApproveType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveRequest {
    private Long id;
    private ApproveType status;
}
