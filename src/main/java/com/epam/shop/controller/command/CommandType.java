package com.epam.shop.controller.command;

import com.epam.shop.controller.command.transition.AboutCommand;
import com.epam.shop.controller.command.transition.GoToAddProductCommand;
import com.epam.shop.controller.command.transition.GoToBasketCommand;
import com.epam.shop.controller.command.transition.GoToEditProductCommand;
import com.epam.shop.controller.command.transition.GoToEditProfileCommand;
import com.epam.shop.controller.command.transition.GoToLogInCommand;
import com.epam.shop.controller.command.transition.GoToOrderHistoryCommand;
import com.epam.shop.controller.command.transition.GoToProfileCommand;
import com.epam.shop.controller.command.transition.GoToRegistrationCommand;
import com.epam.shop.controller.command.transition.GoToStatisticCommand;
import com.epam.shop.controller.command.transition.ShowProductsCommand;
import com.epam.shop.controller.command.transition.ShowCustomersCommand;
import com.epam.shop.dao.Role;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum CommandType {
    DEFAULT(DefaultCommand.getInstance(), Role.UNKNOWN, Role.CUSTOMER, Role.ADMIN),
    ABOUT(AboutCommand.getInstance(), Role.UNKNOWN, Role.CUSTOMER, Role.ADMIN),
    GO_TO_LOGIN(GoToLogInCommand.getInstance(), Role.UNKNOWN, Role.CUSTOMER, Role.ADMIN),
    GO_TO_BASKET(GoToBasketCommand.getInstance(), Role.CUSTOMER),
    GO_TO_PROFILE(GoToProfileCommand.getInstance(),Role.CUSTOMER, Role.ADMIN),
    GO_TO_REGIST(GoToRegistrationCommand.getInstance(), Role.UNKNOWN,Role.CUSTOMER),
    GO_TO_ADD_PRODUCT(GoToAddProductCommand.getInstance(),Role.ADMIN ),
    GO_TO_EDIT_PRODUCT(GoToEditProductCommand.getInstance(),Role.ADMIN),
    GO_TO_STAT(GoToStatisticCommand.getInstance(),Role.ADMIN),
    GO_TO_EDIT_PROF(GoToEditProfileCommand.getInstance(),Role.CUSTOMER),
    LOGIN(LoginCommand.getInstance(), Role.UNKNOWN, Role.CUSTOMER, Role.ADMIN),
    LOGOUT(LogOutCommand.getInstance(), Role.CUSTOMER, Role.ADMIN),
    REGISTRATION(RegistrationCommand.getInstance(), Role.UNKNOWN, Role.CUSTOMER),
    ADD_CUSTOMER(AddCustomerCommand.getInstance(), Role.CUSTOMER),
    SHOW_PRODUCTS(ShowProductsCommand.getInstance(), Role.UNKNOWN, Role.CUSTOMER, Role.ADMIN),
    BLOCK(BlockCustomerCommand.getInstance(),Role.ADMIN),
    EDIT_PRODUCT(EditProductCommand.getInstance(),Role.ADMIN),
    DELETE_PRODUCT(DeleteProductCommand.getInstance(),Role.ADMIN),
    ADD_TO_ORDER(AddProductToOrderCommand.getInstance(),Role.CUSTOMER),
    REMOVE_FROM_ORDER(RemoveProductFromOrderCommand.getInstance(),Role.CUSTOMER),
    PAY_ORDER(PayOrderCommand.getInstance(),Role.CUSTOMER),
    REFUSE_ORDER(RefuseOrderCommand.getInstance(),Role.CUSTOMER),
    ORDER_HISTORY(GoToOrderHistoryCommand.getInstance(),Role.CUSTOMER,Role.ADMIN),
    ABOUT_ORDER(AboutOrderCommand.getInstance(),Role.CUSTOMER,Role.ADMIN),
    ABOUT_PRODUCT(AboutProductCommand.getInstance(),Role.CUSTOMER, Role.ADMIN, Role.UNKNOWN ),
    ADD_NEW_PRODUCT(AddNewProductCommand.getInstance(),Role.ADMIN),
    EDIT_PROFILE(EditProfileCommand.getInstance(),Role.CUSTOMER),
    //todo delete_profile for customer
    SHOW_CUSTOMERS(ShowCustomersCommand.getInstance(),Role.ADMIN);

    private final Command command;
    private final List<Role> accessType;

    CommandType(Command command, Role ... accessLevel) {
        this.command = command;
        this.accessType = Arrays.asList(accessLevel);
    }

    public static Command getCommand(String name) {
        return Arrays.stream(CommandType.values())
                .filter(command -> command.name().equalsIgnoreCase(name))
                .map(command->command.command)
                .findFirst()
                .orElse(DefaultCommand.getInstance());
    }

    public static boolean isEqualAccess(Role role, String commandName){
        final String paramName = Optional.ofNullable(commandName)
                .orElse(CommandType.DEFAULT.name());
        List<Role> roles = Arrays.stream(CommandType.values())
                .filter(command -> command.name().equalsIgnoreCase(paramName))
                .map(command -> command.accessType)
                .findFirst()
                .get();
        return  roles.stream().anyMatch(r->r.equals(role));
    }
}
