package com.epam.shop.controller.command;

public enum Message {
    ERROR_LOGIN("неверный логин или пароль"),
    INVALID_DATA("невалидные данные"),
    ADD_TO_DB("товар добавлен в БД"),
    UPDATED_PRODUCT("товар добавлен в БД"),
    UPDATED_PROFILE("профиль обновлен"),
    PROFILE_ADD("аккаунт успешно создан"),
    ERROR_BALANCE("недостаточно средств на балансе"),
    ORDER_PAY("заказ оплачен"),
    BLOCK("вы заблокированы администратором"),
    ORDER_REFUSE("заказ отменен!"),
    REMOVE_FROM_DB("продукт удален из БД!"),
    ERROR_UNIQUE("логин занят, придумайте другой");


    private String message;

    Message(String message) {
        this.message = message;
    }


}
