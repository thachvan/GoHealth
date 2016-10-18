package com.gohealth;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAlgorithms {
	@Test
	public void testAlgorithm1() {
		String[] participants = new String[] { "Kyle", "Kenny", "Eric", "Stan", "Stewie", "Brian" };
		String[] assignments = RandomGiftExchange.generateAssignments1(participants);

		checkResult(participants, assignments);
	}

	@Test
	public void testAlgorithm2() {
		String[] participants = new String[] { "Kyle", "Kenny", "Eric", "Stan", "Stewie", "Brian" };
		String[] assignments = RandomGiftExchange.generateAssignments2(participants);

		checkResult(participants, assignments);
	}

	private void checkResult(String[] participants, String[] assignments) {
		assertTrue(participants.length == assignments.length);

		if (participants.length == assignments.length) {
			for (int i = 0; i < assignments.length; i++) {
				assertFalse(assignments[i].equals(participants[i]));
			}
		}
	}

}
