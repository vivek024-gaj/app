package in.cropdata.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.cropdata.app.constant.APIConstants;
import in.cropdata.app.dto.NavData;
import in.cropdata.app.exception.InactiveUserException;
import in.cropdata.app.exception.InvalidUserCredentialsException;
import in.cropdata.app.model.AppSession;
import in.cropdata.app.model.AppUser;
import in.cropdata.app.model.CustomUserDetails;
import in.cropdata.app.model.JwtRequest;
import in.cropdata.app.service.impl.AppService;
import in.cropdata.app.service.impl.JwtUserDetailsService;
import in.cropdata.app.utils.JwtTokenUtil;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	AppService appService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		Map<String, Object> dataMap = new HashMap<String, Object>();
//		if (appService.checkUserSession(userDetails.getUsername(), token)) {
//			dataMap.put("message", "Hi " + userDetails.getUsername() + "has already loged in");
//		} else {
			AppUser appUser = null;
			Map<String, NavData> namMap = null;
			if (userDetails instanceof CustomUserDetails) {
				CustomUserDetails user = (CustomUserDetails) userDetails;
				appUser = user.getAclUser();
				appUser.setToken(token);
				AppSession s = new AppSession();
				s.setUserId(appUser.getId());
				s.setJwtToken(appUser.getToken());
				s.setStatus(true);
				this.appService.storeSession(s);
				namMap = user.getNavMap();
			}
			dataMap.put("user", appUser);
			dataMap.put("nav", namMap);
//		}
		return ResponseEntity.ok(dataMap);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new InactiveUserException(APIConstants.RESPONSE_INACTIVE_USER);
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException(APIConstants.RESPONSE_INVALID_CREDENTIALS);
		}
	}
}