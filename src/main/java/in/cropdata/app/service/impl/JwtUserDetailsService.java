package in.cropdata.app.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.cropdata.app.dto.NavData;
import in.cropdata.app.dto.interfaces.Menu;
import in.cropdata.app.exception.InactiveUserException;
import in.cropdata.app.model.AppRestriction;
import in.cropdata.app.model.AppUser;
import in.cropdata.app.model.CustomUserDetails;
import in.cropdata.app.repository.AppUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	AppService appService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	Optional<User> foundAcluser = aclUserRepository.findByEmail(username.trim());

		in.cropdata.app.dto.interfaces.User foundAcluser = appUserRepository.getUserByEmailId(username.trim());
		AppUser aclUser = null;
		if (foundAcluser != null) {
			aclUser = new AppUser();
			aclUser.setId(foundAcluser.getId());
			aclUser.setRoleId(foundAcluser.getRoleId());
			aclUser.setName(foundAcluser.getName());
			aclUser.setRole(foundAcluser.getRole());
			aclUser.setEmail(foundAcluser.getEmail());
			aclUser.setPassword(foundAcluser.getPassword());
			aclUser.setStatus(foundAcluser.getStatus());
			aclUser.setCreatedAt(foundAcluser.getCreatedAt());
			aclUser.setUpdatedAt(foundAcluser.getUpdatedAt());

			if (!aclUser.getStatus().equals("Active")) {
				throw new InactiveUserException("User '" + username + "' is not active.");
			}

			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//	    System.err.println("Role: "+aclUser.getRole());
			grantedAuthorities.add(new SimpleGrantedAuthority(aclUser.getRole()));

			User user = new User(aclUser.getEmail(), aclUser.getPassword(), grantedAuthorities);

			List<Menu> menuList = appUserRepository.getMenusByRole(foundAcluser.getRoleId());

			Map<String, NavData> navMap = new HashMap<String, NavData>();
			for (Menu menu : menuList) {
				String group = menu.getResourceGroupName();
				if (navMap.get(group) == null) {
					NavData navData = new NavData();
					navData.setName(group);
					navData.setIcon("fa fa-indent");
					if (menu.getResourceURL() != null && menu.getResourceURL().contains("/")) {
						String[] urls = menu.getResourceURL().split(File.separator);
						if (urls.length > 1) {
							navData.setUrl("/" + urls[1]);
						} else {
							navData.setUrl("#");
						}
					} // if menu url not null
					navMap.put(group, navData);
				} // if not exist in navMap

				NavData navData = new NavData();
				navData.setName(menu.getResourceName());
				navData.setUrl(menu.getResourceURL());
				navData.setIcon("fa fa-indent");
				if (navMap.get(group) != null) {
					navMap.get(group).getChildren().add(navData);
				}
			} // for

//	    System.err.println(navMap.entrySet());
			return new CustomUserDetails(user, aclUser, navMap);

		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}// loadUserByUsername

	public List<AppRestriction> getRestrictionsForRoleId(int roleId) {
		return appService.findAllByRoleId(roleId);
	}
}