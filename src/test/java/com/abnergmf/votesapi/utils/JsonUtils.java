package com.abnergmf.votesapi.utils;

import com.abnergmf.votesapi.application.error.JsonFormatException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new JsonFormatException(obj.getClass().getName());
        }
    }
}
