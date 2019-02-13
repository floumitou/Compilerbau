package DEAGenerator;

import Visitors.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class DFATableBuilder {

    SortedMap<Integer, FollowposTableEntry> followPosTableEntries = new TreeMap<>();
    DFAMap stateMap;

    public DFATableBuilder() {
        stateMap = new DFAMap();
    }

    public SortedMap<DFAState, Map<Character, DFAState>> createDFATable(SortedMap<Integer, FollowposTableEntry> followPosTableEntries) {
        ArrayList<DFAState> stateEntries = new ArrayList<>();
        for (int i = 0; i < followPosTableEntries.size(); i++) {
            boolean isAcceptingState = followPosTableEntries.get(i).symbol == "#";
            stateEntries.add(new DFAState(followPosTableEntries.get(i).position,
                    isAcceptingState,
                    followPosTableEntries.get(i).followpos));
        }
        SortedMap<DFAState, Map<Character, DFAState>> dfaTransitionMatrix = new TreeMap<>();
        for (int i = 0; i < stateEntries.size(); i++) {
            Map<Character, DFAState> temporaryMap = new TreeMap<>();
            Object[] theFollowPositions = stateEntries.get(i).positionsSet.toArray();
            for (int j = 0; j < theFollowPositions.length; j++) {
                for (int k = 0; k < followPosTableEntries.size(); k++) {
                    if ((int) theFollowPositions[j] == followPosTableEntries.get(k).position) {
                        String theString = followPosTableEntries.get(k).symbol;
                        temporaryMap.put(theString.charAt(0), stateEntries.get(k));
                    }
                }
            }
            dfaTransitionMatrix.put(stateEntries.get(i), temporaryMap);
        }
        return dfaTransitionMatrix;
    }


}
