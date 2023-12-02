package com.ensa.hackathonindatacore.shared;

public class CoreConstant {
    public static class Exception {
        public static final String NOT_FOUND = "No element could be found with value {0}";
        public static final String ALREADY_EXISTS = "Element {0} already exists";
        public static final String FIND_ELEMENTS = "Error while finding elements";
    }
    public static class Pagination {
        public static final int DEFAULT_PAGE_NUMBER = 0;
        public static final int DEFAULT_PAGE_SIZE = 20;
        public static final int MAX_PAGE_SIZE = 20;
        public static final String DEFAULT_SORT_PROPERTY = "createdAt";
    }
}
