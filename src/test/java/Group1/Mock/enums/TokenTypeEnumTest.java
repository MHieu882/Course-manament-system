package Group1.Mock.enums;

import Group1.Mock.enums.TokenType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenTypeEnumTest {

    @Test
    public void testEnumValues() {
        // Test if all expected enum values are present
        TokenType[] values = TokenType.values();

        assertThat(values).hasSize(2);
        assertThat(values).contains(TokenType.VERIFICATION_EMAIL);
        assertThat(values).contains(TokenType.FORGOT_PASSWORD);
    }

    @Test
    public void testEnumFromString() {
        // Test conversion from string to enum
        TokenType verificationEmail = TokenType.valueOf("VERIFICATION_EMAIL");
        TokenType forgotPassword = TokenType.valueOf("FORGOT_PASSWORD");

        assertThat(verificationEmail).isEqualTo(TokenType.VERIFICATION_EMAIL);
        assertThat(forgotPassword).isEqualTo(TokenType.FORGOT_PASSWORD);
    }

    @Test
    public void testEnumToString() {
        // Test conversion from enum to string
        String verificationEmailString = TokenType.VERIFICATION_EMAIL.name();
        String forgotPasswordString = TokenType.FORGOT_PASSWORD.name();

        assertThat(verificationEmailString).isEqualTo("VERIFICATION_EMAIL");
        assertThat(forgotPasswordString).isEqualTo("FORGOT_PASSWORD");
    }
}
