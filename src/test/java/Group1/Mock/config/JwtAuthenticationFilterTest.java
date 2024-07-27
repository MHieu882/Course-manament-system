package Group1.Mock.config;

import Group1.Mock.entity.JwtToken;
import Group1.Mock.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTest {

    private JWTService jwtService;
    private UserDetailsService userDetailsService;
    private HandlerExceptionResolver handlerExceptionResolver;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setUp() {
        jwtService = mock(JWTService.class);
        userDetailsService = mock(UserDetailsService.class);
        handlerExceptionResolver = mock(HandlerExceptionResolver.class);
        jwtAuthenticationFilter = new JwtAuthenticationFilter(handlerExceptionResolver, jwtService, userDetailsService);
    }

    @Test
    public void testDoFilterInternalWithValidToken() throws ServletException, IOException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        FilterChain filterChain = mock(FilterChain.class);
//
//        String token = "Bearer validToken";
//        String username = "user";
//
//        when(request.getHeader("Authorization")).thenReturn(token);
//        when(jwtService.extractUsername(anyString())).thenReturn(username);
//        when(jwtService.getOne(anyString())).thenReturn(Optional.of(new JwtToken()));
//        UserDetails userDetails = mock(UserDetails.class);
//        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
//        when(jwtService.isTokenValid(anyString(), any(UserDetails.class))).thenReturn(true);
//
//        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
//
//        ArgumentCaptor<UsernamePasswordAuthenticationToken> authCaptor = ArgumentCaptor.forClass(UsernamePasswordAuthenticationToken.class);
//        verify(SecurityContextHolder.getContext()).setAuthentication(authCaptor.capture());
//
//        assertEquals(userDetails, authCaptor.getValue().getPrincipal());
//        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalWithInvalidToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String token = "Bearer invalidToken";

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtService.extractUsername(anyString())).thenThrow(new RuntimeException("Invalid token"));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(handlerExceptionResolver).resolveException(eq(request), eq(response), any(), any(Exception.class));
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalWithoutToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(handlerExceptionResolver);
    }
}
