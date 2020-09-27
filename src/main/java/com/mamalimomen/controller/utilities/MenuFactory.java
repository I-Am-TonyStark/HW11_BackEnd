package com.mamalimomen.controller.utilities;

import com.mamalimomen.controller.menus.AdminMenu;
import com.mamalimomen.controller.menus.MainMenu;
import com.mamalimomen.base.controller.menus.Menu;
import com.mamalimomen.controller.menus.WriterMenu;
import com.mamalimomen.domains.User;

public class MenuFactory {
    private MenuFactory() {
    }

    public static Menu getMenu(User user) {
        if (user == null)
            return new MainMenu();
        else if (user.getRole().getTitle().equals("admin"))
            return new AdminMenu(user);
        else
            return new WriterMenu(user);
    }
}
