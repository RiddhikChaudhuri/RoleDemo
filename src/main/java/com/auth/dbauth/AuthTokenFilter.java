package com.auth.dbauth;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth.dbauth.core.service.UserService;
import com.auth.dbauth.entity.JWTBlackListTokenEntity;
import com.auth.dbauth.exception.BlackListedException;
import com.auth.dbauth.repository.JwtBlacklistTokenRepository;


/**
 * The Class AuthTokenFilter.
 */
public class AuthTokenFilter extends OncePerRequestFilter {

	/** The jwt utils. */
	@Autowired
	private JwtUtils jwtUtils;

	/** The user details service. */
	@Autowired
	private UserService userDetailsService;

	/** The jwt blacklist repository. */
	@Autowired
	public JwtBlacklistTokenRepository jwtBlacklistRepository;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	/**
	 * Do filter internal.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param filterChain
	 *            the filter chain
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			final Optional<JWTBlackListTokenEntity> blacklist = this.jwtBlacklistRepository.findByToken(jwt);
			
			logger.info(jwt);
			if (!blacklist.isPresent()) {
              if (jwt != null && jwtUtils.validateJwtToken(jwt)
                  && jwtUtils.getEnabledFromJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authentication
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
              } else {
                logger.info("JWT String is Blank");
              }

			} else {
				throw new BlackListedException("BlackListed Token Being used");
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {%s}", e);
		}
		
		logger.info("Either JWT String is Blank or Passed Successfully");
		filterChain.doFilter(request, response);
	}

	/**
	 * Parses the jwt.
	 *
	 * @param request
	 *            the request
	 * @return the string
	 */
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
        logger.info("JWT String is Blank");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}

		return "";
	}
}
