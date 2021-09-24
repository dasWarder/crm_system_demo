package com.example.web;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public abstract class AbstractTest {

  @RegisterExtension
  protected static GreenMailExtension greenMailExtension =
      new GreenMailExtension(ServerSetupTest.SMTP)
          .withConfiguration(GreenMailConfiguration.aConfig().withUser("tester", "12345"))
          .withPerMethodLifecycle(false);

  ;
}
