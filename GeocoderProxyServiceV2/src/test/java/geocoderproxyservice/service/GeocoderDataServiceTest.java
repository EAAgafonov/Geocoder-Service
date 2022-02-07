package geocoderproxyservice.service;

import com.geocoderproxyservice.geo_main.GeoCoder;
import com.geocoderproxyservice.repository.GeocoderDataRepository;
import com.geocoderproxyservice.service.GeocoderDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GeocoderDataServiceTest {
    @Mock
    private GeocoderDataRepository geocoderRepository;
    @Mock
    private GeoCoder geoFinal;
    private GeocoderDataService underTest;

    @BeforeEach
    void setUp() {
        underTest = new GeocoderDataService(geocoderRepository, geoFinal);
    }

    @Test
    void canGetAllData() {
        underTest.getData();
        verify(geocoderRepository).findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


}