package Group1.Mock.entity;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testAddSubscription() {
        // Given
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName(RoleEnum.STUDENT);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName(RoleEnum.INSTRUCTOR);

        User user1 = new User("user1", "password1", "email1", role1);
        User user2 = new User("user2", "password2", "email2", role2);

        // When
        user1.addSubscription(user2);

        // Then
        assertEquals(1, user1.getSubscriptions().size());
        assertTrue(user1.getSubscriptions().contains(user2));
    }

    @Test
    public void testUnsubscribe() {
        // Given
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName(RoleEnum.STUDENT);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName(RoleEnum.INSTRUCTOR);

        User user1 = new User("user1", "password1", "email1", role1);
        User user2 = new User("user2", "password2", "email2", role2);
        user1.addSubscription(user2);

        // When
        user1.unsubscribe(user2.getId());

        // Then
        assertEquals(0, user1.getSubscriptions().size());
    }

    @Test
    public void testGetAuthorities() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.INSTRUCTOR);

        User user = new User("user1", "password1", "email1", role);

        // When
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Then
        assertEquals(1, authorities.size());
//        assertEquals("INSTRUCTOR", authorities.iterator().next().getAuthority());
    }

    @Test
    public void testIsAccountNonExpired() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.STUDENT);

        User user = new User("user1", "password1", "email1", role);

        // When
        boolean isAccountNonExpired = user.isAccountNonExpired();

        // Then
        assertTrue(isAccountNonExpired);
    }

    @Test
    public void testIsAccountNonLocked() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.STUDENT);

        User user = new User("user1", "password1", "email1", role);

        // When
        boolean isAccountNonLocked = user.isAccountNonLocked();

        // Then
        assertTrue(isAccountNonLocked); // Assuming the user is not blocked initially

        // When
        user.setBlocked(true);
        isAccountNonLocked = user.isAccountNonLocked();

        // Then
        assertFalse(isAccountNonLocked);
    }
}
