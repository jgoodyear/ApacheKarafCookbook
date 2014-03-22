package com.packt.consumer;

import com.packt.MessageService;

public class MessageConsumer {

	private MessageService messageService;

	/**
	 * @return the messageService
	 */
	public MessageService getMessageService() {
		return messageService;
	}

	/**
	 * @param messageService the messageService to set
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public void doRun() throws Exception {
		int count = 0;
		
		while(true) {
			System.out.println("Run "+count+": "+messageService.getRandomMessage());
			Thread.sleep(2000);
			count++;
		}
	}
	
}
