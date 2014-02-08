package TestHelpers;
import java.util.List;

import junit.framework.TestCase;


public class TestCaseHelper extends TestCase{

	public static <T extends Object> T assertIsOfTypeAndGet(
			Class<T> expectedClass, Object obj) {
		if (obj == null) {
			fail("The object was null");
		}
		try {
			return expectedClass.cast(obj);
		} catch (ClassCastException e) {
			fail("Expected object of type '" + expectedClass.getSimpleName()
					+ "' But was of type '" + obj.getClass().getSimpleName()
					+ "'");
		}
		return null;
	}

	public static void assertCollectionsAreEqual(List<?> expectedList,
			List<?> actualList) {
		if (actualList == null) {
			fail("The list was null");
		}
		if (actualList == null) {
			fail("The expected list was null");
		}
		assertEquals(expectedList.size(), actualList.size());
		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i), actualList.get(i));
		}
	}

	public static void assertCollectionsContainSameElementsInDifferentOrder(
			List<?> expectedListInDifferentOrder, List<?> actualList) {
		if (actualList == null) {
			fail("The list was null");
		}
		if (actualList == null) {
			fail("The expected list was null");
		}
		assertEquals(expectedListInDifferentOrder.size(), actualList.size());

		for (int i = 0; i < expectedListInDifferentOrder.size(); i++) {
			if (!expectedListInDifferentOrder.get(i).equals(actualList.get(i))) {
				return;
			}
		}
		fail("The lists wrongly had all the same elements in the same order!!!");
	}
	
}
