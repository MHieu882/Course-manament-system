package Group1.Mock.service;

import Group1.Mock.entity.JwtToken;
import Group1.Mock.reponsitory.JwtTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JWTServiceTest {

    @Mock
    private JwtTokenRepository repository;

    @InjectMocks
    private JWTService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtService, "secretKey", "22a7b1797f7d559834f35c8296775ab7cd70d218684a7f35a342eb21bc27b7dbbd10e7e8d25d4586a4888d9dd74678e4abb845dee7d965c79a49a551b8aabd0aa1719c9e736aa8c5662a94ce1aacd28bd57ee9a58aab3669f402932e0ef14926e89d9b2b1197f4f4f7966022f492fbd44a1861ca80a1034c8691b85a8e11f897597c599f692d3f810cff6d6c0b7e2ee38b113405bd81175f45fb54c7a77e2fde39390eea0e443a0609d64e25780259f531931955ae660ef167116259abf664006dae9ac873200cd3294489f06238bd33b43a20f3c1a3131d8e2176965488e3cf7f64bf484e253384ce95d1e647413f3851060fc97cd5cdb4d6baa90451158887a2786df4b1abb2fcb9b47a80bbe59e26e452e0f2c1a50a26d0e12ba66e863c2e43385c1936e78fd5b5905c4f2f280180e23625b7c75210858382828e75b2afaf");
        ReflectionTestUtils.setField(jwtService, "expiration", Long.parseLong("3600000"));
    }

    @Test
    void generateToken_withExtraClaims() {
        // Mock data
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("claim1", "value1");

        // Call the method
        String token = jwtService.generateToken(extraClaims, userDetails);

        // Verify results
        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiJ9")); // Check for JWT prefix
    }

    @Test
    void generateToken_withoutExtraClaims() {
        // Mock data
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        // Call the method
        String token = jwtService.generateToken(userDetails);

        // Verify results
        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiJ9")); // Check for JWT prefix
    }

    @Test
    void extractUsername() {
        // Mock data
        String token = generateMockToken(null);

        // Call the method
        String username = jwtService.extractUsername(token);

        // Verify results
        assertEquals("user", username);
    }

    @Test
    void extractExpiration() {
        // Mock data
        String token = generateMockToken(null);
        Date expiration = Jwts.parserBuilder().setSigningKey(jwtService.getSignKey()).build().parseClaimsJws(token).getBody().getExpiration();

        // Call the method
        Date expirationDate = jwtService.extractExpiration(token);

        // Verify results
        assertEquals(expiration, expirationDate);
    }

    @Test
    void isTokenValid_validToken() {
        // Mock data
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");
        String token = generateMockToken(null);

        // Call the method
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        // Verify results
        assertTrue(isValid);
    }

    @Test
    void isTokenValid_invalidToken() {
        // Mock data
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");
        String token = generateMockToken(-1L);
        // Simulate token expiration
        assertThrows(ExpiredJwtException.class, () -> {
            jwtService.isTokenValid(token, userDetails);
        });
    }

    @Test
    void getAll() {
        // Mock data
        JwtToken jwtToken = new JwtToken();
        List<JwtToken> tokens = Arrays.asList(jwtToken);
        when(repository.findAll()).thenReturn(tokens);

        // Call the method
        List<JwtToken> result = jwtService.getAll();

        // Verify results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jwtToken, result.get(0));
    }

    @Test
    void getOne() {
        // Mock data
        JwtToken jwtToken = new JwtToken();
        String token = "mockToken";
        when(repository.findByToken(token)).thenReturn(Optional.of(jwtToken));

        // Call the method
        Optional<JwtToken> result = jwtService.getOne(token);

        // Verify results
        assertTrue(result.isPresent());
        assertEquals(jwtToken, result.get());
    }

    @Test
    void saveToken() {
        // Mock data
        JwtToken jwtToken = new JwtToken();

        // Call the method
        jwtService.saveToken(jwtToken);

        // Verify results
        verify(repository).save(jwtToken);
    }

    @Test
    void deleteToken() {
        // Mock data
        String token = "mockToken";

        // Call the method
        jwtService.deleteToken(token);

        // Verify results
        verify(repository).deleteByToken(token);
    }

    private String generateMockToken(Long timestamp) {
        Long expiration = System.currentTimeMillis() + 10000;
        if (timestamp != null) expiration = timestamp;

        return Jwts.builder()
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(expiration))
                .signWith(jwtService.getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
