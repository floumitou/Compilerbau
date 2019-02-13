package DEAGenerator;
import Visitors.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class DFATableBuilder {

    SortedMap<Integer, FollowposTableEntry> followPosTableEntries= new TreeMap<>();
    DFAMap theStateMap;
    public DFATableBuilder(){
        theStateMap=new DFAMap();
    }

    public SortedMap<DFAState, Map<Character, DFAState>> createDFATable(SortedMap<Integer, FollowposTableEntry> followPosTableEntries){
        ArrayList<DFAState> theStates = new ArrayList<>();
        for(int i=0;i<followPosTableEntries.size();i++){
            boolean isAcceptingState = followPosTableEntries.get(i).symbol=="#";
            DFAState tempState = new DFAState(followPosTableEntries.get(i).position,isAcceptingState, followPosTableEntries.get(i).followpos);
            theStates.add(tempState);
        }
        SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<>();
        for(int i=0;i<theStates.size();i++){
            Map<Character,DFAState> tempMap=new TreeMap<>();
            Object[] theFollowPositions = theStates.get(i).positionsSet.toArray();
            for(int j=0;j<theFollowPositions.length;j++){
                for(int k=0;k<followPosTableEntries.size();k++){
                    if((int)theFollowPositions[j]== followPosTableEntries.get(k).position){
                        String theString=followPosTableEntries.get(k).symbol;
                        tempMap.put(theString.charAt(0),theStates.get(k));
                    }
                }
            }
            stateTransitionTable.put(theStates.get(i),tempMap);
        }
        return stateTransitionTable;
    }



}
