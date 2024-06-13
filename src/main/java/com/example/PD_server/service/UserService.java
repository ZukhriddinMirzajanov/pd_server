package com.example.PD_server.service;

import com.example.PD_server.dto.*;
import com.example.PD_server.model.*;
import com.example.PD_server.repository.*;
import com.example.PD_server.response.AuthenticationResponse;
import com.example.PD_server.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final OfferRepository offerRepository;

    @Autowired
    private final RequestRepository requestRepository;

    @Autowired
    private final RegionRepository regionRepository;

    @Autowired
    private final PlaceRepository placeRepository;

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.save(user);
        userRepository.deleteById(id);
    }

    public AuthenticationResponse updateUser(User user) {
        userRepository.save(user);
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }

    public List<ProductDto> getUserProducts() {
        // Assuming that the user's ID or username can be retrieved from the security context
        String username = "currentLoggedInUser"; // Replace with actual logic to get current logged-in user
        return productRepository.findByUserUsername(username).stream()
                .map(product -> new ProductDto(product.getProductCode(), product.getProductName(), product.getProductPrice(), product.getProductImage()))
                .collect(Collectors.toList());
    }

    public void addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductCode(productDto.getProductCode());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductImage(productDto.getProductImage());
        productRepository.save(product);
    }

    public List<RegionDto> getAllRegions() {
        return regionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private RegionDto convertToDto(Region region) {
        RegionDto regionDto = new RegionDto();
        regionDto.setId(region.getId());
        regionDto.setName(region.getName());
        return regionDto;
    }

    public void addOffer(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setOfferCode(offerDto.getOfferCode());
        offer.setPlaceName(offerDto.getPlaceName());
        offer.setProductCode(offerDto.getProductCode());
        offerRepository.save(offer);
    }

    public List<OfferDto> getUserOffers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return offerRepository.findByUserUsername(username).stream()
                .map(offer -> new OfferDto(offer.getOfferCode(), offer.getPlaceName(), offer.getProductCode()))
                .collect(Collectors.toList());
    }

    public void addRequest(RequestDto requestDto) {
        Request request = new Request();
        request.setRequestCode(requestDto.getRequestCode());
        request.setPlaceName(requestDto.getPlaceName());
        request.setProductCode(requestDto.getProductCode());
        requestRepository.save(request);
    }

    public List<RequestDto> getUserRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return requestRepository.findByUserUsername(username).stream()
                .map(request -> new RequestDto(request.getRequestCode(), request.getPlaceName(), request.getProductCode()))
                .collect(Collectors.toList());
    }

    public List<TransactionDto> getUserTransactions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return transactionRepository.findByUserUsername(username).stream()
                .map(transaction -> new TransactionDto(transaction.getTransactionCode(), transaction.getScore()))
                .collect(Collectors.toList());
    }

    public boolean evaluateTransaction(TransactionDto evaluationDto) {
        Optional<Transaction> transactionOpt = transactionRepository.findByTransactionCode(evaluationDto.getTransactionCode());
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            int score = evaluationDto.getScore();
            if (score >= 1 && score <= 10) {
                transaction.setScore(score);
                transactionRepository.save(transaction);
                return true;
            }
        }
        return false;
    }
}
