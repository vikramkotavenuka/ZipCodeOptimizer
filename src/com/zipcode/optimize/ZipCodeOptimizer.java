package com.zipcode.optimize;

import java.util.ArrayList;
import java.util.Comparator;

import com.zipcode.entity.Range;
/**
 * 
 * @author Vikram Kumar
 *
 */
public class ZipCodeOptimizer {
	/**
	 * This method takes list of ranges and sort them by lower bounds.
	 * @param rangeList
	 * @return rangeList
	 */
	public static ArrayList<Range> sortByLowerBoundaries(ArrayList<Range> rangeList) {
		rangeList.sort(
				Comparator.comparing(Range :: getLowerBound));
		System.out.println("Zipcode range sorted by lower bounds");
		rangeList.forEach(range -> System.out.println(range.getLowerBound() +" | "+ range.getUpperBound()));
		return rangeList;
	}
	
	/**
	 * This method takes a list of Ranges and checks if there are any overlaps, if found then that overlapped range will be excluded and a new range is created if necessary.
	 * @param rangeList
	 * @return rangeList
	 */
	public static ArrayList<Range> removeOverlaps(ArrayList<Range> rangeList) {
		Range previousRange = null; 
		int counter = 1;
		ArrayList<Range> optimizedList = new ArrayList<>();
		for(Range currentRange : rangeList) {
			if(previousRange == null) {
				previousRange = currentRange;
				if(rangeList.size() == counter) {
					optimizedList.add(currentRange);
				}
				counter ++;
			}else {
				if(currentRange.getLowerBound() <= previousRange.getUpperBound()) { // Check for the overlap and create a new range.
					previousRange = new Range(previousRange.getLowerBound(), previousRange.getUpperBound() > currentRange.getUpperBound() ? previousRange.getUpperBound() : currentRange.getUpperBound());
					if(rangeList.size() == counter) {
						optimizedList.add(previousRange);
					}
				} else {
					optimizedList.add(previousRange);
					if(rangeList.size() == counter) {
						optimizedList.add(currentRange);
					}else {
						previousRange = currentRange;
					}
				}
				counter ++;
			}
		}
		System.out.println("This is the optimized zipcode range:");
		optimizedList.forEach(range -> System.out.println(range.getLowerBound() +" | "+ range.getUpperBound()));
		return optimizedList;
	}
	
	public static void main(String args[]) {
		ArrayList<Range> rangeList = new ArrayList<>();
		rangeList.add(new Range(94133, 94133));
		rangeList.add(new Range(94200, 94299));
		rangeList.add(new Range(94226, 94399));
		rangeList.add(new Range(94330, 94380));
		rangeList.add(new Range(94202, 94228));
		rangeList.add(new Range(94462, 94470));
		rangeList.add(new Range(94176, 94208));
		
		removeOverlaps(sortByLowerBoundaries(rangeList));
	}
}
