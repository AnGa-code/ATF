package selenium.utils;

public enum PagePath {

    FORGOT_PASSWORD_PAGE("forgot-password"),
    LOGIN_PAGE("login"),
    SIGNUP_PAGE("signup"),
    UPLOAD_BOOK_PAGE("uploadBook"),
    MY_BOOKS_PAGE("myBooks"),
    MY_PROFILE_PAGE("myProfile"),
    MAIN_PAGE("");

    private final String path;

    PagePath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
}
