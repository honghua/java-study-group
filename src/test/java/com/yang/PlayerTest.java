package com.yang;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

//import static com.google.common.truth.Truth8.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PlayerTest {

//    @Mock Player player;
    @Test
    void getWeapon() {
        Player player = mock(Player.class);
        when(player.getWeapon()).thenReturn("weapon");
        assertTrue(player.getWeapon().equals("weapon"));
    }

    @Test
    void throwTest() {
        Player player = mock(Player.class);
        when(player.getWeapon()).thenThrow(new IllegalArgumentException("no weapon"));
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> player.getWeapon());
//        assertThat(illegalArgumentException).getMessage().contains("weapon"));
//        assertThat(illegalArgumentException).hasMessageThat().contains("");
        assertTrue(illegalArgumentException.getMessage().contains("weapon"));
    }
}