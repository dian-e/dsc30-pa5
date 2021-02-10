import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class SortsTester {

    Sorts obj;
    ArrayList<String> names;
    ArrayList<String> sortedNames;
    ArrayList<Character> chars;
    ArrayList<Character> sortedChars;
    ArrayList<Integer> nums;
    ArrayList<Integer> sortedNums;

    @Before
    public void setUp() throws Exception {
        obj = new Sorts();

        names = new ArrayList<>();
        String[] namesToAdd = {"Marina", "Mar", "Diane", "Li", "Langlois"};
        for (String name : namesToAdd) { names.add(name); }
        sortedNames = new ArrayList<>();
        String[] sortedNamesToAdd = {"Diane", "Langlois", "Li", "Mar", "Marina"};
        for (String name : sortedNamesToAdd) { sortedNames.add(name); }

        chars = new ArrayList<>();
        char[] charsToAdd = {'Z', 'Y', 'X', 'D', 'C', 'B', 'A'};
        for (char ch : charsToAdd) { chars.add(ch); }
        sortedChars = new ArrayList<>();
        char[] sortedCharsToAdd = {'A', 'B', 'C', 'D', 'X', 'Y', 'Z'};
        for (char ch : sortedCharsToAdd) { sortedChars.add(ch); }

        nums = new ArrayList<>();
        sortedNums = new ArrayList<>();
        int[] numsToAdd = {2, 4, 7, 10, 11, 32, 45, 78};
        for (int num : numsToAdd) {
            nums.add(num);
            sortedNums.add(num);
        }
    }

    @Test
    public void testInsertionSort() {
        obj.InsertionSort(names, 0, 4);
        assertEquals(sortedNames, names);

        obj.InsertionSort(chars, 0, 6);
        assertEquals(sortedChars, chars);

        obj.InsertionSort(nums, 0, 7);
        assertEquals(sortedNums, nums);
    }

    @Test
    public void testQuickSort() {
        obj.QuickSort(names, 0, 4);
        assertEquals(sortedNames, names);

        obj.QuickSort(chars, 0, 6);
        assertEquals(sortedChars, chars);

        obj.QuickSort(nums, 0, 7);
        assertEquals(sortedNums, nums);
    }

    @Test
    public void testModified_QuickSort() {
        obj.Modified_QuickSort(names, 0, 4, 1);
        assertEquals(sortedNames, names);

        obj.Modified_QuickSort(chars, 0, 6, 2);
        assertEquals(sortedChars, chars);

        obj.Modified_QuickSort(nums, 0, 7, 3);
        assertEquals(sortedNums, nums);
    }

    @Test
    public void testTimSort() {
        obj.TimSort(names, 0, 4, 1);
        assertEquals(sortedNames, names);

        obj.TimSort(chars, 0, 6, 3);
        assertEquals(sortedChars, chars);

        obj.TimSort(nums, 0, 7, 3);
        assertEquals(sortedNums, nums);
    }
}