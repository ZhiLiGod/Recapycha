package com.recaptcha.recaptcha.captcha;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.recaptcha.recaptcha.exception.RegistrationException;
import com.recaptcha.recaptcha.validator.CaptchaValidator;

@Aspect
@Component
public class CaptchaAspect {

  private static final String CAPTCHA_HEADER_NAME = "captcha-response";

  @Autowired
  private CaptchaValidator captchaValidator;

  // run this before code annotated.
  @Around("@annotation(RequiresCaptcha)")
  public Object validateCaptcha(ProceedingJoinPoint joinPoint) throws Throwable {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String captResponse = request.getHeader(CAPTCHA_HEADER_NAME);
    boolean isValid = captchaValidator.validateCaptcha(captResponse);

    if (!isValid) {
      throw new RegistrationException("Invalid User");
    }

    return joinPoint.proceed();
  }

}
