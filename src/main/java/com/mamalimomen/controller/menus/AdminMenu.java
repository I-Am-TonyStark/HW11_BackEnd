package com.mamalimomen.controller.menus;

import com.mamalimomen.domains.User;

public class AdminMenu extends AbstractMenu {

    public AdminMenu(User admin) {
        super(admin.getUserName() + "'s account", new String[]{
                "Change your password",
                "See and Modify your articles",
                "Write new article",
                "Change role of users",
                "Publish or unPublish an article",
                "Delete an article",
                "Create a category",
                "Create a tag"
        }, admin);
    }

    @Override
    public void showMenu() {
        while (true) {
            switch (menuAction()) {
                case 1:
                    MenuActions.changePassword(thisMenuUser, sc);
                    break;
                case 2:
                    MenuActions.seeAndModifyArticles(thisMenuUser, sc);
                    break;
                case 3:
                    MenuActions.writeNewArticle(thisMenuUser, sc);
                    break;
                case 4:
                    MenuActions.changeRoleOfUsers(thisMenuUser,sc);
                    break;
                case 5:
                    MenuActions.seeAllWrittenArticles(thisMenuUser, sc);
                    break;
                case 6:
                    MenuActions.deleteArticles(thisMenuUser,sc);
                    break;
                case 7:
                    MenuActions.createNewCategory(thisMenuUser,sc);
                    break;
                case 8:
                    MenuActions.createNewTag(thisMenuUser,sc);
                    break;
                default:
                    return;
            }
        }
    }
}