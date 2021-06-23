package com.lcdlv.app;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;


class AppTest {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("addFraction")
    void shouldAddFractions(Fraction fraction, Fraction fractionToAdd, Fraction expectedResult) {
        assertThat(fraction.add(fractionToAdd)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> addFraction() {
        return Stream.of(
                Arguments.of(of(1, 1), of(1, 1), of(2, 1)),
                Arguments.of(of(3, 1), of(8, 1), of(11, 1)),
                Arguments.of(of(4, 1), of(16, 1), of(20, 1)),
                Arguments.of(of(1, 2), of(1, 2), of(1, 1)),
                Arguments.of(of(2, 2), of(2, 2), of(2, 1)),
                Arguments.of(of(5,8), of(4, 2), of(21, 8)),
                Arguments.of(of(2,3), of(1, 4), of(11, 12))
        );
    }

    @ParameterizedTest(name = "{0} simplifies => {1}")
    @MethodSource("simplifyFractions")
    void shouldSimplifyFractions(Fraction fraction, Fraction expectedResult) {
        assertThat(fraction.simplify()).isEqualTo(expectedResult);
    }
    private static Stream<Arguments> simplifyFractions() {
        return Stream.of(
                Arguments.of(of(1, 2), of(1, 2)),
                Arguments.of(of(2, 2), of(1, 1)),
                Arguments.of(of(4, 4), of(1, 1)),
                Arguments.of(of(8, 4), of(2, 1)),
                Arguments.of(of(100, 25), of(4, 1)),
                Arguments.of(of(0, 25), of(0, 1)),
                Arguments.of(of(-47, 25), of(-47, 25))
        );
    }

}
