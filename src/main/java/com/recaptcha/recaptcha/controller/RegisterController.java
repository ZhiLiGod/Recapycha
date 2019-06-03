package com.recaptcha.recaptcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recaptcha.recaptcha.dto.UserDto;
import com.recaptcha.recaptcha.exception.RegistrationException;
import com.recaptcha.recaptcha.validator.CaptchaValidator;

@RequestMapping("register")
@RestController
public class RegisterController {

  @Autowired
  private CaptchaValidator captchaValidator;

  @PostMapping("/register")
  @ResponseStatus(value = HttpStatus.OK)
  public String register(@RequestBody UserDto user) throws RegistrationException {
    boolean isValid = captchaValidator.validateCaptcha(user.getCaptchaResponse());
    if (!isValid) {
      throw new RegistrationException("Invalid User");
    }
    return user.getUserName();
  }

  @PostMapping
  public String registerWithAnnotation(@RequestBody UserDto user) {

  }

}
