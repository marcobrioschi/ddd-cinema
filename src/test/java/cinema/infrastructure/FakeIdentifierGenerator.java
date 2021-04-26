package cinema.infrastructure;

import java.util.UUID;

public class FakeIdentifierGenerator implements IdentifierGenerator {

    private final UUID generatedId;

    public FakeIdentifierGenerator(UUID generatedId) {
        this.generatedId = generatedId;
    }

    @Override
    public UUID generateAnID() {
       return generatedId;
    }

}
