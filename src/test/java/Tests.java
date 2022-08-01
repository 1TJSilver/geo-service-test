import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;

public class Tests {
    @Test
    public void geoServiceTest (){
        //arrange
        String ip = "172.1.1.2";
        GeoService geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        //act
        Location location = geoService.byIp(ip);
        //assert
        assert expected.getCity().equals(location.getCity()) &&
                expected.getCountry().equals(location.getCountry());
    }

    @Test
    public void locationServiceTestN1(){
        //arrange
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        Country russia = Country.RUSSIA;
        //act
        String result = localizationService.locale(russia);
        //assert
        assert expected.equals(result);
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
    @Test
    public void messageSenderTest() {
        //arrange
        String expected = "Добро пожаловать";

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("x-real-ip", "172.0.32.11");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService , localizationService);
        //act
        String result = messageSender.send(map);
        //assert
        assert expected.equals(result);
    }
}
