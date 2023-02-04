package edu.qaguru;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.qaguru.model.Bet;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseJson {
    ClassLoader cl = SelenideFilesTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void jsonParseJackson() throws Exception {

        try (InputStream resource = cl.getResourceAsStream("bet.json");
             InputStreamReader reader = new InputStreamReader(resource)) {

           Bet bet = objectMapper.readValue(reader, Bet.class);
            assertThat(bet.status).isEqualTo("accepted");
            assertThat(bet.amount).isEqualTo(1000);
            assertThat(bet.inBonusForDeposit).isFalse();
            assertThat(bet.marketName.en).isEqualTo("1 innings - Warner, David total");

        }
    }
}
