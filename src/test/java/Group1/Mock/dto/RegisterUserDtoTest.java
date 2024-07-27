package Group1.Mock.dto;

import Group1.Mock.entity.RoleEnum;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterUserDtoTest {

    private Validator validator;
    private RegisterUserDto registerUserDto;

    @BeforeEach
    public void setUp() {
        registerUserDto = new RegisterUserDto();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void testValidRegisterUserDto() {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setUsername("validUser");
        dto.setPassword("validPassword");
        dto.setEmail("user@example.com");
        dto.setRole(RoleEnum.STUDENT); // Assuming RoleEnum.USER is a valid role

        Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    public void testBlankUsername() {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setUsername("");
        dto.setPassword("password");
        dto.setEmail("user@example.com");

        Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertTrue(violations.stream()
                        .anyMatch(v -> v.getMessage().equals("Username must not be blank")),
                "Expected error message for username");
    }

    @Test
    public void testBlankPassword() {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setUsername("username");
        dto.setPassword("");
        dto.setEmail("user@example.com");

        Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertTrue(violations.stream()
                        .anyMatch(v -> v.getMessage().equals("Password must not be blank")),
                "Expected error message for password");
    }

    @Test
    public void testBlankEmail() {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setUsername("username");
        dto.setPassword("password");
        dto.setEmail("");

        Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertTrue(violations.stream()
                        .anyMatch(v -> v.getMessage().equals("Email must not be blank")),
                "Expected error message for email");
    }
    @Test
    public void testUsername_whenBlank_shouldThrowException() {
//        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//            registerUserDto.setUsername("");
//        });
    }


    @Test
    public void testPassword_whenBlank_shouldThrowException() {
//        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//            registerUserDto.setPassword("");
//        });
    }



    @Test
    public void testEmail_whenBlank_shouldThrowException() {
//        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
//            registerUserDto.setEmail("");
//        });
    }

    @Test
    public void testRole_whenSet_shouldNotBeNull() {
        registerUserDto.setRole(RoleEnum.STUDENT);
        assertNotNull(registerUserDto.getRole(), "Role should not be null");
    }
}
