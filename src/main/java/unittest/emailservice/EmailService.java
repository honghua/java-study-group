package unittest.emailservice;

public class EmailService {

    private DeliveryPlatform platform;

    public EmailService(DeliveryPlatform platform) {
        this.platform = platform;
    }

    public void send(String to, String subject, String body, boolean html) {
        if (to == null) {
            throw new IllegalArgumentException("To address is not valid!");
        }
        Format format = Format.TEXT_ONLY;
        if (html) {
            format = Format.HTML;
        }
        Email email = Email.builder()
                .setAddress(to)
                .setSubject(subject)
                .setBody(body)
                .setFormat(format)
                .build();
        platform.deliver(email);
    }

    public ServiceStatus checkServiceStatus() {
        if (platform.getServiceStatus().equals("OK")) {
            return ServiceStatus.UP;
        } else {
            return ServiceStatus.DOWN;
        }
    }

    public boolean authenticatedSuccessfully(Credentials credentials) {
        return platform.authenticate(credentials).equals(AuthenticationStatus.AUTHENTICATED);
    }
}
