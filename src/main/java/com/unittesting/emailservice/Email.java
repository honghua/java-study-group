package com.unittesting.emailservice;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Email {
    abstract String getAddress();
    abstract String getSubject();
    abstract String getBody();
    abstract Format getFormat();

    public static Builder builder() {
        return new AutoValue_Email.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder setAddress(String address);
        abstract Builder setSubject(String subject);
        abstract Builder setBody(String body);
        abstract Builder setFormat(Format format);

        abstract Email build();
    }
}