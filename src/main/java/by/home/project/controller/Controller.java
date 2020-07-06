package by.home.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.home.project.controller.command.Command;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -8247025761160944272L;

	private final CommandProvider commandProvider = new CommandProvider();

	private static final String COMMAND_PARAMETER = "command";
	private static final String ATTRIBUTE_NAME_LAST_REQUEST = "lastUserRequest";

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(COMMAND_PARAMETER);
		Command executionCommand = commandProvider.getCommand(commandName);
		executionCommand.execute(request, response);
		request.getSession(true).setAttribute(ATTRIBUTE_NAME_LAST_REQUEST,
				request.getRequestURI() + "?" + request.getQueryString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName = request.getParameter(COMMAND_PARAMETER);
		Command executionCommand = commandProvider.getCommand(commandName);
		executionCommand.execute(request, response);
	}
}
