package unittest.emailservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {
    @Mock
    DeliveryPlatform platform;

    @InjectMocks
    EmailService emailService;

    @Captor
    ArgumentCaptor<Email> emailCaptor;

    @Captor
    ArgumentCaptor<Credentials> credentialsCaptor;

    @Test
    public void whenDoesNotSupportHtml_expectTextOnlyEmailFormat() {
        String to = "info@baeldung.com";
        String subject = "Using ArgumentCaptor";
        String body = "Hey, let'use ArgumentCaptor";

        emailService.send(to, subject, body, false);

        Mockito.verify(platform).deliver(emailCaptor.capture());

        Email emailCaptorValue = emailCaptor.getValue();
        assertEquals(Format.TEXT_ONLY, emailCaptorValue.getFormat());
    }

    @Test
    public void whenDoesSupportHtml_expectHTMLEmailFormat() {
        String to = "info@baeldung.com";
        String subject = "Using ArgumentCaptor";
        String body = "<html><body>Hey, let'use ArgumentCaptor</body></html>";

        emailService.send(to, subject, body, true);

        Mockito.verify(platform).deliver(emailCaptor.capture());
        Email value = emailCaptor.getValue();
        assertEquals(Format.HTML, value.getFormat());
    }

    @Test
    public void whenServiceRunning_expectUpResponse() {
        when(platform.getServiceStatus()).thenReturn("OK");

        ServiceStatus serviceStatus = emailService.checkServiceStatus();

        assertEquals(ServiceStatus.UP, serviceStatus);
    }

    @Test
    public void whenServiceNotRunning_expectDownResponse() {
        when(platform.getServiceStatus()).thenReturn("Error");

        ServiceStatus serviceStatus = emailService.checkServiceStatus();

        assertEquals(ServiceStatus.DOWN, serviceStatus);
    }

    @Test
    public void whenUsingArgumentMatcherForValidCredentials_expectTrue() {
        Credentials credentials = new Credentials("baeldung", "correct_password", "correct_key");
        when(platform.authenticate(Mockito.eq(credentials))).thenReturn(AuthenticationStatus.AUTHENTICATED);

        assertTrue(emailService.authenticatedSuccessfully(credentials));
    }

    @Test
    public void whenUsingArgumentCaptorForValidCredentials_expectTrue() {
        Credentials credentials = new Credentials("baeldung", "correct_password", "correct_key");
        when(platform.authenticate(credentialsCaptor.capture())).thenReturn(AuthenticationStatus.AUTHENTICATED);

        assertTrue(emailService.authenticatedSuccessfully(credentials));
        assertThat(credentialsCaptor.getValue()).isEqualTo(credentials);
    }

    @Test
    public void whenNotAuthenticated_expectFalse() {
        Credentials credentials = new Credentials("baeldung", "incorrect_password",
                "incorrect_key");
        when(platform.authenticate(Mockito.eq(credentials))).thenReturn(AuthenticationStatus.NOT_AUTHENTICATED);

        assertFalse(emailService.authenticatedSuccessfully(credentials));
    }

    @Test
    public void invalidToAddress_throwException() {
        String to = null;
        String subject = "Using ArgumentCaptor";
        String body = "<html><body>Hey, let'use ArgumentCaptor</body></html>";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> emailService.send(to, subject, body, false));

        assertThat(exception.getMessage()).contains("address is not valid");
    }
}