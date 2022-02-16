package webservices;

public class Producers {
	private static Producer testProducer;

	public static Producer getTestProducer() {
		return testProducer;
	}

	public static void setTestProducer(Producer testProducer) {
		Producers.testProducer = testProducer;
	}
}
