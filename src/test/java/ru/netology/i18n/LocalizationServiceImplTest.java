package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
	@Test
	void getLocale(){
		final String expectedRus = "Добро пожаловать";
		final String expectedOther = "Welcome";
		final Country expectedCountryRus = Country.RUSSIA;

		LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
		Mockito.when(localizationService.locale(expectedCountryRus)).thenReturn(expectedRus);

		assertEquals(expectedRus, localizationService.locale(expectedCountryRus));
		assertEquals(expectedOther, localizationService.locale(Country.GERMANY));
	}
}
