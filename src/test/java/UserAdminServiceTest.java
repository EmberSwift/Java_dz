import org.example.application.UserAdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование сервиса UserAdminService")
class UserAdminServiceTest {

    @Test
    @DisplayName("Ошибка при передаче null в конструктор")
    void ExceptionWhenMapperIsNull() {
        //given
        //when
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new UserAdminService(null)
        );
        //then
        assertEquals("Mapper must not be null", exception.getMessage());
    }

}