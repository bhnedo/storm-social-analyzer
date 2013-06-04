package org.securityartwork.storm.social.analyzer.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.Multiset.Entry;

public class MultiSetUtils {
	
	public static <T> ImmutableList<Entry<T>> topByCount(Multiset<T> multiset,
            int max) {
        ImmutableList<Entry<T>> sortedByCount = sortedByCount(multiset);
        if (sortedByCount.size() > max) {
            sortedByCount = sortedByCount.subList(0, max);
        }

        return sortedByCount;
    }
	
	public static <T> ImmutableList<Entry<T>> sortedByCount(Multiset<T> multiset) {
        Ordering<Multiset.Entry<T>> countComp = new Ordering<Multiset.Entry<T>>() {
            public int compare(Multiset.Entry<T> e1, Multiset.Entry<T> e2) {
                return e2.getCount() - e1.getCount();
            }
        };
        return countComp.immutableSortedCopy(multiset.entrySet());
    }
}
