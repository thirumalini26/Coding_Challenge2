package com.event;

import java.util.ArrayList;
import java.util.List;

public class AdoptionEvent {
	// using list for adding participants
    private List<IAdoptable> participants = new ArrayList<>();

    public void hostEvent() {
        System.out.println("Hosting adoption event with participants:");
        for (IAdoptable p : participants) {
            p.adopt();
        }
    }

    public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
    }
}
