package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {
	@Test
	void getLocationByIp(){
		final String RUS_IP = "172.111.11.11";
		final String USA_IP = "96.44.183.149";
		final String LOCAL_IP = "127.0.0.1";

		GeoService geoService = Mockito.spy(GeoServiceImpl.class);
		Mockito.when(geoService.byIp(RUS_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

		assertEquals((new Location("Moscow", Country.RUSSIA, null, 0)).getCountry(), geoService.byIp(RUS_IP).getCountry());
		//For test other logic by ip
		assertEquals((new Location("New York", Country.USA, null,  0)).getCountry(), geoService.byIp(USA_IP).getCountry());
		assertEquals((new Location(null, null, null, 0)).getCountry(), geoService.byIp(LOCAL_IP).getCountry());
	}
}
