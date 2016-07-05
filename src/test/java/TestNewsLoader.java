import edu.iis.mto.staticmock.ConfigurationLoader;
import edu.iis.mto.staticmock.NewsLoader;
import edu.iis.mto.staticmock.NewsReaderFactory;
import edu.iis.mto.staticmock.reader.FileNewsReader;
import edu.iis.mto.staticmock.reader.WebServiceNewsReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by pawelek on 03.07.16.
 */

@RunWith(PowerMockRunner.class)
public class TestNewsLoader {
    private ConfigurationLoader configurationLoader;
    private NewsReaderFactory newsReaderFactory;
    private FileNewsReader fileNewsReader;
    private WebServiceNewsReader webServiceNewsReader;
    @Before
    public void SetUp(){
        mockStatic(ConfigurationLoader.class);
        configurationLoader = mock(ConfigurationLoader.class);

        mockStatic(NewsReaderFactory.class);
        newsReaderFactory = mock(NewsReaderFactory.class);

        fileNewsReader = mock(FileNewsReader.class);
        webServiceNewsReader = mock(WebServiceNewsReader.class);

        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);
        when(NewsReaderFactory.getReader("File")).thenReturn(fileNewsReader);
        when(NewsReaderFactory.getReader("WS")).thenReturn(webServiceNewsReader);
    }

    @Test
        public void TestLoadNews(){

        NewsLoader newsLoader = new NewsLoader();
        newsLoader.loadNews();

    }
}
