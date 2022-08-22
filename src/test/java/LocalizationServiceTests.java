import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceTests {
    @Test
    public void locationServiceTestN1(){
        //arrange
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        Country russia = Country.RUSSIA;
        //act
        String result = localizationService.locale(russia);
        //assert
        assertEquals(expected, result);
    }
    @Test
    public void locationServiceTestN2() {
        //arrange
        LocalizationService localizationService = new LocalizationServiceImpl();
        //act
        String russiaMessage = localizationService.locale(Country.RUSSIA);
        String otherMessage = localizationService.locale(Country.GERMANY);
        //assert
        assert !(russiaMessage.equals(otherMessage));
    }
}
