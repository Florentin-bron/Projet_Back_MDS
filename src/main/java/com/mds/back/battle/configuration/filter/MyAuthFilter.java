package com.mds.back.battle.configuration.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mds.back.battle.configuration.settings.SecuritySettings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

//@Component
public class MyAuthFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(MyAuthFilter.class);
	private static String[] UNPROTECTED = { "/api/auth", "/v2/api-docs", "/spring-security-rest", "/webjars",
			"/configuration", "/swagger-ui", "/swagger-resources", "/images", "/favicon" };

	@Autowired
	private SecuritySettings config;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String auth = request.getHeader("Authorization");
		if (auth != null && !auth.isEmpty()) {
			String tokenCrypt = auth.substring("Bearer".length()).trim();
			byte[] secrets64 = Base64.getEncoder().encode(config.getSecret().getBytes());
			Jwt<?, Claims> jwt = Jwts.parser().setSigningKey(secrets64).parseClaimsJws(tokenCrypt);
			Claims claims = jwt.getBody();

			String result = claims.get("role", String.class);
			logger.info(result);
			request.setAttribute("role", result);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + result));
			Authentication auth2 = new UsernamePasswordAuthenticationToken(null, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(auth2);
			filterChain.doFilter(request, response);
			return;
		}
		response.setStatus(401);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getServletPath();
		for (String exclude : UNPROTECTED)
			if (path.startsWith(exclude))
				return true;
		return false;
	}
}
