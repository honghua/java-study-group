package unittest.emailservice;

public interface DeliveryPlatform {

    void deliver(Email email);

    String getServiceStatus();

    AuthenticationStatus authenticate(Credentials credentials);
}
