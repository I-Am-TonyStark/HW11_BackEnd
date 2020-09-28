package com.mamalimomen.controller.menus;

import com.mamalimomen.domains.UserInfo;

public class WriterMenu extends AbstractMenu {

    public WriterMenu(UserInfo writer) {
        super(writer.getFullName() + "'s account", new String[]{
                "Change your password",
                "See and Modify your articles",
                "Write new article"
        }, writer);
    }

    @Override
    public void showMenu() {
        while (true) {
            switch (menuAction()) {
                case 1:
                    MenuActions.changePassword(thisMenuUser,sc);
                    break;
                case 2:
                    MenuActions.seeAndModifyArticles(thisMenuUser,sc);
                    break;
                case 3:
                    MenuActions.writeNewArticle(thisMenuUser,sc);
                    break;
                default:
                    return;
            }
        }
    }
}

