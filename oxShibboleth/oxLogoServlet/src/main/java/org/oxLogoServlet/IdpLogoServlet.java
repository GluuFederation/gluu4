package org.oxLogoServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/servlet/logo")
public class IdpLogoServlet extends HttpServlet {

	private Logger logger;

	private static final long serialVersionUID = 5445488800130871634L;
	private static final String DEFAULT_LOGO_FILENAME = "logo.png";
	public static final String BASE_IDP_LOGO_PATH = "/opt/gluu/jetty/idp/custom/static/logo/";

	private static final Logger log = LoggerFactory.getLogger(IdpLogoServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		logger = LoggerFactory.getLogger(IdpLogoServlet.class);
		response.setContentType("image/jpg");
		response.setDateHeader("Expires", new Date().getTime() + 1000L * 1800);
		boolean hasSucceed = readCustomLogo(response);
		if (!hasSucceed) {
			readDefaultLogo(response);
		}
	}

	private boolean readDefaultLogo(HttpServletResponse response) {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream(DEFAULT_LOGO_FILENAME);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			IOUtils.copy(in, out);
			return true;
		} catch (IOException e) {
			logger.debug("Error loading default logo: " + e);
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean readCustomLogo(HttpServletResponse response) {

		File directory = new File(BASE_IDP_LOGO_PATH);
		if (!directory.exists()) {
			directory.mkdir();
		}
		File logoPath = new File(BASE_IDP_LOGO_PATH + DEFAULT_LOGO_FILENAME);
		if (!logoPath.exists()) {
			return false;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(logoPath);
			out = response.getOutputStream();
			IOUtils.copy(in, out);
			return true;
		} catch (Exception e) {
			log.debug("Error loading custom logo: " + e);
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected static File getResourceFile(String resName) {
		ClassLoader classLoader = IdpLogoServlet.class.getClassLoader();
		return new File(classLoader.getResource(resName).getFile());
	}
}
