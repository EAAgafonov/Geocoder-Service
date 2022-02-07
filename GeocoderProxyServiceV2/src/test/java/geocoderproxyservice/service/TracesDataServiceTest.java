package geocoderproxyservice.service;

import com.geocoderproxyservice.data.TracesData;
import com.geocoderproxyservice.repository.TracesDataRepository;
import com.geocoderproxyservice.service.TracesDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TracesDataServiceTest {
    @Mock
    private TracesDataRepository tracesDataRepository;

    private TracesDataService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TracesDataService(tracesDataRepository);
    }

    @Test
    void getTraces() {
        underTest.getTraces();
        verify(tracesDataRepository).findAll();
    }

    @Test
    void saveTrace() {
        TracesData test123 = new TracesData(
                null,
                "2022-02-03 20:18:06",
                "POST",
                "120ms",
                200,
                "/data/dt/"
        );
        underTest.saveTrace(test123);
        verify(tracesDataRepository).save(test123);
    }
}