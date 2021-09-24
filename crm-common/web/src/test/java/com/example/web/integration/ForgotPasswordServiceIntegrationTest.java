package com.example.web.integration;

import com.example.exception.PasswordResetTokenNotFoundException;
import com.example.exception.ResetTokenExpiryException;
import com.example.exception.UserNotFoundException;
import com.example.exception.WrongPasswordException;
import com.example.model.user.User;
import com.example.service.user.ForgotPasswordService;
import com.example.web.AbstractTest;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static com.example.web.data.TestTokenData.TEST_TOKEN_1;
import static com.example.web.data.TestUserData.TEST_USER_1;

@Slf4j
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/token/populate_password_reset_token_table.sql"
    })
public class ForgotPasswordServiceIntegrationTest extends AbstractTest {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired private ForgotPasswordService forgotPasswordService;

  @RegisterExtension
  static GreenMailExtension greenMailExtension =
      new GreenMailExtension(ServerSetupTest.SMTP)
          .withConfiguration(GreenMailConfiguration.aConfig().withUser("tester", "12345"))
          .withPerMethodLifecycle(false);

  @Test
  public void shouldProcessForgotPasswordProperly()
      throws UserNotFoundException, MessagingException, IOException {

    log.info("Test processForgotPassword() method");

    forgotPasswordService.processForgotPassword(TEST_USER_1.getEmail());
    MimeMessage[] receivedMessages = greenMailExtension.getReceivedMessages();

    for(MimeMessage message : receivedMessages) {
      Assert.assertNotNull(message.getContent());
    }
  }

  @Test
  public void shouldDirectResetPasswordProperly()
      throws PasswordResetTokenNotFoundException, ResetTokenExpiryException {

    log.info("Test directResetPassword() method");

    String validToken = forgotPasswordService.directResetPassword(TEST_TOKEN_1.getToken());

    Assert.assertNotNull(validToken);
    Assert.assertEquals(TEST_TOKEN_1.getToken(), validToken);
  }

  @Test
  public void shouldRefreshUserPasswordProperly() throws UserNotFoundException, PasswordResetTokenNotFoundException, WrongPasswordException, MessagingException, IOException {

    log.info("Test resetUserPassword() method");

    String updatedPass = "reset";
    User user = forgotPasswordService.resetUserPassword(updatedPass, updatedPass, TEST_TOKEN_1.getToken());
    MimeMessage[] receivedMessages = greenMailExtension.getReceivedMessages();

    for(MimeMessage message : receivedMessages) {
      Assert.assertNotNull(message.getContent());
    }

    Assert.assertNotNull(user);
    Assert.assertTrue(passwordEncoder.matches(updatedPass, user.getPassword()));
  }
}
