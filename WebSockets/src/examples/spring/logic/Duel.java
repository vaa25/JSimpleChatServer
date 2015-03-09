package examples.spring.logic;

import java.util.logging.Logger;

/**
 * @author Alexander Vlasov
 */
public class Duel {
    private static Logger log = Logger.getLogger(Duel.class.getName());
    private String sourceId;
    private String destinationId;
    private String status;

    public Duel(String sourceId, String destinationId) {
        log.info("New Duel created: " + sourceId + " vs " + destinationId);
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        status = "request";
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public String getStatus() {
        return status;
    }

    public Duel setStatus(String status) {
        this.status = status;
        return this;
    }

    public Duel getInverted() {
        return new Duel(destinationId, sourceId).setStatus(status);
    }
}
