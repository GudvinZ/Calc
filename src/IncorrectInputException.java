class IncorrectInputException extends Exception {
    private String message;

    IncorrectInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
