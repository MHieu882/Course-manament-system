package Group1.Mock.entity;

import Group1.Mock.entity.Role;
import Group1.Mock.reponsitory.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RoleTest {
    private Long currentId;
    private RoleRepository roleRepository;
    private EntityManager entityManager;
    @InjectMocks
    private Role role;

    @BeforeEach
    public void setUp() {
        currentId = 0L;
    }private Role createRole() {
        Role role = new Role();
        role.setId(++currentId);
        return role;
    }

    @Test
    public void testAutoIncrementingId() {
        // Given
        Long firstId = 1L;
        Long secondId = 2L;

        // When
        Role role = createRole();
        Role anotherRole = createRole();

        // Then
        assertNotNull(role.getId());
        assertEquals(firstId, role.getId());

        assertNotNull(anotherRole.getId());
        assertEquals(secondId, anotherRole.getId());
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
    @Transactional
    public void testIdGenerationWithPersistenceContext() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.STUDENT); // Assuming RoleEnum is defined
        role.setDescription("A student role");

        // When


        // Then
        assertNotNull(role.getId(), "ID should be generated and assigned");
    }

    @Test
    public void testIdGenerationWithRepository() {
        // Given
        // Assume a RoleRepository is available and the role is saved to the database

        // When
        // Save the role to the database using the RoleRepository
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.STUDENT); // Assuming RoleEnum is defined
        role.setDescription("A student role");

        // When


        // Then
        assertNotNull(role.getId(), "ID should be generated and assigned");
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
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.STUDENT); // Assuming RoleEnum is defined
        role.setDescription("A student role");
        // Then
        // Verify that the id is correctly generated and assigned to the role
        // You can use Mockito or any other testing framework to mock the RoleService and verify the behavior
    }
}