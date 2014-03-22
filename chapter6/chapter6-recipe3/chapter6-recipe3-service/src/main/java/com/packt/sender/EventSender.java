package com.packt.sender;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

public class EventSender {

	private EventAdmin eventAdmin;

	/**
	 * @return the eventAdmin
	 */
	public EventAdmin getEventAdmin() {
		return eventAdmin;
	}

	/**
	 * @param eventAdmin the eventAdmin to set
	 */
	public void setEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}
	
	public void doRun() throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		String canonicalHostName = inetAddress.getCanonicalHostName();
		
		int count = 0;
		while(true) {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("content", count);
			properties.put("node", canonicalHostName);
			Event event = new Event("com/packt/cellar/events/FIRED", properties);
			eventAdmin.postEvent(event);
			count++;
			
			Thread.sleep(5000);
		}
	}
	
}
