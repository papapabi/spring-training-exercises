package rewards.infrastructure.monitor.jamon;

import rewards.infrastructure.monitor.Monitor;

public class JamonMonitor implements Monitor {

	private final com.jamonapi.Monitor jamonMonitor;

	public JamonMonitor(com.jamonapi.Monitor jamonMonitor) {
		super();
		this.jamonMonitor = jamonMonitor;
	}

	@Override
	public void start() {
		this.jamonMonitor.start();
	}

	@Override
	public void stop() {
		this.jamonMonitor.stop();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(jamonMonitor.getLabel()).append(": ");
		sb.append("Last=").append(jamonMonitor.getLastValue()).append(", ");
		sb.append("Calls=").append(jamonMonitor.getHits()).append(", ");
		sb.append("Avg=").append(jamonMonitor.getAvg()).append(", ");
		sb.append("Total=").append(jamonMonitor.getTotal()).append(", ");
		sb.append("Min=").append(jamonMonitor.getMin()).append(", ");
		sb.append("Max=").append(jamonMonitor.getMax());
		return sb.toString();
	}

}
