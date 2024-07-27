package Group1.Mock.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleEnumTest {

    @Test
    public void testRoleEnumValues() {
        // Verify the number of roles
        assertThat(RoleEnum.values()).hasSize(3);

        // Verify the role names
        assertThat(RoleEnum.ADMIN.name()).isEqualTo("ADMIN");
        assertThat(RoleEnum.STUDENT.name()).isEqualTo("STUDENT");
        assertThat(RoleEnum.INSTRUCTOR.name()).isEqualTo("INSTRUCTOR");

        // Verify the ordinal values
        assertThat(RoleEnum.ADMIN.ordinal()).isEqualTo(0);
        assertThat(RoleEnum.STUDENT.ordinal()).isEqualTo(1);
        assertThat(RoleEnum.INSTRUCTOR.ordinal()).isEqualTo(2);
    }

    @Test
    public void testRoleEnumValueOf() {
        // Verify the valueOf method
        assertThat(RoleEnum.valueOf("ADMIN")).isEqualTo(RoleEnum.ADMIN);
        assertThat(RoleEnum.valueOf("STUDENT")).isEqualTo(RoleEnum.STUDENT);
        assertThat(RoleEnum.valueOf("INSTRUCTOR")).isEqualTo(RoleEnum.INSTRUCTOR);
    }
}
