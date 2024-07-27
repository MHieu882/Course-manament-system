package Group1.Mock.dto;

import Group1.Mock.entity.Role;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class LoginUserDtoTest {

    private Validator validator;
    @InjectMocks
    private Role role;
    @BeforeEach
    public void setUp() {
        role = new Role();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidLoginUserDto() {
        LoginUserDto dto = new LoginUserDto();
        dto.setUsername("validUser");
        dto.setPassword("validPassword");

        Set<ConstraintViolation<LoginUserDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    public void testBlankUsername() {
        LoginUserDto dto = new LoginUserDto();
        dto.setUsername("");
        dto.setPassword("password");

        Set<ConstraintViolation<LoginUserDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertTrue(violations.stream()
                        .anyMatch(v -> v.getMessage().equals("Username must not be blank")),
                "Expected error message for username");
    }

    @Test
    public void testBlankPassword() {
        LoginUserDto dto = new LoginUserDto();
        dto.setUsername("username");
        dto.setPassword("");

        Set<ConstraintViolation<LoginUserDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Expected validation errors");
        assertTrue(violations.stream()
                        .anyMatch(v -> v.getMessage().equals("Password must not be blank")),
                "Expected error message for password");
    }
    @Test
    public void testIdGeneration() {
        // Given
        Long expectedId = 1L;

        // When
        role.setId(expectedId);

        // Then
        assertNotNull(role.getId());
        assertEquals(expectedId, role.getId());
    }

    @Test
    public void testAutoIncrementingId() {
        // Given
        Long firstId = 1L;
        Long secondId = 2L;

        // When
        role.setId(firstId);
        Role anotherRole = new Role();

        // Then
        assertNotNull(role.getId());
        assertEquals(firstId, role.getId());

//        assertNotNull(anotherRole.getId());
//        assertEquals(secondId, anotherRole.getId());
    }

    @Test
    public void testIdGenerationWithPersistenceContext() {
        // Given
        // Assume a persistence context is available and the role is managed

        // When
        // Perform database operations or entity manager operations

        // Then
        // Verify that the id is correctly generated and assigned to the role
        // You can use Mockito or any other testing framework to mock the persistence context and verify the behavior
    }

    @Test
    public void testIdGenerationWithRepository() {
        // Given
        // Assume a RoleRepository is available and the role is saved to the database

        // When
        // Save the role to the database using the RoleRepository

        // Then
        // Verify that the id is correctly generated and assigned to the role
        // You can use Mockito or any other testing framework to mock the RoleRepository and verify the behavior
    }

    @Test
    public void testIdGenerationWithService() {
        // Given
        // Assume a RoleService is available and the role is created using the service

        // When
        // Create a role using the RoleService

        // Then
        // Verify that the id is correctly generated and assigned to the role
        // You can use Mockito or any other testing framework to mock the RoleService and verify the behavior
    }
}
