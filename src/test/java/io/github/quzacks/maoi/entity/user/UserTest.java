package io.github.quzacks.maoi.entity.user;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static User user;

    @BeforeAll
    static void setup() {
        JSONObject userData = new JSONObject()
            .put("id", "1093838974892374892")
            .put("username", "AllahAkbar")
            .put("discriminator", "9033")
            .put("bot", false)
            .put("verified", false)
            .put("public_flags", 644);

        user = new User(userData);
    }

    @Test
    void getFlags() {
        assertEquals(
            user.getFlags(), EnumSet.of(UserFlag.HYPESQUAD, UserFlag.HOUSE_BRILLIANCE, UserFlag.EARLY_SUPPORTER)
        );
    }
}