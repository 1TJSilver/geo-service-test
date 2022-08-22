import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {
    @Test
    public void geoServiceTest (){
        //arrange
        String ip = "172.1.1.2";
        GeoService geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        //act
        Location location = geoService.byIp(ip);
        //assert
        assert expected.getCity().equals(location.getCity());
    }

}
