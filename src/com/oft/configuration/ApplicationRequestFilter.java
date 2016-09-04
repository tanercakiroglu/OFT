package com.oft.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class ApplicationRequestFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest servletRequest;

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		String ipAddress = request.getHeaderValue("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = servletRequest.getRemoteAddr();
		}

		if (request != null && request.getRequestUri() != null
				&& (request.getRequestUri().getPath() != null)
				&& !(request.getRequestUri().getPath().contains("login"))) {

			int rs = ConnectionManager.selectUser(request
					.getHeaderValue("authorization"));

			if (rs != 1)
				throw new WebApplicationException(401);
		}
		return request;
	}

}
