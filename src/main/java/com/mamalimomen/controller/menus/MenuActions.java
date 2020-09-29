package com.mamalimomen.controller.menus;

import com.mamalimomen.base.controller.utilities.PersistenceUnit;
import com.mamalimomen.base.controller.utilities.PersistenceUnitManager;
import com.mamalimomen.controller.utilities.MenuFactory;
import com.mamalimomen.base.controller.utilities.InValidDataException;
import com.mamalimomen.base.controller.utilities.SingletonScanner;
import com.mamalimomen.controller.utilities.SingletonWriterRole;
import com.mamalimomen.domains.*;
import com.mamalimomen.repositories.impl.*;
import com.mamalimomen.services.*;
import com.mamalimomen.services.impl.*;

import java.sql.Date;
import java.util.*;

public final class MenuActions {

    private static final MenuActions mas = new MenuActions();

    private static ArticleService articleService;
    private static CategoryService categoryService;
    private static RoleService roleService;
    private static TagService tagService;
    private static UserService userService;
    private static CreditCardService creditCardService;

    private MenuActions() {
        articleService = new ArticleServiceImpl(new ArticleRepositoryImpl(PersistenceUnitManager.getEntityManager(PersistenceUnit.UNIT_ONE)));
        categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl(PersistenceUnitManager.getEntityManager(PersistenceUnit.UNIT_ONE)));
        tagService = new TagServiceImpl(new TagRepositoryImpl(PersistenceUnitManager.getEntityManager(PersistenceUnit.UNIT_ONE)));
        userService = new UserServiceImpl(new UserRepositoryImpl(PersistenceUnitManager.getEntityManager(PersistenceUnit.UNIT_ONE)));
        roleService = new RoleServiceImpl(new RoleRepositoryImpl(PersistenceUnitManager.getEntityManager(PersistenceUnit.UNIT_ONE)));
        creditCardService = new CreditCardServiceImpl(new CreditCardRepositoryImpl(PersistenceUnitManager.getEntityManager(PersistenceUnit.UNIT_TWO)));
    }

    public static synchronized void startApp() {
        MenuFactory.getMenu(null).showMenu();
    }

    public static synchronized void endApp() {
        articleService.closeEntityManger();
        categoryService.closeEntityManger();
        tagService.closeEntityManger();
        userService.closeEntityManger();
        roleService.closeEntityManger();
        creditCardService.closeEntityManger();
        PersistenceUnitManager.closePersistenceUnits();
    }

    static void seePublishedArticles(Scanner sc) {
        List<Article> articles = articleService.findPublishedArticles();
        if (articles.size() == 0) {
            System.out.println("There is not any published article yet!");
            return;
        }
        while (true) {
            int choice = printArticles(articles, sc);
            try {
                articles.get(choice - 1).printCompleteInformation();
                System.out.print("Press any key to back! ");
                sc.next();
            } catch (IndexOutOfBoundsException e) {
                return;
            }
        }
    }

    static int printArticles(List<Article> articles, Scanner sc) {
        while (true) {
            for (int i = 1; i <= articles.size(); i++) {
                System.out.printf("%n%d. %s", i, articles.get(i - 1));
            }
            System.out.print("Enter your choice (or other number for \"exit\"): ");
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, enter an integer number please!");
                sc.nextLine();
            }
        }
    }

    static void logInUser(Scanner sc) {
        while (true) {
            System.out.printf("%n====== %s ======%n", "LOGIN USER");
            System.out.print("UserName: ");
            String userName = sc.next();
            if (userName.equalsIgnoreCase("esc")) {
                return;
            }
            Optional<User> oUser = userService.findOneUser(userName);
            if (oUser.isPresent()) {
                User user = oUser.get();
                System.out.print("Password: ");
                String password = sc.next();
                if (password.equals(user.getPassword())) {
                    MenuFactory.getMenu(user).showMenu();
                    break;
                } else {
                    System.out.println("Wrong password!");
                }
            } else {
                System.out.println("There is not a User with this UserName!");
            }
        }
    }

    public static void signUpUser(Scanner sc) {
        while (true) {
            System.out.printf("%n====== %S ======%n", "SIGN UP USER");
            try {
                User user = new User();
                System.out.print("UserName: ");
                String userName = sc.next();
                if (userName.equalsIgnoreCase("esc")) {
                    return;
                }
                if (userService.findOneUser(userName).isPresent()) {
                    System.out.println("This UserName has taken already!");
                    continue;
                }
                user.setUserName(userName);
                System.out.print("FirstName: ");
                user.setFirstName(sc.next());
                System.out.print("LastName: ");
                user.setLastName(sc.next());
                System.out.print("NationalCode: ");
                user.setNationalCode(sc.next());
                user.setPassword(user.getNationalCode());
                System.out.print("Birthday (YYYY-MM-DD): ");
                user.setStringBirthDay(sc.next());

                CreditCard creditCard = new CreditCard();
                System.out.print("AccountNumber (CreditCard): ");
                creditCard.setAccountNumber(sc.next());
                creditCard.setOwnerID(user.getId());

                user.setRole(SingletonWriterRole.getWriterRole(roleService));
                user.setAddress(setUserAddress(sc));
                Optional<User> oUser = userService.save(user);
                Optional<CreditCard> oCreditCard = creditCardService.save(creditCard);
                if (oUser.isPresent() && oCreditCard.isPresent()) {
                    System.out.println("You were SignUp correctly, please login!");
                    break;
                } else {
                    System.out.println("There is a User with this information already!");
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
    }

    private static Address setUserAddress(Scanner sc) {
        while (true) {
            try {
                Address address = new Address();
                System.out.print("Your Country: ");
                address.setCountry(sc.next());
                System.out.print("Your City: ");
                address.setCity(sc.next());
                System.out.print("Your Avenue: ");
                address.setAvenue(sc.next());
                System.out.print("Your PostalCode (10 digit): ");
                address.setPostalCode(sc.next());
                return address;
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
    }

    public static void changePassword(User user, Scanner sc) {
        while (true) {
            System.out.printf("%n====== %s ======%n", "CHANGE PASSWORD");
            try {
                System.out.print("Old Password: ");
                String oldPassword = sc.next();
                if (oldPassword.equalsIgnoreCase("esc")) {
                    return;
                } else if (!oldPassword.equals(user.getPassword())) {
                    System.out.println("Wrong Password!");
                    continue;
                }
                System.out.print("New Password: ");
                String newPassword = sc.next();
                if (newPassword.equalsIgnoreCase("esc")) {
                    return;
                }
                user.setPassword(newPassword);
                Optional<User> oUser = userService.update(user);
                if (oUser.isPresent()) {
                    System.out.println("Your password was update successfully!");
                    break;
                } else {
                    System.out.println("There is a problem, We can not update your password!");
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
    }

    public static void seeAndModifyArticles(User user, Scanner sc) {
        List<Article> articles = articleService.findAllUserArticles(user.getId());
        if (articles.size() == 0) {
            System.out.println("You have not any article yet!");
            return;
        }
        while (true) {
            int number = printUserArticles(articles, sc);
            try {
                modifyUserArticle(articles.get(number - 1), sc);
                System.out.print("Press any key to back! ");
                sc.next();
            } catch (IndexOutOfBoundsException e) {
                return;
            }
        }
    }

    private static int printUserArticles(List<Article> articles, Scanner sc) {
        while (true) {
            for (int i = 1; i <= articles.size(); i++) {
                System.out.printf("%n%d. %s", i, articles.get(i - 1));
            }
            System.out.print("Enter your choice for modify (other number for \"exit\"): ");
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, enter an integer number please!");
                sc.nextLine();
            }
        }
    }

    private static void modifyUserArticle(Article article, Scanner sc) {
        while (true) {
            System.out.printf("%n====== %s ======%n", "MODIFY ARTICLE");
            try {
                System.out.println("Old Title: " + article.getTitle());
                System.out.print("New Title: ");
                String newTitle = sc.next() + sc.nextLine();
                if (newTitle.equalsIgnoreCase("esc")) {
                    return;
                } else if (!newTitle.equalsIgnoreCase("pass")) {
                    article.setTitle(newTitle);
                    article.setLastUpdateDate(new Date(System.currentTimeMillis()));
                    Optional<Article> oArticle = articleService.update(article);
                    if (oArticle.isPresent()) {
                        System.out.println("Your article title was updated successfully!");
                        break;
                    } else {
                        System.out.println("There is a problem, we can not update your article title!");
                        continue;
                    }
                }
                System.out.println("Old Brief: " + article.getBrief());
                System.out.print("New Brief: ");
                String newBrief = SingletonScanner.readParagraph();
                if (newBrief.endsWith("esc")) {
                    return;
                } else if (!newBrief.endsWith("pass")) {
                    article.setBrief(newBrief);
                    article.setLastUpdateDate(new Date(System.currentTimeMillis()));
                    Optional<Article> oArticle = articleService.update(article);
                    if (oArticle.isPresent()) {
                        System.out.println("Your article brief was updated successfully!");
                        break;
                    } else {
                        System.out.println("There is a problem, we can not update your article brief!");
                        continue;
                    }
                }
                System.out.println("Old Content: " + article.getContent());
                System.out.print("New Content: ");
                String newContent = SingletonScanner.readParagraph();
                if (newContent.endsWith("esc")) {
                    return;
                } else if (!newContent.endsWith("pass")) {
                    article.setContent(newContent);
                    article.setLastUpdateDate(new Date(System.currentTimeMillis()));
                    Optional<Article> oArticle = articleService.update(article);
                    if (oArticle.isPresent()) {
                        System.out.println("Your article content was updated successfully!");
                        break;
                    } else {
                        System.out.println("There is a problem, we can not update your article content!");
                    }
                }
                return;
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
    }

    public static void writeNewArticle(User user, Scanner sc) {
        List<Category> categories = categoryService.findAllCategories();
        List<Tag> tags = tagService.findAllTags();
        if (tags.size() == 0 || categories.size() == 0) {
            System.out.println("There is not any category or tag yet.\nso you can't create any article!");
            return;
        }
        Article article = new Article();
        System.out.printf("%n====== %s ======%n", "CREATE NEW ARTICLE");
        try {
            while (true) {
                int cNumber = printAllCategories(categories, sc);
                article.getCategories().add(categories.get(cNumber - 1));
                System.out.print("Do you want add another category? ");
                String cAnswer = sc.next();
                if (cAnswer.equalsIgnoreCase("esc")) {
                    return;
                } else if (!cAnswer.equalsIgnoreCase("y")) {
                    break;
                }
            }
            while (true) {
                int tNumber = printAllTags(tags, sc);
                article.getTags().add(tags.get(tNumber - 1));
                System.out.print("Do you want add another tag? ");
                String tAnswer = sc.next();
                if (tAnswer.equalsIgnoreCase("esc")) {
                    return;
                } else if (!tAnswer.equalsIgnoreCase("y")) {
                    break;
                }
            }
            while (true) {
                try {
                    System.out.print("Title: ");
                    String title = sc.next() + sc.nextLine();
                    if (title.equalsIgnoreCase("esc")) {
                        return;
                    }
                    article.setTitle(title);
                    System.out.print("Brief: ");
                    String brief = SingletonScanner.readParagraph();
                    if (brief.endsWith("esc")) {
                        return;
                    }
                    article.setBrief(brief);
                    System.out.print("Content: ");
                    String content = SingletonScanner.readParagraph();
                    if (content.endsWith("esc")) {
                        return;
                    }
                    article.setContent(content);
                    article.setCreateDate(new Date(System.currentTimeMillis()));
                    article.setPublished(false);
                    article.addWriter(user);
                    addOtherWriters(article, sc);
                    Optional<Article> oArticle = articleService.save(article);
                    if (oArticle.isPresent()) {
                        System.out.println("Your article was created correctly!");
                        return;
                    } else {
                        System.out.println("There is a article with this title already!");
                    }
                } catch (InValidDataException e) {
                    System.out.println("Wrong entered data format for " + e.getMessage() + "!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Rolling back to previous step!\n");
        }
    }

    private static int printAllCategories(List<Category> categories, Scanner sc) {
        while (true) {
            for (int i = 1; i <= categories.size(); i++) {
                System.out.printf("%n%d. %s%n", i, categories.get(i - 1));
            }
            System.out.print("Enter your choice for category (other number for \"exit\"): ");
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, enter an integer number please!");
                sc.nextLine();
            }
        }
    }

    private static int printAllTags(List<Tag> tags, Scanner sc) {
        while (true) {
            for (int i = 1; i <= tags.size(); i++) {
                System.out.printf("%n%d. %s%n", i, tags.get(i - 1));
            }
            System.out.print("Enter your choice for tag (other number for \"exit\"): ");
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, enter an integer number please!");
                sc.nextLine();
            }
        }
    }

    private static void addOtherWriters(Article article, Scanner sc) {
        while (true) {
            System.out.print("Enter another writer username (except you): ");
            String writer = sc.next() + sc.nextLine();
            if (writer.equalsIgnoreCase("esc")) {
                return;
            }
            Optional<User> oUser = userService.findOneUser(writer);
            if (oUser.isPresent()) {
                article.addWriter(oUser.get());
                System.out.print("Do you want add another writer? ");
                String ans = sc.next();
                if (ans.equalsIgnoreCase("y")) {
                    continue;
                }
                return;
            } else {
                System.out.println("There is not a User with this UserName!");
                System.out.print("Do you want to SignUp it? ");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("y")) {
                    signUpUser(sc);
                }
            }
        }
    }

    public static void seeAllWrittenArticles(User user, Scanner sc) {
        List<Article> articles = articleService.findAllArticles();
        if (articles.size() == 0) {
            System.out.println("There is not any article yet!");
            return;
        }
        while (true) {
            int choice = printArticles(articles, sc);
            try {
                Article chooseArticle = articles.get(choice - 1);
                chooseArticle.printCompleteInformation();
                changeArticlesPublishState(chooseArticle, sc);
                System.out.print("Press any key to back! ");
                sc.next();
            } catch (IndexOutOfBoundsException e) {
                return;
            }
        }
    }

    public static void changeArticlesPublishState(Article article, Scanner sc) {
        System.out.print("Toggle publication state (current state: " + article.isPublished() + ")? ");
        String answer = sc.next();
        if (answer.equalsIgnoreCase("y")) {
            article.setPublished(!article.isPublished());
            article.setPublishedDate(new Date(System.currentTimeMillis()));
            Optional<Article> oArticle = articleService.update(article);
            if (oArticle.isPresent()) {
                System.out.println("Your article publication state was toggled successfully!");
            } else {
                System.out.println("There is a problem, we can not toggle your article publication state!");
            }
        }
    }

    public static void deleteArticles(User user, Scanner sc) {
        System.out.printf("%n====== %s ======%n", "DELETE ARTICLE");
        while (true) {
            System.out.print("Enter article title: ");
            String articleTitle = sc.next() + sc.nextLine();
            if (articleTitle.equalsIgnoreCase("esc")) {
                return;
            }
            Optional<Article> oArticle = articleService.findOneArticle(articleTitle);
            if (oArticle.isPresent()) {
                System.out.print("Do you want delete it? ");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("y")) {
                    articleService.delete(oArticle.get());
                    System.out.println(articleTitle + " Article was delete successfully!");
                }
            } else {
                System.out.println("There is not any Article with this title!");
            }
        }
    }

    public static void createNewCategory(User user, Scanner sc) {
        while (true) {
            System.out.printf("%n====== %s ======%n", "CREATE NEW CATEGORY");
            try {
                Category category = new Category();
                System.out.print("Title: ");
                String title = sc.next() + sc.nextLine();
                if (title.equalsIgnoreCase("esc")) {
                    return;
                }
                category.setTitle(title);
                System.out.print("Description: ");
                category.setDescription(SingletonScanner.readParagraph());
                Optional<Category> oCategory = categoryService.save(category);
                if (oCategory.isPresent()) {
                    System.out.println("Your category was create correctly!");
                    break;
                } else {
                    System.out.println("There is a category with this title already!");
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
    }

    public static void createNewTag(User user, Scanner sc) {
        while (true) {
            System.out.printf("%n====== %s ======%n", "CREATE NEW TAG");
            try {
                Tag tag = new Tag();
                System.out.print("Title: ");
                String title = sc.next() + sc.nextLine();
                if (title.equalsIgnoreCase("esc")) {
                    return;
                }
                tag.setTitle(title);
                Optional<Tag> oTag = tagService.save(tag);
                if (oTag.isPresent()) {
                    System.out.println("Your tag was create correctly!");
                    break;
                } else {
                    System.out.println("There is a tag with this title already!");
                }
            } catch (InValidDataException e) {
                System.out.println("Wrong entered data format for " + e.getMessage() + "!");
            }
        }
    }

    public static void changeRoleOfUsers(User user, Scanner sc) {
        while (true) {
            List<UserInfo> userInfos = userService.findAllExceptMeInfo(user.getUserName());
            if (userInfos.size() == 0) {
                System.out.println("There is not any other user yet!");
                return;
            }
            int choice = printUsers(userInfos, sc);
            try {
                UserInfo chooseUserInfo = userInfos.get(choice - 1);
                chooseUserInfo.printCompleteInformation();
                changeUserRole(chooseUserInfo, sc);
            } catch (IndexOutOfBoundsException e) {
                return;
            }
        }
    }


    private static int printUsers(List<UserInfo> userInfos, Scanner sc) {
        while (true) {
            for (int i = 1; i <= userInfos.size(); i++) {
                System.out.printf("%n%d. %s%n", i, userInfos.get(i - 1));
            }
            System.out.print("Enter your choice (or other number for \"exit\"): ");
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong format, enter an integer number please!");
                sc.nextLine();
            }
        }
    }

    private static void changeUserRole(UserInfo userInfo, Scanner sc) {
        System.out.print("Enter user role title (current role: " + userInfo.getRole() + "): ");
        String newRole = sc.next();
        if (newRole.equalsIgnoreCase("esc")) {
            return;
        }
        if (newRole.equalsIgnoreCase(userInfo.getRole().getTitle())) {
            System.out.println("Duplicate role!\nnothing being changed!");
            return;
        }
        Optional<Role> oRole = roleService.findOneRole(newRole);
        if (oRole.isPresent()) {
            Optional<User> oUser = userService.findOneUser(userInfo.getId());
            oUser.get().setRole(oRole.get());
            Optional<User> updatedUser = userService.update(oUser.get());
            if (updatedUser.isPresent()) {
                System.out.println("Your user's role was changed successfully!");
            } else {
                System.out.println("There is a problem, we can not change user's role!");
            }
        } else {
            System.out.println("There is not any role with this title yet!");
        }
    }

    static void seeCreditCard(User user, Scanner sc) {
        Optional<CreditCard> oCreditCard = creditCardService.findOneCreditCard(user.getId());
        if (oCreditCard.isPresent()) {
            System.out.printf("%nYour CreditCart's account number is \'%s\'%n", oCreditCard.get());
        }
        System.out.print("Press any key to back! ");
        sc.next();
    }
}

