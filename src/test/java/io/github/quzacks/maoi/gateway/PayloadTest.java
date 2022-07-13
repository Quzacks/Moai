package io.github.quzacks.maoi.gateway;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayloadTest {
    private static Payload payload;

    @BeforeAll
    static void setup() {
        JSONObject object = new JSONObject()
                .put("op", 10)
                .put("t", "EVENT_NAME");

        payload = new Payload(object);
    }

    @Test
    void deserialize() {
        assertAll(
            () -> assertEquals(payload.op(), 10),
            () -> assertEquals(payload.name(), "EVENT_NAME"),
            () -> assertNull(payload.data()),
            () -> assertEquals(payload.sequence(), -10)
        );
    }
}