/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.enums;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author xxz
 */
public class Enums {

    
    public enum Error {

        SUCCESS("{\"error\":0}"),
        WRONG_SESSION("{\"error\":1}"),
        ACCESS_DENIED("{\"error\":2}"),
        WRONG_INPUT("{\"error\":4}"),
        WRONG_INPUT_DATA("{\"error\":5}"),
        UNKNOWN_ERROR("{\"error\":10}"),
        FOREIGN_KEY_VIOLATION("{\"error\":11}"),
        UNIQUE_VIOLATION("{\"error\":21}"),
        ONLY_ONE_ACTIVE_MANAGER("{\"error\":22}"),
        REQUEST_LIMIT_WAS_REACHED("{\"error\":30}"),
        SEND_SMS_LIMIT_IS_REACHED("{\"error\":31}"),
        EMAIL_IS_NOT_VERIFIED("{\"error\":100}"),
        SMS_NUMBER_IS_NOT_VERIFIED("{\"error\":101}"),
        LIMIT_WAS_REACHED("{\"error\":103}"),
        NOT_ENOUGH_BONUSES("{\"error\":120}"),
        WRONG_WIALON_CONNECTION_DATA("{\"error\":150}"),
        WIALON_ACCOUNT_IS_NOT_ENABLED("{\"error\":151}"),
        ALREADY_REGISTERED("{\"error\":152}"),
        BOX_IS_BUSY("{\"error\":154}"),
        USER_IS_BLOCKED("{\"error\":156}"),
        TOO_LARGE_FILE("{\"error\":157}"),
        ALREADY_IN_DB("{\"error\":158}"),
        ID_NOT_EXIST_IN_DB("{\"error\":159}");
        private String value;

        private Error(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String addToResponse(String addToResponse) {
            return value.replace("}", ",".concat(addToResponse).concat("}"));
        }

        public String addCause(String text) {
            return value.replace("}", ",".concat("\"cause\":\"" + text + "\"").concat("}"));
        }

        @Override
        public String toString() {
            return getValue();
        }
    }

    public enum FilterTypes {
       
        NAME("name"),
        NUMBER("number"),
        GENRE("genre"),
        FILM("film"),
        POPULARITY("popularity"),
        RANGE("range");

        private final String value;

        private FilterTypes(String value) {
            this.value = value;
        }


        public static FilterTypes type(final String type){
            return Stream.of(FilterTypes.values())
                         .filter(sort->sort.value.equals(type))
                         .findFirst()
                         .orElse(GENRE);
        }

        public static boolean in(final String value){
            final List<String> list = new ArrayList<>();
            for (FilterTypes values : FilterTypes.values()) {
                list.add(values.value);
            }
            final boolean contain = list.contains(value);
            list.clear();
            return contain;
        }

        public final String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return getValue();
        }

    }

    public enum ErrorMessage {
        WRONG_JSON_FORMAT("WrongJsonFormat"),
        UNKNOWN_ERROR("UnknownError");
        private String value;

        private ErrorMessage(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return getValue();
        }
    }
}
