package com.kwj.iceberg.springrest.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.rest.RESTCatalogAdapter;
import org.apache.iceberg.rest.RESTCatalogServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

public class IcebergRESTCatalogServletRegistration
	extends ServletRegistrationBean<IcebergRESTCatalogServletRegistration.IcebergRESTCatalogServlet> {

	public IcebergRESTCatalogServletRegistration(Catalog catalog, String id) {
		super(new IcebergRESTCatalogServlet(catalog, id), "/v1/" + id + "/*");
	}

	public static class IcebergRESTCatalogServlet extends RESTCatalogServlet {

		private final String id;

		public IcebergRESTCatalogServlet(Catalog catalog, String id) {
			super(new RESTCatalogAdapter(catalog));
			this.id = id;
		}

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpServletRequest wrapped = new HttpServletRequestWrapper(req) {
				@Override
				public String getRequestURI() {
					return removeIdSegment(super.getRequestURI());
				}
			};
			super.service(wrapped, resp);
		}

		private String removeIdSegment(String uri) {
			return uri.replace("/" + id, "");
		}

	}

}
