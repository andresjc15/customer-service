package pe.com.nttdata.customer.util;

import java.util.concurrent.ExecutionException;

public interface SequenceGeneratorService {

    long generateSequence(final String sequenceName) throws InterruptedException, ExecutionException;

}
