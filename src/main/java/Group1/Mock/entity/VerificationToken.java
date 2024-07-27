package Group1.Mock.entity;

import Group1.Mock.enums.TokenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verify_token")
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {
    private static final int EXPIRATION = 60 * 60 * 1000 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }

    public VerificationToken(String token, User user, TokenType type) {
        this.token = token;
        this.user = user;
        this.type = type;
        this.expiryDate = calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Long timestamp = System.currentTimeMillis() + EXPIRATION;
        return new Date(timestamp);
    }
    public boolean isExpired() {
        return expiryDate.before(new Date());
    }
}
