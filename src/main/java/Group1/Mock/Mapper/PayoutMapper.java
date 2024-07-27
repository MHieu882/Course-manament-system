package Group1.Mock.Mapper;

import Group1.Mock.dto.PayoutDto;
import Group1.Mock.entity.Payout;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.UserRepository;

public class PayoutMapper {
    private static UserRepository userRepository;
    public static PayoutDto mapToPayoutDto(Payout payout) {
        if (payout == null) {
            return null;
        }

        PayoutDto payoutDto = new PayoutDto();
        payoutDto.setId(payout.getId());
        payoutDto.setUserId(payout.getUser().getId());
        payoutDto.setAmount(payout.getAmount());
        payoutDto.setCreatedAt(payout.getCreatedAt());
        payoutDto.setStatus(PayoutDto.PayoutStatus.valueOf(payout.getStatus().name()));

        return payoutDto;
    }
    public static Payout mapToPayout(PayoutDto payoutDto) {
        if (payoutDto == null) {
            return null;
        }

        Payout payout = new Payout();
        payout.setId(payoutDto.getId());
        // You will need to set the User entity separately in your service as it requires fetching from DB
        payout.setAmount(payoutDto.getAmount());
        payout.setStatus(Payout.PayoutStatus.valueOf(String.valueOf(payoutDto.getStatus())));
        // createdAt should typically be handled by the entity itself

        return payout;
    }
}
