/**
 * 
 */
package com.packt;

import java.util.Random;

import com.packt.message.MessageService;

/**
 * @author anierbeck
 *
 */
public class MessageServiceImpl implements MessageService {

	String[] messages = {
			"You can do anything, but not everything.",
			"The richest man is not he who has the most, but he who needs the least.",
			"To the man who only has a hammer, everything he encounters begins to look like a nail.",
			"Do not seek to follow in the footsteps of the men of old; seek what they sought.",
			"The real voyage of discovery consists not in seeking new lands but seeing with new eyes.",
	};
	
	/* (non-Javadoc)
	 * @see com.packt.message.MessageService#getRandomMessage()
	 */
	public String getRandomMessage() {
		Random rand = new Random();
		int nextInt = rand.nextInt(messages.length);
		return messages[nextInt];
	}

}
