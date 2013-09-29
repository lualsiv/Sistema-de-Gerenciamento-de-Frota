package gerenciamentodefrota.infra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Cookies {

	//	TODO - Problema! Funciona uma hora e outra não.
	
	private HttpServletResponse response;
	private HttpServletRequest request;

	public Cookies(HttpServletResponse response, HttpServletRequest request) {
		this.response = response;
		this.request = request;
	}

	public void set(String key, String value) {
		Cookie cookie;
		
		if (exists(key)) {
			cookie = getCookie(key);
			cookie.setValue(value);
		}
		else {
			cookie = new Cookie(key, value);
		}
		
		response.addCookie(cookie);
	}

	public boolean exists(String key) {
		return getCookie(key) != null;
	}
	
	public void delete(String key) {
		if (exists(key)) {
			Cookie cookie = getCookie(key);
			cookie.setMaxAge(0);
			cookie.setValue(null);
			response.addCookie(cookie);
		}
	}

	public String get(String key) {
		for (Cookie c : get()) {
			if (c.getName().equals(key) && c.getValue() != null)
				if (c.getValue().length() > 0)
					return c.getValue();
		}

		return null;
	}

	private Cookie getCookie(String key) {
		for (Cookie c : get()) {
			if (c.getName().equals(key))
				return c;
		}

		return null;
	}

	private List<Cookie> get() {
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null)
			return Arrays.asList(cookies);
		else
			return new ArrayList<Cookie>();
	}

}
