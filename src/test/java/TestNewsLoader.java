import edu.iis.mto.staticmock.*;
import edu.iis.mto.staticmock.reader.FileNewsReader;
import edu.iis.mto.staticmock.reader.NewsReader;
import edu.iis.mto.staticmock.reader.WebServiceNewsReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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
    private NewsReader newsReader;
    @Before
    public void SetUp(){
        mockStatic(ConfigurationLoader.class);
        configurationLoader = mock(ConfigurationLoader.class);

        mockStatic(NewsReaderFactory.class);
        newsReaderFactory = mock(NewsReaderFactory.class);

        fileNewsReader = mock(FileNewsReader.class);
        webServiceNewsReader = mock(WebServiceNewsReader.class);
        newsReader = mock(NewsReader.class);
        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);
        when(NewsReaderFactory.getReader("File")).thenReturn(fileNewsReader);
        when(NewsReaderFactory.getReader("WS")).thenReturn(webServiceNewsReader);
    }

    @Test
        public void TestLoadNews(){

        IncomingNews incomingNews = new IncomingNews();
        when(newsReader.read()).thenReturn(incomingNews);
        NewsLoader newsLoader = new NewsLoader();
        PublishableNews publishableNews = newsLoader.loadNews();
        publishableNews.addPublicInfo("content");
        assertThat(publishableNews.getPublicContent().size(),is(1));

    }

    @Test
    public void TestSubscriber() {
        IncomingNews incomingNews = new IncomingNews();
        when(newsReader.read()).thenReturn(incomingNews);
        NewsLoader newsLoader = new NewsLoader();
        PublishableNews publishableNews = newsLoader.loadNews();
        publishableNews.addForSubscription("content", SubsciptionType.A);
        assertThat(publishableNews.getSubscribentContent().size(), is(1));
    }

    @Test
    public void TestNonSubscriber() {
        IncomingNews incomingNews = new IncomingNews();
        when(newsReader.read()).thenReturn(incomingNews);
        NewsLoader newsLoader = new NewsLoader();
        PublishableNews publishableNews = newsLoader.loadNews();
        publishableNews.addPublicInfo("content");
        assertThat(publishableNews.getPublicContent().size(), is(1));
    }


}
