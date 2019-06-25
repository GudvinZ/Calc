class SomeException extends Exception {
    private String message;

    SomeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
