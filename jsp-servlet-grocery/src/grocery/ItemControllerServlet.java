package grocery;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ItemControllerServlet
 */
@WebServlet("/ItemControllerServlet")
public class ItemControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ItemDbUtil itemDbUtil;

	@Resource(name = "jdbc/grocery_store")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			itemDbUtil = new ItemDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String command = request.getParameter("command");

			if (command == null) {
				command = "list";
			}

			switch (command) {
			case "list":
				listItems(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateItem(request, response);
				break;
			case "delete":
				deleteItem(request, response);
				break;
			case "search":
				searchItems(request, response);
				break;
			default:
				listItems(request, response);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void searchItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String searchName = request.getParameter("searchName");
		List<Item> items = itemDbUtil.searchItems(searchName);
		request.setAttribute("items", items);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/itemList.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String command = request.getParameter("command");

			switch (command) {
			case "add":
				addItem(request, response);
				break;

			default:
				listItems(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("itemId"));
		itemDbUtil.deleteItem(id);

		listItems(request, response);
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("itemId"));
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		float price = Float.parseFloat(request.getParameter("price"));

		Item item = new Item(id, name, quantity, price);
		itemDbUtil.updateItem(item);

		listItems(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("itemId"));
		Item item = itemDbUtil.getItem(id);
		request.setAttribute("item", item);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editItemForm.jsp");
		dispatcher.forward(request, response);
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		float price = Float.parseFloat(request.getParameter("price"));

		Item item = new Item(name, quantity, price);
		itemDbUtil.addItem(item);
		
		response.sendRedirect(request.getContextPath() + "/ItemControllerServlet?command=list");
	}

	private void listItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Item> items = itemDbUtil.getItems();
		request.setAttribute("items", items);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/itemList.jsp");
		dispatcher.forward(request, response);
	}
}
