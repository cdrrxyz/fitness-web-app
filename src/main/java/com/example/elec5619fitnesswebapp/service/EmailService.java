package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.pre.PreUser;
import com.example.elec5619fitnesswebapp.pre.PreUserToUser;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sendingMail;

    /**
     * Randomly generate 4-digit verification code
     * @return String code
     */
    public String generateVerificationCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++){
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * Send verification mail
     * @param preUser
     * @return
     */
    public boolean sendVerificationMail(PreUser preUser) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("Email Verification");

            // Get verification code
            String code = generateVerificationCode();
            String email = preUser.getEmail();

            preUser.setCode(code);


            mailMessage.setText("Here is your verification code:\n"
                    + "\n"+code+"\n"
                    + "\nPlease note that this is an automatically generated email, replies will not be answered.");


            mailMessage.setTo(email);
            mailMessage.setFrom(sendingMail);
            mailSender.send(mailMessage);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if the verification code is the same
     * @param preUser
     * @param inputCode
     * @return
     */
    public boolean registered(PreUser preUser, String inputCode) {
        String verCode = preUser.getCode();
        String email = preUser.getEmail();

        if (email == null || email.isEmpty()) {
            return false;
        } else if (!inputCode.equals(verCode)){
            return false;
        }

        // Save user data
        User user = PreUserToUser.toUser(preUser);

        // Insert data to database
        userRepository.save(user);

        return true;
    }


}
