package rewards.infrastructure.monitor.jamon;

import rewards.infrastructure.monitor.Monitor;
import rewards.infrastructure.monitor.MonitorFactory;

public class JamonMonitorFactory implements MonitorFactory {

	private final com.jamonapi.MonitorFactoryInterface jamonMonitorFactory;

	public JamonMonitorFactory() {
		jamonMonitorFactory = com.jamonapi.MonitorFactory.getFactory();
	}

	@Override
	public Monitor create(String name) {
		return new JamonMonitor(jamonMonitorFactory.start(name));
	}

}
