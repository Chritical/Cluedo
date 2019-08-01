import org.junit.jupiter.api.Test;

public class EnvelopeTests {

    @Test
    private void test_construction() {
        Envelope envelope1 = new Game().generateEnvelope();
        Envelope envelope2 = new Envelope(new Room(), new Token(), new Weapon());
    }
}
