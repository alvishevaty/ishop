package by.home.project.controller;

import java.util.HashMap;
import java.util.Map;

import by.home.project.controller.command.Command;
import by.home.project.controller.command.impl.ChangeLocale;
import by.home.project.controller.command.impl.DelFromBasket;
import by.home.project.controller.command.impl.GetGoodsList;
import by.home.project.controller.command.impl.GoToBasketPage;
import by.home.project.controller.command.impl.GoToMainPage;
import by.home.project.controller.command.impl.GoToCatalogPage;
import by.home.project.controller.command.impl.GoToRegistrationPage;
import by.home.project.controller.command.impl.GoToSignInPage;
import by.home.project.controller.command.impl.GoToUserInfoPage;
import by.home.project.controller.command.impl.PutIntoBasket;
import by.home.project.controller.command.impl.Registration;
import by.home.project.controller.command.impl.ShowGoodsPage;
import by.home.project.controller.command.impl.SignIn;
import by.home.project.controller.command.impl.SignOut;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	public CommandProvider() {
		repository.put(CommandName.SIGNIN, new SignIn());
		repository.put(CommandName.SIGNOUT, new SignOut());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.CHANGELOCALE, new ChangeLocale());
		repository.put(CommandName.GOTOSIGNINPAGE, new GoToSignInPage());
		repository.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
		repository.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPage());
		repository.put(CommandName.GOTOCATALOGPAGE, new GoToCatalogPage());
		repository.put(CommandName.GETGOODSLIST, new GetGoodsList());
		repository.put(CommandName.PUTINTOBASKET, new PutIntoBasket());
		repository.put(CommandName.GOTOBASKETPAGE, new GoToBasketPage());
		repository.put(CommandName.DELFROMBASKET, new DelFromBasket());
		repository.put(CommandName.GOTOUSERINFOPAGE, new GoToUserInfoPage());
		repository.put(CommandName.SHOWGOODSPAGE, new ShowGoodsPage());
	}

	public Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.GOTOERRORPAGE);
		}
		return command;
	}
}
