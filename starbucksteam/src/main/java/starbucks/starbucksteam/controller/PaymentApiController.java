package starbucks.starbucksteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starbucks.starbucksteam.exception.BusinessException;
import starbucks.starbucksteam.exception.ResourceNotFoundException;
import starbucks.starbucksteam.model.*;
import starbucks.starbucksteam.model.enums.PaymentStatusEnum;
import starbucks.starbucksteam.repository.CardRepository;
import starbucks.starbucksteam.repository.OrderRepository;
import starbucks.starbucksteam.repository.PaymentRepository;
import starbucks.starbucksteam.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/v1/starbucks")
public class PaymentApiController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/{email}/{cardId}/{cardCode}/{orderId}")
    @Transactional
    public ResponseEntity<Object> createPayment(@PathVariable("email") String email,
                                                @PathVariable("cardId") Integer cardId,
                                                @PathVariable("cardCode") Integer cardCode,
                                                @PathVariable("orderId") Long orderId) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist :: " + email);
        }

        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new ResourceNotFoundException("Order does not exist :: " + orderId);
        }

        Optional<Card> card = cardRepository.findById(cardId);
        if (!card.isPresent()) {
            throw new ResourceNotFoundException("Card does not exist :: " + cardId);
        }

        if (card.get().getCardcode() != cardCode) {
            throw new BusinessException("Invalid card code.");
        }

        if (!card.get().getUser().getEmail().equals(email)) {
            throw new BusinessException("Card does not belong to the user.");
        }

        if (card.get().getBalance() < order.get().getBillAmount()) {
            throw new BusinessException("Insufficient balance :: " + card.get().getBalance());
        }

        Card c = card.get();
        c.setBalance(c.getBalance() - order.get().getBillAmount());
        c = cardRepository.save(c);

        Payment payment = new Payment();
        payment.setPaymentAmount(order.get().getBillAmount());
        payment.setPaymentStatus(PaymentStatusEnum.COMPLETE);
        payment.setCard(c);
        payment = paymentRepository.save(payment);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setBalance(c.getBalance());
        paymentResponse.setCardId(c.getCardid());
        paymentResponse.setPaymentStatus(payment.getPaymentStatus());

        return ResponseEntity.ok().body(paymentResponse);
    }


    @GetMapping("/{email}")
    @Transactional
    public ResponseEntity<Object> getAllPaymentsForCard(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist :: " + email);
        }

        return ResponseEntity.ok().body(paymentRepository.findAllForUser(email));
    }

    @GetMapping("/{email}/{cardId}")
    @Transactional
    public ResponseEntity<Object> getAllPaymentsForCard(@PathVariable("email") String email,
                                                        @PathVariable("cardId") Integer cardId) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist :: " + email);
        }

        Optional<Card> card = cardRepository.findById(cardId);
        if (!card.isPresent()) {
            throw new ResourceNotFoundException("Card does not exist :: " + cardId);
        }

        if (!card.get().getUser().getEmail().equals(email)) {
            throw new BusinessException("Card does not belong to the user.");
        }

        return ResponseEntity.ok().body(paymentRepository.findAllForCard(cardId));
    }

}
