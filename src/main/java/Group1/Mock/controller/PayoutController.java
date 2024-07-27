package Group1.Mock.controller;

import Group1.Mock.dto.PayoutDto;
import Group1.Mock.entity.Payout;
import Group1.Mock.entity.User;
import Group1.Mock.service.PayoutService;
import Group1.Mock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payouts")
public class PayoutController {
    @Autowired
    private PayoutService payoutService;

    @Autowired
    private UserService userService;

    @PostMapping("/request")
    public PayoutDto requestPayout(@RequestParam Double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return payoutService.requestPayout(user, amount);
    }

    @GetMapping
    public List<PayoutDto> getPayouts(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<PayoutDto> payouts = payoutService.getPayoutsForUser(user.getId());
        return payouts;
    }
}