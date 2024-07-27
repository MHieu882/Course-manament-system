package Group1.Mock.enums;

import Group1.Mock.enums.ApproveType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApproveTypeEnumTest {

    @Test
    public void testEnumValues() {
        // Test if all expected enum values are present
        ApproveType[] values = ApproveType.values();

        assertThat(values).hasSize(2);
        assertThat(values).contains(ApproveType.APPROVED);
        assertThat(values).contains(ApproveType.REJECTED);
    }

    @Test
    public void testEnumFromString() {
        // Test conversion from string to enum
        ApproveType approved = ApproveType.valueOf("APPROVED");
        ApproveType rejected = ApproveType.valueOf("REJECTED");

        assertThat(approved).isEqualTo(ApproveType.APPROVED);
        assertThat(rejected).isEqualTo(ApproveType.REJECTED);
    }

    @Test
    public void testEnumToString() {
        // Test conversion from enum to string
        String approvedString = ApproveType.APPROVED.name();
        String rejectedString = ApproveType.REJECTED.name();

        assertThat(approvedString).isEqualTo("APPROVED");
        assertThat(rejectedString).isEqualTo("REJECTED");
    }
}
