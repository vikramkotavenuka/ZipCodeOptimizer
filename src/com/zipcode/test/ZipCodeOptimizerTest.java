package com.zipcode.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.zipcode.entity.Range;
import com.zipcode.optimize.ZipCodeOptimizer;

public class ZipCodeOptimizerTest {
	
	@Test
	public void testSortByLowerBoundaries() {
		//Given
		ArrayList<Range> actualList = new ArrayList<>();
		actualList.add(new Range(94133, 94133));
		actualList.add(new Range(94200, 94299));
		actualList.add(new Range(94226, 94399));
		actualList.add(new Range(94330, 94380));
		actualList.add(new Range(94202, 94228));
		actualList.add(new Range(94462, 94470));
		actualList.add(new Range(94176, 94208));
		
		//When
		ArrayList<Range> sortedList = ZipCodeOptimizer.sortByLowerBoundaries(actualList);
		
		//Then
		assertEquals(7, sortedList.size());
		
	    assertEquals(94133, sortedList.get(0).getLowerBound());
		assertEquals(94133, sortedList.get(0).getUpperBound());
		
		assertEquals(94176, sortedList.get(1).getLowerBound());
		assertEquals(94208, sortedList.get(1).getUpperBound());
		
		assertEquals(94200, sortedList.get(2).getLowerBound());
		assertEquals(94299, sortedList.get(2).getUpperBound());
		
		assertEquals(94202, sortedList.get(3).getLowerBound());
		assertEquals(94228, sortedList.get(3).getUpperBound());
		
		assertEquals(94226, sortedList.get(4).getLowerBound());
		assertEquals(94399, sortedList.get(4).getUpperBound());
		
		assertEquals(94330, sortedList.get(5).getLowerBound());
		assertEquals(94380, sortedList.get(5).getUpperBound());
		
		assertEquals(94462, sortedList.get(6).getLowerBound());
		assertEquals(94470, sortedList.get(6).getUpperBound());
		
	}

	@Test
	public void testRemoveOverlaps() {
		//Given
		ArrayList<Range> actualList = new ArrayList<>();
		actualList.add(new Range(94133, 94133));
		actualList.add(new Range(94176, 94208));
		actualList.add(new Range(94200, 94299));
		actualList.add(new Range(94202, 94228));
		actualList.add(new Range(94226, 94399));
		actualList.add(new Range(94330, 94380));
		actualList.add(new Range(94462, 94470));
		
		//When
		ArrayList<Range> optimizedList = ZipCodeOptimizer.removeOverlaps(actualList);
		
		//Then
		assertEquals(3, optimizedList.size());
		assertEquals(94133, optimizedList.get(0).getLowerBound());
		assertEquals(94133, optimizedList.get(0).getUpperBound());
		
		assertEquals(94176, optimizedList.get(1).getLowerBound());
		assertEquals(94399, optimizedList.get(1).getUpperBound());
		
		assertEquals(94462, optimizedList.get(2).getLowerBound());
		assertEquals(94470, optimizedList.get(2).getUpperBound());
	}

}
