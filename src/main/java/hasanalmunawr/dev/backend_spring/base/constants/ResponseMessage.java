package hasanalmunawr.dev.backend_spring.base.constants;

public class ResponseMessage {

    public static class Api {
        public static final String API_NOT_FOUND = "API not found";
        public static final String API_ALREADY_REGISTERED = "API already registered";
        public static final String SUCCESS_CREATED = "Api created successfully";
        public static final String API_UNREGISTERED = "API unregistered";
        public static final String API_PASSWORD_NOT_MATCH = "Invalid password";
        public static final String API_LOGIN_SUCCESS = "Login success";
        public static final String NOT_FOUND = "Api not found";
        public static final String SUCCESS_REVOKE = "Api revoked successfully";
    }

    public static class User {
        public static final String USER_NOT_FOUND = "User not found";
        public static final String USER_ALREADY_REGISTERED = "User already registered";
        public static final String USER_REGISTERED = "User registered";
        public static final String USER_UNREGISTERED = "User unregistered";
        public static final String USER_PASSWORD_NOT_MATCH = "Invalid password";
        public static final String USER_LOGIN_SUCCESS = "Login success";
        public static final String VIEW_NOT_FOUND = "Invalid Parameter";
    }

    public static class Authentication {
        public static final String UNAUTHORIZED_HEADER = "Unauthorized - Missing or invalid Authorization header";
        public static final String INVALID_AUTH_FORMAT = "Unauthorized - Invalid Authorization Format";
        public static final String INVALID_TOKEN = "Unauthorized - Invalid Token";
        public static final String FORBIDDEN_PRIVILEGES = "Forbidden - Insufficient privileges";
        public static final String INVALID_BASIC_CREDENTIALS = "Unauthorized - Invalid Credentials Api Key";
        public static final String ACCOUNT_DISABLED = "Forbidden - Account disabled";
        public static final String ACCOUNT_DISABLED_OR_NOT_FOUND = "Forbidden - Account disabled or not found";
    }

    public static class Resource {
        public static final String RESOURCE_NOT_FOUND = "Resource not found";
        public static final String RESOURCE_FOUND = "Resource found";
        public static final String RESOURCE_ALREADY_EXIST = "Resource already exist";
        public static final String RESOURCE_CREATED = "Resource created";
        public static final String RESOURCE_UPDATED = "Resource updated";
        public static final String RESOURCE_DELETED = "Resource deleted";
    }
}
