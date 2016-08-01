package icc.referral.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//	private RequestCache requestCache = new HttpSessionRequestCache();
//	
//	@Override
//    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
//        final SavedRequest savedRequest = requestCache.getRequest(request, response);
//
//        if (savedRequest == null) {
//            clearAuthenticationAttributes(request);
//            return;
//        }
//        final String targetUrlParameter = getTargetUrlParameter();
//        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
//            requestCache.removeRequest(request, response);
//            clearAuthenticationAttributes(request);
//            return;
//        }
//
//        clearAuthenticationAttributes(request);
//
//    }
//	
//	public void setRequestCache(RequestCache requestCache) {
//        this.requestCache = requestCache;
//    }
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isAdmin(roles)) {
			url = "/admin";
		} else if (isUser(roles)) {
			url = "/home";
		} else if (isSchool(roles)) {
			url = "/school";
		} else if (isProfessor(roles)) {
			url = "/professor";
		} else if (isStudent(roles)) {
			url = "/student";
		}
//		} else if(isConsultant(roles)){
//			url = "/api/referral";
//		}
		else {
			url = "/accessDenied";
		}

		return url;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	private boolean isUser(List<String> roles) {
		if (roles.contains("ROLE_USER")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	private boolean isSchool(List<String> roles) {
		if (roles.contains("ROLE_SCHOOL")) {
			return true;
		}
		return false;
	}

	private boolean isProfessor(List<String> roles) {
		if (roles.contains("ROLE_PROFESSOR")) {
			return true;
		}
		return false;
	}

	private boolean isStudent(List<String> roles) {
		if (roles.contains("ROLE_STUDENT")) {
			return true;
		}
		return false;
	}
	
	private boolean isConsultant(List<String> roles) {
		if (roles.contains("ROLE_CONSULTANT")) {
			return true;
		}
		return false;
	}

}