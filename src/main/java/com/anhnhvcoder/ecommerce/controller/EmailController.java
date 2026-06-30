package com.anhnhvcoder.ecommerce.controller;

import com.anhnhvcoder.ecommerce.request.VerifyEmailRequest;
import com.anhnhvcoder.ecommerce.response.ApiResponse;
import com.anhnhvcoder.ecommerce.service.Impl.EmailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verifyEmail(@NonNull @RequestBody VerifyEmailRequest request){

        try {
            return ResponseEntity.ok(new ApiResponse(200, emailService.verifyEmail(request)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, e.getMessage()));
        }
    }

}
