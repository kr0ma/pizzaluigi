package be.vdab.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeadersServlet
 */
@WebServlet("/headers.htm")
public class HeadersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/headers.jsp";
	private final Map<String, String> browsers = new HashMap<>();
	
	
	public HeadersServlet(){
		browsers.put("firefox", "Firefox");
		browsers.put("chrome", "Chrome");
		browsers.put("msie", "Internet Explorer");
		browsers.put("trident", "Internet Explorer");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAgent = request.getHeader("user-agent").toLowerCase();
		String browser = "onbekend";
		for (Map.Entry<String, String> entry: browsers.entrySet()){
			if (userAgent.contains(entry.getKey())){
				browser = entry.getValue();
				break;
			}
		}
		
		Map <String, String> headers = new LinkedHashMap<>();
		
		for (Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements();){
			String headerName = headerNames.nextElement();
			headers.put(headerName, request.getHeader(headerName));
		}
	
		request.setAttribute("browser", browser);
		request.setAttribute("headers", headers);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
