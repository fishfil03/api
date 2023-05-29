import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {
    @ParameterizedTest(name = "{index} - user {0} has the george.bluth@reqres.in email")
    @ValueSource(ints = {1, 2})
    public void get(int id) {
        var email = UserService.getUserEmail(id);
        assertEquals("george.bluth@reqres.in", email);
    }

    @ParameterizedTest(name = "{index} - user {0} has been deleted")
    @ValueSource(ints = {0, 665})
    public void delete(int id) {
        UserService.delete(id);
    }

    @ParameterizedTest(name = "{index} - user {0} has been deleted")
    @MethodSource("postGenerator")
    public void post(String name, String job) {
        UserService.add(name, job);
    }

    @ParameterizedTest(name = "{index} - user {0} has been deleted")
    @MethodSource("putGenerator")
    public void put(int id, String name, String job) {
        UserService.update(id, name, job);
    }

    private static Stream<Arguments> postGenerator() {
        return Stream.of(
                Arguments.of("Андрей", "Руководитель"),
                Arguments.of(null, null));
    }

    private static Stream<Arguments> putGenerator() {
        return Stream.of(
                Arguments.of(1, "Андрей", "Руководитель"),
                Arguments.of(-2, null, null));
    }
}