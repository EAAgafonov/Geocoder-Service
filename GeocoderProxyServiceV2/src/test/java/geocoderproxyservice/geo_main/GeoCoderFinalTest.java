package geocoderproxyservice.geo_main;

import com.geocoderproxyservice.geo_main.GeoCoderFinal;
import com.geocoderproxyservice.repository.GeocoderDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GeoCoderFinalTest {
    @Mock
    private GeocoderDataRepository geocoderRepository;
    GeoCoderFinal underTest;

    @BeforeEach
    void setUp() {
        underTest = new GeoCoderFinal(geocoderRepository);
    }

    @Test
    void formRequestFromJsonDataArray() {


    }

    @Test
    void formRequestFromStringAddress() {
    }

    @Test
    void formRequestFromStringCoordinates() {
    }
}