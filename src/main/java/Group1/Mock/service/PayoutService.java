package Group1.Mock.service;

import Group1.Mock.Mapper.PayoutMapper;
import Group1.Mock.dto.PayoutDto;
import Group1.Mock.entity.Payout;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.PayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PayoutService {
    @Autowired
    private PayoutRepository payoutRepository;

    public PayoutDto requestPayout(User user, Double amount) {
        Payout payout = new Payout();
        payout.setUser(user);
        payout.setAmount(amount);
        payout.setStatus(Payout.PayoutStatus.PENDING);
        return PayoutMapper.mapToPayoutDto(payoutRepository.save(payout));

    }


    public List<PayoutDto> getPayoutsForUser(Long userId) {
        List<PayoutDto> payoutDtos = new ArrayList<>();
        List<Payout> payouts = payoutRepository.findByUserId(userId);
        for (Payout payout : payouts) {
            PayoutDto payoutDto = PayoutMapper.mapToPayoutDto(payout);
            payoutDtos.add(payoutDto);
        }
        return payoutDtos;
    }
}