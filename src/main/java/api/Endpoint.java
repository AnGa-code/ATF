package api;

public enum Endpoint {
    LOGIN("/Authentication/login"),
    CREATE_USER("/Authentication/register");

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}