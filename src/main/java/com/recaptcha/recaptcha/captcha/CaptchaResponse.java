package com.recaptcha.recaptcha.captcha;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaptchaResponse {

  private Boolean success;
  private Timestamp timestamp;
  private String hostname;
  @JsonProperty("error-codes")
  private List<String> errorCodes;

}
