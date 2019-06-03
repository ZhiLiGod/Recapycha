package com.recaptcha.recaptcha.captcha;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    return joinPoint.proceed();
  }

}
