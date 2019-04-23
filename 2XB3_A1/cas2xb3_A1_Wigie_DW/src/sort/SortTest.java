package sort;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ testBasicQuick.class, testHeap.class, testInsertBinary.class, testInsertComparable.class,
		testMergeBU.class, testMergeTD.class, testSortInsert.class, testThreePartition.class })
public class SortTest {

}
