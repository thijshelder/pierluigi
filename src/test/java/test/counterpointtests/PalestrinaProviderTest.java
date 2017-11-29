package test.counterpointtests;

import common.Note;
import counterpoint.PalestrinaProvider;
import counterpoint.Tonality;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PalestrinaProviderTest {

    @Test
    public void testInitialConsonance()
    {
        Note a= new Note(34,0,0);
        Note b = new Note(36,0,0);
        List<Note> notelist = new ArrayList<>();
        notelist.add(a);
        notelist.add(b);
        PalestrinaProvider p = new PalestrinaProvider(Mockito.mock(Tonality.class));
        Assert.assertThat("not consonant", p.determineSonance(new Note(34, 0,0),new Note(38,0,0)), Is.is(true));


    }
}
