package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderImplTest {
	@Test
	void getSendRussianText(){
		final String RUS_IP = "172.123.12.19";
		String expected = "Добро пожаловать";

		GeoService geoService = Mockito.mock(GeoServiceImpl.class);
		Mockito.when(geoService.byIp(RUS_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

		LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
		Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

		MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
		Map<String, String> headersActual = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, RUS_IP);
		String actual = messageSender.send(headersActual);

		assertEquals(expected, actual);
	}

	@Test
	void getSendEnglishText(){
		final String AMERICAN_IP = "96.44.183.149";
		String expected = "Welcome";

		GeoService geoService = Mockito.mock(GeoServiceImpl.class);
		Mockito.when(geoService.byIp(AMERICAN_IP)).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

		LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
		Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

		MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
		Map<String, String> headersActual = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, AMERICAN_IP);
		String actual = messageSender.send(headersActual);

		assertEquals(expected, actual);
	}
}
