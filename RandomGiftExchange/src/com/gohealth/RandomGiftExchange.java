package com.gohealth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomGiftExchange {
	/**
	 * Algorithm 1
	 * 
	 * @param participants
	 *            list of individuals participating in the draw.
	 * @return the list of individuals paired with the participants where
	 *         participants[0] is matched with assignments[0], participants[1]
	 *         is matched with assignments[1], and so on.
	 */
	public static String[] generateAssignments1(final String[] participants) {
		String[] assignments = new String[participants.length];
		int[] status = new int[participants.length];
		Random random = new Random();

		System.arraycopy(participants, 0, assignments, 0, participants.length);

		for (int i = 0; i < participants.length - 1; i++) {
			if (status[i] == 0) {
				// get a random element of the rest of array
				int index = random.nextInt(participants.length - i - 1) + i + 1;
				// swap it with the processing element
				String temp = assignments[i];
				assignments[i] = assignments[index];
				assignments[index] = temp;
				// mark processed elements
				status[i] = 1;
				status[index] = 1;
			}
		}

		// process the last one
		if (status[participants.length - 1] == 0) {
			// swap it with any element
			int index = random.nextInt(participants.length - 1);
			String temp = assignments[participants.length - 1];
			assignments[participants.length - 1] = assignments[index];
			assignments[index] = temp;
		}

		return assignments;
	}

	/**
	 * Algorithm 2
	 * 
	 * @param participants
	 *            list of individuals participating in the draw.
	 * @return the list of individuals paired with the participants where
	 *         participants[0] is matched with assignments[0], participants[1]
	 *         is matched with assignments[1], and so on.
	 */
	public static String[] generateAssignments2(final String[] participants) {
		ArrayList<String> participantsCopy = new ArrayList<String>(Arrays.asList(participants));
		Random random = new Random();
		String[] assignments = new String[participants.length];
		int index;

		for (int i = 0; i < participants.length - 1; i++) {
			boolean cont = false;
			do {
				// get a random match
				index = random.nextInt(participantsCopy.size());
				// do it again if it's the same person
				if (participantsCopy.get(index).equals(participants[i])) {
					cont = true;
				} else {
					// add new result into the list
					assignments[i] = participantsCopy.remove(index);
					cont = false;
				}
			} while (cont);
		}
		// if the last one is at the same position
		if (participantsCopy.get(0).equals(participants[participants.length - 1])) {
			// find another one to swap
			index = 0;
			boolean cont = false;
			do {
				if (!assignments[index].equals(participants[participants.length - 1])) {
					assignments[participants.length - 1] = assignments[index];
					assignments[index] = participantsCopy.get(0);
					cont = false;
				}
			} while (cont && index < assignments.length - 1);
		} else {
			assignments[participants.length - 1] = participantsCopy.get(0);
		}

		return assignments;
	}

	public static void main(String[] args) {
		String[] participants = new String[] { "Kyle", "Kenny", "Eric", "Stan", "Stewie", "Brian" };
		String[] assignments;
		long start, end;

		start = System.currentTimeMillis();
		assignments = generateAssignments1(participants);
		end = System.currentTimeMillis();

		System.out.println("Algorithm 1:");
		System.out.println("\tParticipants: \t" + Arrays.toString(participants));
		System.out.println("\tAssignments: \t" + Arrays.toString(assignments));
		System.out.println("\tTime: " + Long.toString(end - start) + " milliseconds");

		start = System.currentTimeMillis();
		assignments = generateAssignments2(participants);
		end = System.currentTimeMillis();

		System.out.println("Algorithm 2:");
		System.out.println("\tParticipants: \t" + Arrays.toString(participants));
		System.out.println("\tAssignments: \t" + Arrays.toString(assignments));
		System.out.println("\tTime: " + Long.toString(end - start) + " milliseconds");
	}
}
