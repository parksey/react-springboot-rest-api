package com.example.gccoffee;

import com.example.gccoffee.order.domain.Email;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EamilTest {
    @Test
    public void testEamil() {
        assertThatThrownBy(()->{
            var email = new Email("acccc");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid email address");
    }

    @Test
    public void equals() {
        var email = new Email("hello@Gmail.com");
        var email2 = new Email("hello@Gmail.com");
        assertThat(email.getEmail()).isEqualTo(email2.getEmail());
        assertThat(email.equals(email2)).isTrue();
        assertThat(email).isEqualTo(email2);
    }
}
