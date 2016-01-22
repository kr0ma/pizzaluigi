package be.vdab.servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/pizzas.htm")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private static final String PIZZAS_REQUESTS = "pizzasRequests";
	@Resource(name = PizzaDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		pizzaDAO.setDataSource(dataSource);
	}
	
	private final transient PizzaDAO pizzaDAO = new PizzaDAO();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		Map<Long,Pizza> pizzas = new LinkedHashMap<>(); //keys zijn pizzanummers
		pizzas.put(12L, new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true));
		pizzas.put(14L, new Pizza(14, "Margherita", BigDecimal.valueOf(5), false));
		pizzas.put(17L,new Pizza(17, "Calzone", BigDecimal.valueOf(4), false));
		pizzas.put(23L, new Pizza("Fungi & Olive" , BigDecimal.valueOf(5), false));		
		request.setAttribute("pizzas",pizzas);
		*/
		((AtomicInteger) this.getServletContext().getAttribute(PIZZAS_REQUESTS)).incrementAndGet();
		List<Pizza> pizzas = pizzaDAO.findAll();
		String pizzaFotosPad = this.getServletContext().getRealPath("/pizzafotos");
		Set<Long> pizzaIdsMetFoto = new HashSet<>();
		for (Pizza pizza : pizzas) {
			File file = new File(String.format("%s/%d.jpg", pizzaFotosPad, pizza.getId()));
			if (file.exists()) { // is er foto voor deze pizza ?
				pizzaIdsMetFoto.add(pizza.getId());
			}
		}
		
		request.setAttribute("pizzas", pizzaDAO.findAll());
		request.setAttribute("pizzaIdsMetFoto", pizzaIdsMetFoto);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute(PIZZAS_REQUESTS,new AtomicInteger());
	}	
	
	
	
}
