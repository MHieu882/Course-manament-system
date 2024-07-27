package Group1.Mock.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
//
    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }
//
@Test
    void handleDataIntegrityViolationException() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Data integrity violation occurred");
        ProblemDetail errorDetail = globalExceptionHandler.handleSecurityException(exception);

        assertNotNull(errorDetail);
        assertEquals(400, errorDetail.getStatus());
//        assertEquals("Data integrity violation occurred", errorDetail.getTitle());
//        assertNull(errorDetail.getProperty("description"));
}
    @Test
    void handleBadCredentialsException() {
        BadCredentialsException exception = new BadCredentialsException("Bad credentials");
        ProblemDetail errorDetail = globalExceptionHandler.handleSecurityException(exception);

        assertNotNull(errorDetail);
        assertEquals(401, errorDetail.getStatus());
//        assertEquals("Bad credentials", errorDetail.getTitle());
//        assertEquals("The username or password is incorrect", errorDetail.getProperty("description"));
    }

    @Test
    void handleAccessDeniedException() {
        AccessDeniedException exception = new AccessDeniedException("Access is denied");
        ProblemDetail errorDetail = globalExceptionHandler.handleSecurityException(exception);

        assertNotNull(errorDetail);
        assertEquals(403, errorDetail.getStatus());
//        assertEquals("Access is denied", errorDetail.getTitle());
//        assertEquals("You are not authorized to access this resource", errorDetail.getProperty("description"));
    }
    @Test
    void handleHttpMessageNotReadableException() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Request body is missing or non-readable");
        ProblemDetail errorDetail = globalExceptionHandler.handleSecurityException(exception);

        assertNotNull(errorDetail);
        assertEquals(400, errorDetail.getStatus());
//        assertEquals("Request body is missing or non-readable", errorDetail.getTitle());
//        assertEquals("Request body is missing or non-readable", errorDetail.getProperty("description"));
    }
//    @Test
//    public void testHandleDataIntegrityViolationException() {
//        DataIntegrityViolationException exception = new DataIntegrityViolationException("Data integrity violation");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(400), problemDetail.getStatus());
//        assertEquals("Data integrity violation", problemDetail.getDetail());
//    }
//
//    @Test
//    public void testHandleBadCredentialsException() {
//        BadCredentialsException exception = new BadCredentialsException("Bad credentials");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(401), problemDetail.getStatus());
//        assertEquals("Bad credentials", problemDetail.getDetail());
//        assertEquals("The username or password is incorrect", problemDetail.getProperties().get("description"));
//    }
//
//    @Test
//    public void testHandleAccountStatusException() {
//        AccountStatusException exception = new AccountStatusException("Account locked") {};
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(403), problemDetail.getStatus());
//        assertEquals("Account locked", problemDetail.getDetail());
//        assertEquals("The account is locked", problemDetail.getProperties().get("description"));
//    }
//
//    @Test
//    public void testHandleAccessDeniedException() {
//        AccessDeniedException exception = new AccessDeniedException("Access denied");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(403), problemDetail.getStatus());
//        assertEquals("Access denied", problemDetail.getDetail());
//        assertEquals("You are not authorized to access this resource", problemDetail.getProperties().get("description"));
//    }
//
//    @Test
//    public void testHandleSignatureException() {
//        SignatureException exception = new SignatureException("Invalid JWT signature");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(403), problemDetail.getStatus());
//        assertEquals("Invalid JWT signature", problemDetail.getDetail());
//        assertEquals("The JWT signature is invalid", problemDetail.getProperties().get("description"));
//    }
//
//    @Test
//    public void testHandleExpiredJwtException() {
//        ExpiredJwtException exception = mock(ExpiredJwtException.class);
//        Mockito.when(exception.getMessage()).thenReturn("JWT expired");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(403), problemDetail.getStatus());
//        assertEquals("JWT expired", problemDetail.getDetail());
//        assertEquals("The JWT token has expired", problemDetail.getProperties().get("description"));
//    }
//
//    @Test
//    public void testHandleMethodArgumentNotValidException() {
//        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
//        Mockito.when(exception.getMessage()).thenReturn("Validation failed");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(400), problemDetail.getStatus());
//        assertEquals("Validation failed", problemDetail.getDetail());
//    }
//
//    @Test
//    public void testHandleHttpMessageNotReadableException() {
//        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Request body is missing");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(400), problemDetail.getStatus());
//        assertEquals("Request body is missing or non-readable", problemDetail.getDetail());
//    }
//
//    @Test
//    public void testHandleUsernameExistException() {
//        UsernameExistException exception = new UsernameExistException("Username exists");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(400), problemDetail.getStatus());
//        assertEquals("Username exists", problemDetail.getDetail());
//    }
//
//    @Test
//    public void testHandleEmailExistException() {
//        EmailExistException exception = new EmailExistException("Email exists");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(400), problemDetail.getStatus());
//        assertEquals("Email exists", problemDetail.getDetail());
//    }
//
//    @Test
//    public void testHandleSearchNotFoundException() {
//        SearchNotFoundException exception = new SearchNotFoundException("Search not found");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(404), problemDetail.getStatus());
//        assertEquals("Search not found", problemDetail.getDetail());
//    }
//
//    @Test
//    public void testHandleResourceNotFoundException() {
//        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(404), problemDetail.getStatus());
//        assertEquals("Resource not found", problemDetail.getDetail());
//    }
//
//    @Test
//    public void testHandleUnknownException() {
//        Exception exception = new Exception("Unknown error");
//        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(exception);
//        assertEquals(HttpStatusCode.valueOf(500), problemDetail.getStatus());
//        assertEquals("Unknown error", problemDetail.getDetail());
//        assertEquals("Unknown internal server error.", problemDetail.getProperties().get("description"));
//    }
}
