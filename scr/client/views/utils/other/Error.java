package client.views.utils.other;

public enum Error {
        PASSWORD_TYPE(1, "Password should have a number, a caps and..."),
        PASSWORD_NOT_MACH(2,"Passwords should match!!!"),
        EMPTY_FIELDS(2,"Fiels cannot be empty"),
        LOGIN(3,"Username or password are wrong"),
        CONNECTION(4,"there are no connection to the server"),
        USER_AGE_INVALID(5, "User should have at least 18 years old"),
        USER_EXIST(6, "Already exist a user with that email account"),
        INTEGER(7, "Some field must be only numbers");

    private  int num;
        private  String message;

        private Error(int num, String message) {
            this.num = num;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "ERROR " + num + ":" + message;
    }
}
